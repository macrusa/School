/*
SerialLoopback.c
A simple test of serial-port functionality.
Takes in a character at a time and sends it right back out,
 displaying the ASCII value on the LEDs.
*/

// ------- Preamble -------- //
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
#include "pinDefines.h"       //Self-defined in the library
#include "USART.c"            //Self-define in the library

int main(void)
{
  char serialCharacter;

  // -------- Inits --------- //
  LED_DDR = 0xff;                            /* set up LEDs for output */
  initUSART();
  printString("Hello World!\r\n");           /* to test */

  // ------ Event loop ------ //
  while (1)
  {
    serialCharacter = receiveByte();
    transmitByte(serialCharacter);
    //ASCII
    
    //Binary
    printBinaryByte(serialCharacter);
    //Decimal
    printByte(serialCharacter);
    
    LED_PORT = serialCharacter;
    /* display ascii/numeric value of character */

  }                                          /* End event loop */
  return (0);
}
