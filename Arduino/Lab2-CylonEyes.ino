// ------- Preamble -------- //
#include <avr/io.h>          			 /* Defines pins, ports, etc */
#include <util/delay.h>      			 /* Functions to waste time */

#define DELAYTIME              85      		 /*Symbol, varialbe of DELAYTIME = 85ms */
#define LED_PORT               PORTD     	 /* Defines LED_PORT as PORTD (has 0-7) pins*/
#define LED_PIN                PIND      	 /*Defines LED_PIN as PIND which can read the pins */
#define LED_DDR                DDRD     	/* DDRD (data direction) inputs (0), outputs (1)*/

int main(void)
{

  // -------- Inits --------- //
  uint8_t i = 0;               			 /* Define 8 bit int type called i */
  LED_DDR = 0xff;            			 /* Data Direction Register D: 0x means hexa, 0b means binary,
  0xff is 1111 1111 (all set for output)
  Ok so. 0xff = 1111 1111
        	 0b11111111 = 1111 1111
 using hex (0x) is shorter.*/

  // ------ Event loop ------ //
  while (1)                             			 /* In while loop as long as the
  parameter is true*/
  {

    while (i < 7)                    			  /* another while loop from 0 to 6 (7 loops) */
    {
      LED_PORT = (1 << i);                		 /* illuminate only i'th pin*/
      _delay_ms(DELAYTIME);                 	 /* wait for as defined 85ms */
      i = i + 1;                             			 /* move to the next LED (can use i++)
      Note: i exits with a value of 7*/
    }

    while (i > 0)                    			/* another while loop from 7 to 1 */
    {
      LED_PORT = (1 << i);              		/* illuminate only i'th pin */
      _delay_ms(DELAYTIME);                       /* wait for 85ms */
      i = i - 1;                          			/* move to the previous LED (can use i--)
      Note: i exits with a value of 0 */
    }

  }                                               		   /* End event loop */
}

