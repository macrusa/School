/*
Press the button as quickly as you can after the LEDs light up.
Your time is printed out over the serial port.
*/
//Change baud rate to 4800
// ------- Preamble -------- //
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
#include "pinDefines.h"
#include "USART.h"
#include "support.h"

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

void transmitByte(uint8_t data) {
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

void readString(char myString[], uint8_t maxLength) {
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

void printByte(uint8_t byte) {
  /* Converts a byte to a string of decimal text, sends it */
  transmitByte('0' + (byte / 100));                        /* Hundreds */
  transmitByte('0' + ((byte / 10) % 10));                      /* Tens */
  transmitByte('0' + (byte % 10));                             /* Ones */
}

void printWord(uint16_t word) {
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

char nibbleToHexCharacter(uint8_t nibble) {
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

uint8_t getNumber(void) {
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



static inline void initTimer1(void)
{
  //65536 bits (0:65535)
  /* Normal mode (default), just counting */
  TCCR1B |= (1 << CS11) | (1 << CS10);
  //page 135
  /* Clock speed: 1 MHz / 64,
  each tick is 64 microseconds ~= 15.6 per ms */
  /* No special output modes */
}
static inline void initTimer0(void)
{
  //256 bits (0:25)
  //page 108
  //page 109 enable Overflow
  TIMSK0 |= (1 << TOIE0);
  TCCR0B |= (1 << CS00) | (1 << CS00);
  //64microseconds --> reach 256 bits is 16.384 ms
}

static inline void initTimer2(void)
{
  TIMSK2 |= (1 << TOIE2);
  TCCR2B |= (1 << CS22) | (1 << CS20);
}

void printMilliseconds(uint16_t value)
{
  /* Given a value in milliseconds, prints out how many seconds
     you took over the serial port.  Does ascii conversion, prints
     decimal point, and drops extra leading zeros.
   */
  uint8_t digit;

  printString("\r\nYou took ");
  /* add up ten-thousands */
  digit = 0;
  while (value >= 10000)
  {
    digit++;
    value -= 10000;
  }
  if (digit)
  {
    transmitByte('0' + digit);
  }
  /* add up thousands */
  digit = 0;
  while (value >= 1000) {
    digit++;
    value -= 1000;
  }
  transmitByte('0' + digit);

  /* decimal point here b/c dividing by 1000 */
  transmitByte('.');

  /* add up hundreds */
  digit = 0;
  while (value >= 100) {
    digit++;
    value -= 100;
  }
  transmitByte('0' + digit);
  /* add up tens */
  digit = 0;
  while (value >= 10) {
    digit++;
    value -= 10;
  }
  transmitByte('0' + digit);

  // Ones digit is easy.
  transmitByte('0' + value);

  printString(" seconds.\r\n");
}

void printComments(uint16_t value) {
  /* Given a value in milliseconds, rates your reaction time */
  if (value > 2000) {
    printString("---->  Ummm...this is a reaction timer...\r\n");
  }
  else if (value > 1000) {
    printString("---->  Hello?\r\n");
  }
  else if (value > 500) {
    printString("---->  Slow.\r\n");
  }
  else if (value > 250) {
    printString("---->  Have another cup of coffee.\r\n");
  }
  else if (value > 200) {
    printString("---->  Respectable.\r\n");
  }
  else if (value >= 150) {
    printString("---->  Fast.\r\n");
  }
  else if (value < 150) {
    printString("---->  Amazing!\r\n");
  }
}

void randomDelay(void) {
  /* Waits for a "random" delay from 1 - 3.5 sec */
  /* Requires timer 1 initialized and running */
  /* It's not really random, but very hard to control --
     like coin-flipping. */
  uint8_t randomTime;

  _delay_ms(1000);                              /* wait at least 1 sec */
  randomTime = (uint8_t) TCNT1;
  /* type-casting the 16-bit TCNT1 as an 8-bit number keeps
     only the 8 least-significant (fastest-changing) bits  */
  while (--randomTime) {
    _delay_ms(10);                      /* max value is 255 ~= 2.5 sec */
  }
}




int main(void)
{
  uint16_t timerValue;
  // -------- Inits --------- //
  initUSART();
  int count;
  uint16_t sum;

  initTimer0();
  //initTimer1();
  //initTimer2();

  LED_DDR = 0xff; /* all LEDs for output */
  BUTTON_PORT |= (1 << BUTTON); /* enable internal pull-up */
  printString("\r\nReaction Timer:\r\n");
  printString("---------------\r\n");
  printString("Press any key to start.\r\n");
  // ------ Event loop ------ //
  while (1)
  {
    receiveByte(); /* press any key */
    printString("\r\nGet ready...");
    randomDelay();
    printString("\r\nGo!\r\n");

    LED_PORT = 0xff; /* light LEDs */
    //page 108 ATMega168, Timer/Counter0 Register
    TCNT0 = 0;

    //page 136 ATMega168, Timer/Counter1 Register
    //TCNT1 = 0; /* reset counter */

    //page 160 ATMega168, Timer/Counter2 Register
    //TCNT2 = 0;



    if (bit_is_clear(BUTTON_PIN, BUTTON))
    {
      /* Button pressed _exactly_ as LEDs light up. Suspicious. */
      printString("You're only cheating yourself.\r\n");
    }
    else
    {
      // Wait until button pressed, save timer value.
      loop_until_bit_is_clear(BUTTON_PIN, BUTTON);
      if(TOIE2 == 1)
      {
        count = count + 1;
      }
      timerValue = TCNT2 >> 4;
  
      /* each tick is approx 1/16 milliseconds, so we bit-shift divide */
      sum = count * 15.63;
      printMilliseconds(timerValue + sum);
      printComments(timerValue);
      count = 0;
    }
    // Clear LEDs and start again.
    count = 0;
    LED_PORT = 0x00;
    printString("Press any key to try again.\r\n");
  } /* End event loop */
  return (0); /* This line is never reached */
}
