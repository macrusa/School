//ADC - Examples -> 03. Analog -> AnalogInput, Calibration
int photocell = A0;
int led = 12;

int photocellValue = 0;
int photocellMin = 1023;
int photocellMax = 0;

void setup()
{
  pinMode(13, OUTPUT);
  digitalWrite(13, HIGH);

  while(millis() < 5000)
  {
    photocellValue = analogRead(photocell);

    if(photocellValue > photocellMax)
    {
      photocellMax = photocellValue;
    }

    if(photocellValue < photocellMin)
    {
      photocellMin = photocellValue;
    }
  }
  digitalWrite(13, LOW);
}


void loop()
{
  photocellValue = analogRead(photocell);

  photocellValue = map(photocellValue, photocellMin, photocellMax, 0, 255);

  photocellValue = constrain(photocellValue, 0, 255);

  analogWrite(led, photocellValue);
}

