/*
 * client.cpp
 * CS 4514 (Computer Networks) Program 2
 * Completed on 11/24/2008 by Joshua Dick <jdick@wpi.edu>, Eric Greer <egreer@wpi.edu>,
 * and Tyler Flaherty <flaherty@wpi.edu>.
 */

#include <iostream> /* for cout */
#include <stdio.h> /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), connect(), send(), and recv() */
#include <arpa/inet.h> /* for sockaddr_inand inet_addr() */
#include <stdlib.h> /* for atoi() and exit() */
#include <string.h> /* for memset() */
#include <unistd.h> /* for close() */
#include <netdb.h> /* for gethostbyname() */
#include <signal.h> /* for singals*/
#include <time.h>  /*for timers */
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include "common.h"

#define SOCKETNUM 60879
#define PACKET_SIZE 190
#define FRAME_SIZE 100
#define END_OF_PICTURE "END_OF_PICTURE"
#define END_OF_PACKET "1"
#define NOT_END_OF_PACKET "0"
#define MAKE_ERROR false

using namespace std;

/*Function Declarations*/
void dlc_send(char* frame, int length);
int dlc_recieve();
int nlc_read(int openFile);
int plc_recieve();
void plc_send(char* packet, int length);
void plc_setup();
void start_timer();
void timeout(int signal_number);
char* createError(char* frame);

/*GLOBAL VARIABLES*/

/*General*/
FILE* logFile;

/*Network Layer*/
int openPic;
char packetbuf[PACKET_SIZE];
char nl_buffer[PACKET_SIZE];

/*Data Link Layer*/
char dl_ack[] = "dl_ack";
union u_t { unsigned short num; char bytes[2]; } seqIndex; //Represent the same sequence number two ways
char dl_buffer[FRAME_SIZE];
int currFrameSize;
int dataLeft;
timer_t timer_id;
uint8_t dl_buffer_crc;

/*Physical Layer*/
int sock;
char* serverName;
struct sockaddr_in server;   /* Server address */
struct hostent* hp;
char pl_buffer[FRAME_SIZE];
long errorGenerator;
char error_buffer[FRAME_SIZE];

//Written by Eric Greer and Joshua Dick (peer programmed)
int main(int argc, char** argv) {

	int starting_time = (int)time(NULL);
	
	if (argc != 2) {
		cout << "Usage: " << argv[0] << " [hostname/ip]" << endl;
		exit(-1);
	}

	//Set up the log file
	logFile = fopen("client.log", "w");
    if (logFile == NULL) {
            dieWithError("Error opening client.log for writing");
    }

    serverName = argv[1];

	/* Set up a connection */
	plc_setup();

	int rsize;
	char *fileNames[] = { "photo1.jpg", "photo2.jpg", "photo3.jpg", "photo4.jpg", "photo5.jpg", 0};
	int numFiles;
	for (numFiles = 0; fileNames[numFiles] != '\0'; numFiles++) {
		if ((openPic = open(fileNames[numFiles], O_RDONLY)) < 0)
			dieWithError("Input File Open Error");
		cout << "Starting transmission of file " << numFiles + 1 << "..." << endl;
		errorGenerator = 0; //Resets the error rate every file
		int i = 0;
		while ((rsize = nlc_read(openPic)) > 0) {
			//Send packet over to server
			fprintf(logFile, "Sending packet %i of file %i...\n", i, numFiles + 1);
			dlc_send(packetbuf, rsize);
			while (true) {
				dlc_recieve();
				if (strcmp(((char*)nl_buffer), dl_ack) == 0) {
					fprintf(logFile, "Recieved network layer ACK!\n");
					break;
				}
			}
			i++;
		}
		close(openPic);
		dlc_send(END_OF_PICTURE, sizeof(END_OF_PICTURE));
		dlc_recieve();
	}
	close(sock);
	
	int ending_time = (int)time(NULL);
	fprintf(logFile, "Total client execution time: %i seconds.\n", ending_time - starting_time);
	
	fclose(logFile);
}

//Written by Joshua Dick
int nlc_read(int openFile) {
	int rd_size;

	//Clear out the last packet
	bzero(packetbuf, sizeof(packetbuf));

	if ((rd_size = read(openFile, packetbuf, PACKET_SIZE)) < 0) {
		perror("File read error");
		exit(1);
	}
	return rd_size;
}

//Written by Tyler Flaherty
int dlc_recieve() {
	//Pass data from the data link layer to the network layer
	//TODO: Check for an data link layer-level ACK here?
	int received = plc_recieve();
	memcpy(nl_buffer, dl_buffer, sizeof(dl_buffer));
	return received;
}

