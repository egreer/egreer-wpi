/*
 * common.cpp
 * CS 4514 (Computer Networks) Program 2
 * Completed on 11/21/2008 by Joshua Dick <jdick@wpi.edu>, Eric Greer <egreer@wpi.edu>,
 * and Tyler Flaherty <flaherty@wpi.edu>.
 *
 * This file contains functions common to both the client and server code.
 */

#include "common.h"

#define WIDTH  (8 * sizeof(crc))
#define TOPBIT (1 << (WIDTH - 1))
#define POLYNOMIAL 0x90 /* Binary: 10010000 */

/*
 * The width of the CRC calculation and result.
 * Modify the typedef for a 16 or 32-bit CRC standard.
 * Author: by Michael Barr
 * Site: http://www.netrino.com/Embedded-Systems/How-To/CRC-Calculation-C-Code
 */
crc crcSlow(uint8_t const message[], int nBytes)
{
    crc remainder = 0;
    /*
     * Perform modulo-2 division, a byte at a time.
     */
    for (int byte = 0; byte < nBytes; ++byte)
    {
        /*
         * Bring the next byte into the remainder.
         */
        remainder ^= (message[byte] << (WIDTH - 8));

        /*
         * Perform modulo-2 division, a bit at a time.
         */
        for (uint8_t bit = 8; bit > 0; --bit)
        {
            /*
             * Try to divide the current data bit.
             */
            if (remainder & TOPBIT)
            {
                remainder = (remainder << 1) ^ POLYNOMIAL;
            }
            else
            {
                remainder = (remainder << 1);
            }
        }
    }

    /*
     * The final remainder is the CRC result.
     */
    return (remainder);
}

//Written by Joshua Dick
void dieWithError(char* errorMessage) {
	perror(errorMessage);
	exit(-1);
}
