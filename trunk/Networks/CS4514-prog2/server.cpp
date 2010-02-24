/*
 * server.cpp
 * CS 4514 (Computer Networks) Program 2
 * Completed on 11/24/2008 by Joshua Dick <jdick@wpi.edu>, Eric Greer <egreer@wpi.edu>,
 * and Tyler Flaherty <flaherty@wpi.edu>.
 */

#include <iostream>		/* for cout */
#include <stdio.h>		/* for printf() and fprintf() */
#include <sys/socket.h>	/* for socket(), bind(), and connect() */
#include <arpa/inet.h>	/* for sockaddr_in and inet_ntoa() */
#include <stdlib.h>		/* for atoi() and exit() */
#include <string.h>		/* for memset() */
#include <unistd.h>		/* for close() */
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include "common.h"

#define SOCKETNUM 60879
#define MAXPENDING 5
#define PACKET_SIZE 190
#define FRAME_SIZE 100
#define END_OF_PICTURE "END_OF_PICTURE"
#define END_OF_PACKET "1"
#define NOT_END_OF_PACKET "0"
#define MAKE_ERROR false //PROBLEM: Server causes client to wait for a nl_ack that occurs in the wrong spot.

using namespace std;

/*Function Declarations*/
void dls_send(char* packet, int length);
int dls_receive();
int nls_write(int openFile, int w_size);
int pls_receive();
void pls_send(char* frame, int length);
void pls_setup();
char* createError(char* frame);

/*GLOBAL VARIABLES*/

/*General*/
FILE* logFile;
int servSock;                    /*Socket descriptor for server */
int clientSock;                    /* Socket descriptor for client */
struct sockaddr_in server;     /* Local address */
struct sockaddr_in client;   /* Client address */
int openFile;

/*Network Layer*/
char nl_buffer[PACKET_SIZE];

/*Data Link Layer*/
char dl_ack[] = "dl_ack";
char dl_buffer[FRAME_SIZE]; /* place to write incoming messages to */
union u_t { unsigned short num; char bytes[2]; } receivedSN; //Represent the same sequence number two ways
short expectedSN;
uint8_t dl_buffer_crc; //Can't allocate memory where this is actually needed

/*Physical Layer*/
char pl_ack[4];
long errorGenerator;
char* error_ack = (char*) malloc(4*sizeof(char));

//Written by Eric Greer and Tyler Flaherty (peer programmed)
int main (int argc, char** argv) {

	pls_setup();

	//Set up the log file
	logFile = fopen("server.log", "w");
    if (logFile == NULL) {
            dieWithError("Error opening client.log for writing");
    }
	
	int rcvd;
	char *fileNames[] = { "photonew1.jpg", "photonew2.jpg", "photonew3.jpg", "photonew4.jpg", "photonew5.jpg", 0};

	int numFiles;
	for (numFiles = 0; fileNames[numFiles] != '\0'; numFiles++) {
		cout << "Opening file \"" << fileNames[numFiles] << "\" for writing..." << endl;
		if ((openFile = open(fileNames[numFiles], O_WRONLY|O_CREAT|O_TRUNC, S_IRUSR|S_IWUSR|S_IRGRP|S_IWGRP)) < 0)
			dieWithError("Output File Open Error");

		cout << "Started receiving picture \"" << fileNames[numFiles] << "\"..." << endl;
		int i = 0;
		long errorGenerator = 0; //Resets the error generator every new file
		while ((rcvd = dls_receive()) > 0) {
			if (strcmp(nl_buffer, END_OF_PICTURE) == 0) {
				dls_send(dl_ack, sizeof(dl_ack));
				cout << "Finished receiving picture '" << fileNames[numFiles] << "'!" << endl << endl;
				break;
			}
			nls_write(openFile, rcvd);
			fprintf(logFile, "Recieved %i packet(s) for file %i...\n", i + 1, numFiles + 1);
			dls_send(dl_ack, sizeof(dl_ack));
			fprintf(logFile, "ACK packet sent...\n");
			i++;
		}
		close(openFile);
	}
	close(servSock);
	fclose(logFile);
	return(0);
}

