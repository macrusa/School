// Slow-scope. A free-running AVR / ADC "oscilloscope"
// ------- Preamble -------- //
#include <avr/io.h>
#include <util/delay.h>
#include "pinDefines.h"
#include "USART.h"
#define SAMPLE_DELAY 500 /* ms, controls the scroll-speed of the scope */
// -------- Functions --------- //
static inline void initFreerunningADC(void)
{
  UCSR0B = 11000000;
  ADMUX |= (1 << REFS0); /* reference voltage on AVCC */
  ADCSRA |= (1 << ADPS1) | (1 << ADPS0); /* ADC clock prescaler /8 */
  ADMUX |= (1 << ADLAR); /* left-adjust result, return only 8 bits */
  ADCSRA |= (1 << ADEN); /* enable ADC */
  ADCSRA |= (1 << ADATE); /* auto-trigger enable */
  ADCSRA |= (1 << ADSC); /* start first conversion */
  ADMUX = ADMUX & 0b11110000; //clears all 4 mux bits
  ADMUX = ADMUX | PC3; //set the bits and PC3 is the potentiometer
}
int main(void)
{
  // -------- Inits --------- //
  initUSART();
  initFreerunningADC();
  // ------ Event loop ------ //
  while (1)
  {
    printByte(ADCH); /* transmit the high byte, left-adjusted */
    printString("\n");
    _delay_ms(SAMPLE_DELAY);
  } /* End event loop */
  return (0); /* This line is never reached */
}

