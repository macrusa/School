/* PWM Demo with serial control over three LEDs */
// ------- Preamble -------- //
#include <avr/io.h> /* Defines pins, ports, etc */
#include <util/delay.h> /* Functions to waste time */
#include "pinDefines.h"
#include "USART.h"
#define SAMPLE_DELAY 500

void initUSART(void)
{ /* requires BAUD */
  UBRR0 = 12;                        /* defined in setbaud.h */
  UCSR0A |= (1 << U2X0);
#if USE_2X
  UCSR0A |= (1 << U2X0);
#else
  UCSR0A &= ~(1 << U2X0);
#endif
  /* Enable USART transmitter/receiver */
  UCSR0B = (1 << TXEN0) | (1 << RXEN0);
  UCSR0C = (1 << UCSZ01) | (1 << UCSZ00);   /* 8 data bits, 1 stop bit */
}

void transmitByte(uint8_t data)
{
  /* Wait for empty transmit buffer */
  loop_until_bit_is_set(UCSR0A, UDRE0);
  UDR0 = data;                                            /* send data */
}

uint8_t receiveByte(void)
{
  loop_until_bit_is_set(UCSR0A, RXC0);       /* Wait for incoming data */
  return UDR0;                                /* return register value */
}

/* Here are a bunch of useful printing commands */

void printString(const char myString[])
{
  uint8_t i = 0;
  while (myString[i]) {
    transmitByte(myString[i]);
    i++;
  }
}

void readString(char myString[], uint8_t maxLength)
{
  char response;
  uint8_t i;
  i = 0;
  while (i < (maxLength - 1)) {                   /* prevent over-runs */
    response = receiveByte();
    transmitByte(response);                                    /* echo */
    if (response == '\r') {                     /* enter marks the end */
      break;
    }
    else {
      myString[i] = response;                       /* add in a letter */
      i++;
    }
  }
  myString[i] = 0;                          /* terminal NULL character */
}

void printByte(uint8_t byte)
{
  /* Converts a byte to a string of decimal text, sends it */
  transmitByte('0' + (byte / 100));                        /* Hundreds */
  transmitByte('0' + ((byte / 10) % 10));                      /* Tens */
  transmitByte('0' + (byte % 10));                             /* Ones */
}

void printWord(uint16_t word)
{
  transmitByte('0' + (word / 10000));                 /* Ten-thousands */
  transmitByte('0' + ((word / 1000) % 10));               /* Thousands */
  transmitByte('0' + ((word / 100) % 10));                 /* Hundreds */
  transmitByte('0' + ((word / 10) % 10));                      /* Tens */
  transmitByte('0' + (word % 10));                             /* Ones */
}

void printBinaryByte(uint8_t byte) {
  /* Prints out a byte as a series of 1's and 0's */
  uint8_t bit;
  for (bit = 7; bit < 255; bit--) {
    if (bit_is_set(byte, bit))
      transmitByte('1');
    else
      transmitByte('0');
  }
}

char nibbleToHexCharacter(uint8_t nibble)
{
  /* Converts 4 bits into hexadecimal */
  if (nibble < 10) {
    return ('0' + nibble);
  }
  else {
    return ('A' + nibble - 10);
  }
}

void printHexByte(uint8_t byte) {
  /* Prints a byte as its hexadecimal equivalent */
  uint8_t nibble;
  nibble = (byte & 0b11110000) >> 4;
  transmitByte(nibbleToHexCharacter(nibble));
  nibble = byte & 0b00001111;
  transmitByte(nibbleToHexCharacter(nibble));
}

uint8_t getNumber(void)
{
  // Gets a numerical 0-255 from the serial port.
  // Converts from string to number.
  char hundreds = '0';
  char tens = '0';
  char ones = '0';
  char thisChar = '0';
  do {                                                   /* shift over */
    hundreds = tens;
    tens = ones;
    ones = thisChar;
    thisChar = receiveByte();                   /* get a new character */
    transmitByte(thisChar);                                    /* echo */
  } while (thisChar != '\r');                     /* until type return */
  return (100 * (hundreds - '0') + 10 * (tens - '0') + ones - '0');
}




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

static inline void initTimers(void)
{
  // Timer 1 A,B
  TCCR1A |= (1 << WGM10); /* Fast PWM mode, 8-bit */
  TCCR1B |= (1 << WGM12); /* Fast PWM mode, pt.2 */
  TCCR1B |= (1 << CS11); /* PWM Freq = F_CPU/8/256 */
  TCCR1A |= (1 << COM1A1); /* PWM output on OCR1A */
  TCCR1A |= (1 << COM1B1); /* PWM output on OCR1B */
  // Timer 2
  TCCR2A |= (1 << WGM20); /* Fast PWM mode */
  TCCR2A |= (1 << WGM21); /* Fast PWM mode, pt.2 */
  TCCR2B |= (1 << CS21); /* PWM Freq = F_CPU/8/256 */
  TCCR2A |= (1 << COM2A1); /* PWM output on OCR2A */
}

int main(void)
{
  initUSART();
  initFreerunningADC();
  initTimers();

  LED_DDR |= (1 << LED1);
  LED_DDR |= (1 << LED2);
  LED_DDR |= (1 << LED3);

  // ------ Event loop ------ //
  while (1)
  {
    OCR2A = OCR1B;
    OCR1B = OCR1A;
    OCR1A = ADCH; /* read ADC in */
  }
}
