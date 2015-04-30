//Serial: Examples -> 04.Communication -> SerialEvent
String inputString = "Hello World";
boolean stringComplete = true;

void setup()
{
  Serial.begin(9600);
  inputString.reserve(200);
}

void loop()
{
  if(stringComplete)
  {
    Serial.println(inputString);
    stringComplete = false;
  }
}

