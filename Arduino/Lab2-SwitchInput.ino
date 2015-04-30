//Switch Input: Examples -> 02. Digital -> Button

const int button = 2;
const int led = 10;

int buttonState = 0;

void setup()
{
  pinMode(led, OUTPUT);
  pinMode(button, INPUT);
}

void loop()
{
  buttonState = digitalRead(button);
  //Pushed HIGH

  if(buttonState == HIGH)
  {
    digitalWrite(led, HIGH);
    delay(250);
    digitalWrite(led, LOW);
    delay(250);
  }
  else
  {
    digitalWrite(led, HIGH);
    delay(1000);
    digitalWrite(led, LOW);
    delay(1000);
  }
}




