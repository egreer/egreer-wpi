README: PROGRAM 2
CS4514-C08
Joshua Dick <jdick@wpi.edu>, Eric Greer <egreer@wpi.edu>, and Tyler Flaherty <flaherty@wpi.edu>.

-- USAGE INSTRUCTIONS --

The client and server programs can be compiled using the 'make' command. Run ./server first, and then run ./client [hostname] to start the photo transfer.

To enable or disable artifical error insertion, edit either .cpp file and set the defined constant MAKE_ERROR (apppearing at the top of either file) to true.

-- KNOWN ISSUES --

1) We've handed this program in effectively 1 day late, and acknowledge that our grade will be decreased by 10%.
2) Turning Server errors on will cause the program to cease fucnctioning correctly. This is a condition of the server continuing on without knowing that the ACK failed.
3) It seems to be very slow, but all files are transmitted correctly with identical md5sums on the transmitted and received files.


-- DESIGN DECISIONS AND CONSIDERATIONS --

1) For our implementation of timeouts, we chose to make use of timers rather than selects.
2) Although each frame constructed by our programs include 2 bytes to hold CRC values, only one of these two bytes are used. This is because our TA, Choong-Soo Lee, sent us a resource explaining how to calculate 1-byte CRC remainders, available at http://www.netrino.com/Embedded-Systems/How-To/CRC-Calculation-C-Code. The code we used from this resource is properly attributed in our code.
3) If a frame contains less than 95 usable bytes of data, its first data byte is an encoded integer that represents the actual amount of data in the frame, not including itself (< 94 bytes.) This was necessary to find the end of the data in order to calculate CRCs for that data.
4) Although the assignement specification called for one byte to be used as an end-of-picture message, we send an entirely seperate end-of-picture message.