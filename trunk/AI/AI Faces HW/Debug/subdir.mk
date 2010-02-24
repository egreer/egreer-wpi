################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
O_SRCS += \
../backprop_initr.o \
../facetrain.o \
../imagenet.o \
../pgmimage.o 

C_SRCS += \
../backprop.c \
../facetrain.c \
../hidtopgm.c \
../imagenet.c \
../outtopgm.c \
../pgmimage.c 

OBJS += \
./backprop.o \
./facetrain.o \
./hidtopgm.o \
./imagenet.o \
./outtopgm.o \
./pgmimage.o 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.c
	@echo 'Building file: $<'
	@echo 'Invoking: XL C++ Compiler'
	/opt/ibmcmp/xlC -c -g -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