//Written by Eric Greer and Joshua Dick (peer programmed)
void dlc_send(char* packet, int length) {

	dataLeft = length;
	char* pointer = packet;
	while (dataLeft > 0) {

		//Clear out the last frame
		bzero(pl_buffer, sizeof(pl_buffer));

		//Create a temporary buffer for half of the packet
		char tmpBuf[95];
		bzero(tmpBuf, sizeof(tmpBuf));

		//Set CRC bytes to default values for now
		char crc_buffer[2] = { '0', '0' };

		//Write the sequence number to the buffer as two bytes
		memcpy(pl_buffer, seqIndex.bytes, 2);

		//This is the last frame! END_OF_PACKET byte should be set in all cases.
		if (dataLeft < 95 || dataLeft == 95) {
						
			memcpy(tmpBuf, pointer, dataLeft);
			memcpy(pl_buffer+2, END_OF_PACKET, 1); //Set END_OF_PACKET flag
			
			//Last frame isn't a full frame, so the first byte of data
			//holds the length of the remaining data (since there's no other way to tell
			//how much data is actually in the frame, otherwise.)
			if (dataLeft < 95) {
				unsigned char convert = (unsigned char)dataLeft;
				memcpy(pl_buffer+3, &convert, 1); //Data Flag
				memcpy(pl_buffer+4, tmpBuf, dataLeft); //DATA
				
				//Calculate CRC and add to frame
				crc_buffer[1] = crcSlow((const uint8_t*)pl_buffer, dataLeft+4);
				memcpy(pl_buffer+dataLeft+4, &crc_buffer, 2); //CRC
				
				currFrameSize = dataLeft+6;
			//Last frame IS a full frame, so every data byte contains actual data.
			} else {
				memcpy(pl_buffer+3, tmpBuf, dataLeft); //DATA

				//Calculate CRC and add to frame
				crc_buffer[1] = crcSlow((const uint8_t*)pl_buffer, dataLeft+3);
				memcpy(pl_buffer+dataLeft+3, &crc_buffer, 2); //CRC

				currFrameSize = dataLeft+5;
			}
		//This frame isn't the last frame; proceed normally.
		} else {
			//Copy the payload into the temp buffer
			memcpy(tmpBuf, packet, 95);

			//Copy in EOP (0 in this case because it's not the last packet)
			memcpy(pl_buffer + 2, NOT_END_OF_PACKET, 1); //EOP Flag

			//Copy in the payload to the frame
			memcpy(pl_buffer + 3, tmpBuf, 95); //DATA

			//Calculate CRC and add to frame
			crc_buffer[1] = crcSlow((const uint8_t*)pl_buffer, 98);
			memcpy(pl_buffer+98, &crc_buffer, 2); //CRC

			currFrameSize = FRAME_SIZE;
		}

		fprintf(logFile, "Sending frame with sequence number %i to the physical layer...\n", seqIndex.num);

		//Send the frame down to the physical layer
		plc_send(pl_buffer, currFrameSize);
		start_timer();
		while(true){
			plc_recieve(); //receive ACK
			//Check ACK
			dl_buffer_crc = crcSlow((const uint8_t*)dl_buffer, 2);
			if (memcmp(&dl_buffer[2], &dl_buffer_crc, 1) == 0){
				if(memcmp(dl_buffer, seqIndex.bytes, 2) == 0){
					fprintf(logFile, "ACK frame received successfully!\n");
					timer_delete(timer_id);
					break;
				}
			} 
			fprintf(logFile, "ACK frame received in error!\n");
		}
		
		//Increment the sequence number for the next frame
		seqIndex.num++;
		dataLeft -= 95;
		pointer += 95;
	}
}

//Written by Eric Greer 
char* createError(char* frame){
	if (errorGenerator%5 == 1 && MAKE_ERROR){ 
		memcpy(error_buffer, frame, FRAME_SIZE);
		error_buffer[2] = 'e';
		errorGenerator++;
		return error_buffer;
	}else{
		errorGenerator++;
		return frame;
	}
}

//Written by Tyler Flaherty
int plc_recieve() {
	int received;
	bzero(dl_buffer, sizeof(dl_buffer));
	if ((received = recv(sock, dl_buffer, FRAME_SIZE, 0)) <= 0)
 		dieWithError("recv() in plc_receive failed");
	return received;
}

//Written by Tyler Flaherty
void plc_send(char* frame, int length) {
	frame = createError(frame);
	if (send(sock, frame, length, 0) != length)
		dieWithError("plc_send() sent a different number of bytes than expected");
}

//Written by Eric Greer
void plc_setup() {
	struct hostent *hp;
	//Sets up socket TCP
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
		dieWithError("socket() failed");

	//Prepares Server Address
	bzero((char*)&server, sizeof(server));
	server.sin_family = AF_INET;
	server.sin_port = htons(SOCKETNUM);
	if ((hp = gethostbyname(serverName)) == NULL)
		dieWithError("gethostbyname() failed");
	bcopy(hp->h_addr, (char*)&server.sin_addr, hp->h_length);

	//Connects to Server
	if (connect(sock, (struct sockaddr*) &server, sizeof(server)) < 0)
		dieWithError("connect() failed");
}

//Written by Eric Greer
void timeout(int signal_number) {
	fprintf(logFile, "Timeout occurred!\n");
	plc_send(pl_buffer, currFrameSize);
	fprintf(logFile, "Resent frame!\n");
	start_timer();
}

//Written by Eric Greer
void start_timer() {
	struct itimerspec time_val;
	signal(SIGALRM, timeout);
	timer_create(CLOCK_REALTIME, NULL, &timer_id);

	/* set timeout to 1 second */
	time_val.it_value.tv_sec = 0;
	time_val.it_value.tv_nsec = 20000000; //wait 0.02 sec on time out
	time_val.it_interval.tv_sec = 0;
	time_val.it_interval.tv_nsec = 0;
	timer_settime(timer_id, 0, &time_val, NULL);
}
