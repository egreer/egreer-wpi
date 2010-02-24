#include <stdlib.h>
#include <stdint.h>
#include <limits.h>
#include <stdio.h>

typedef uint8_t crc;
crc crcSlow(uint8_t const message[], int nBytes);
void dieWithError(char* errorMessage);