//Written by Eric Greer and Joshua Dick (peer programmed)
int dls_receive() {
	int pretend;
	int received = 0;
	bzero(nl_buffer, sizeof(nl_buffer));
	bool badCRC;

	char* nlbufptr = nl_buffer;

	while (true) {
		badCRC = true;
		pretend = pls_receive();
		
		//Copy the received sequence number out of the buffer, in bytes...
		memcpy(receivedSN.bytes, dl_buffer, 2);
		//But it's actually a short.
		fprintf(logFile, "Received frame with sequence number %i...\n", receivedSN.num);

		if (pretend == 100) {
			dl_buffer_crc = crcSlow((const uint8_t*)dl_buffer, FRAME_SIZE-2);
			if (memcmp(&dl_buffer_crc, &dl_buffer[FRAME_SIZE-1], 1) == 0)  badCRC = false;
		} else {
			int dl_buffer_length = 6 + (int)dl_buffer[3];
			dl_buffer_crc = crcSlow((const uint8_t*)dl_buffer, dl_buffer_length-2);
			if (memcmp(&dl_buffer_crc, &dl_buffer[dl_buffer_length-1], 1) == 0) badCRC = false;
		}
		
		//Sends ACK if all ready recieved the frame 
		if((receivedSN.num < expectedSN) && !badCRC){
			//Calculate CRC from received sequence number
			uint8_t pl_ack_crc = crcSlow((const uint8_t*)&receivedSN.bytes, 2);
			memcpy(pl_ack, receivedSN.bytes, 2);
			memcpy(pl_ack+2, (const void*)&pl_ack_crc, sizeof(pl_ack_crc));
			error_ack = createError(pl_ack); //Throws error every 7th time
			pls_send(error_ack, sizeof(error_ack));
			fprintf(logFile, "Duplicate frame received!\n");
			
		} else if (receivedSN.num != expectedSN || badCRC) {
			fprintf(logFile, "Frame received in error!\n");
			//NOOP...wait for the client to time out and send the data again
		} else {
			//Calculate CRC from received sequence number
			uint8_t pl_ack_crc = crcSlow((const uint8_t*)&receivedSN.bytes, 2);

			memcpy(pl_ack, receivedSN.bytes, 2);
			memcpy(pl_ack+2, (const void*)&pl_ack_crc, sizeof(pl_ack_crc));
			error_ack = createError(pl_ack); //Throws error every 7th time
			pls_send(error_ack, sizeof(error_ack));
			fprintf(logFile, "ACK frame sent!\n");
			
			received += pretend;		
			received -= 5;
			
			//Check if the received frame is the last frame of the current packet
			if (dl_buffer[2] == *END_OF_PACKET) {
				unsigned int numToRead;
				if (pretend != 100) {
				//Since this frame appears at the end of the packet, assume that
				//the first byte of data is an integer encoded as an unsigned character.
				//This integer denotes how many subsequent bytes of data are actually contained
				//within the frame.
				numToRead = (unsigned int)dl_buffer[3];
				memcpy(nlbufptr, dl_buffer + 4, numToRead);
				received -= 1;
			} else {
				memcpy(nlbufptr, dl_buffer + 3, FRAME_SIZE - 5);
			}

			nlbufptr += numToRead;
			expectedSN++;
			break;
		}

		memcpy(nlbufptr, dl_buffer + 3, FRAME_SIZE - 5);
		nlbufptr += 95;

		expectedSN++;
		}
	}
	return received;
}

//Written by Eric Greer
void dls_send(char* packet, int length) {
	pls_send(packet, length); //Passthrough method for testing
}

//Written by Eric Greer
void pls_setup() {
	//Sets up socket TCP
	if ((servSock = socket(AF_INET, SOCK_STREAM, 0)) < 0) dieWithError("socket() failed");

	/* Construct local address structure */
	bzero((char*)&server, sizeof(server));		/* Zero out structure */
	server.sin_family = AF_INET;                  /* Internet address family */
	server.sin_port = htons(SOCKETNUM);           /* Local port */
	server.sin_addr.s_addr = htonl(INADDR_ANY);   /* Any incoming interface */

	/* Bind to the local address */
	if (bind (servSock, (struct sockaddr *) &server, sizeof(server)) < 0)
		dieWithError("Server bind() failed");

	/* Mark the socket so it will listen for incoming connections */
	if (listen (servSock, MAXPENDING) < 0) dieWithError("listen() failed");

    unsigned int cltsize = sizeof(client);
    if ((clientSock = accept(servSock, (struct sockaddr*)&client, &cltsize)) < 0)
		dieWithError("Server accept() failed");
}

//Written by Tyler Flaherty
void pls_send(char* frame, int length) {
	if (send(clientSock, frame, length, 0) != length)
		dieWithError("pls_send() sent a different number of bytes than expected");
}

//Written by Eric Greer 
char* createError(char* frame){
	if (errorGenerator%7 == 1 && MAKE_ERROR){ 
		memcpy(error_ack, frame, FRAME_SIZE);
		error_ack[2] = 'e';
		errorGenerator++;
		return error_ack;
	}else{
		errorGenerator++;
		return frame;
	}
}

//Written by Joshua Dick
int pls_receive() {
	int received;
	//TODO: MAKE SURE =0 IS CORRECT (ORDERLY SHUTDOWN?)
	if ((received = recv(clientSock, dl_buffer, FRAME_SIZE, 0)) <= 0)
		dieWithError("recv() in pls_receive() failed");
	return received;
}

//Written by Joshua Dick
int nls_write(int openFile, int w_size) {
	int wr_size;
	if ((wr_size = write(openFile, nl_buffer, w_size)) < 0)
	{
		dieWithError("File Write Error");
	}
	return wr_size;
}
