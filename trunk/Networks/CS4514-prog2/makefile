all: client server
client: common.o client.cpp
	g++ -g -lrt client.cpp common.o -o client
server: common.o server.cpp
	g++ -g server.cpp common.o -o server
common.o: common.h common.cpp
	g++ -c -g common.cpp
clean:
	rm -f client server *.o *new*.jpg *.log
