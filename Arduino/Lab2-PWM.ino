//PWM: Examples -> 03.Analog -> Fading

int led9 = 9;
int led10 = 10;

void setup()
{

}

void loop()
{
  for(int fadeValue = 0; fadeValue <= 255; fadeValue +=10)
  {
    analogWrite(led9, fadeValue);
    analogWrite(led10, 255 - fadeValue);
    delay(50);
  }

  for(int fadeValue = 255; fadeValue >= 0; fadeValue -=10)
  {
    analogWrite(led9, fadeValue);
    analogWrite(led10, 255 - fadeValue);
    delay(50);
  }
}

