//Frequency 1 Hz = 1 second = 1000ms
int led = 13;				//Establish a int (number) variable called led and set to 13
void setup()				//This is the standard setup when reset is pressed
{
  pinMode(led, OUTPUT);		//The led variable (pin #13) is initialized as an output
}

void loop()				//This loop runs forever. Please check Question II for 
{					//further explanation about the loop() method
  digitalWrite(led, HIGH);		 //Turn on led
  delay(500);				//Waits for 0.5 seconds
  digitalWrite(led, LOW);		//Turn off led
  delay(500);				//Waits for 0.5 seconds
}					 

//Frequency 2Hz = 2 seconds = 2000ms
int led = 13;				//Establish a int (number) variable called led and set to 13
void setup()				//This is the standard setup when reset is pressed
{
  pinMode(led, OUTPUT);		//The led variable (pin #13) is initialized as an output
}

void loop()				//This loop runs forever. Please check Question II for 
{					//further explanation about the loop() method
  digitalWrite(led, HIGH);		 //Turn on led
  delay(1000);				//Waits for a second
  digitalWrite(led, LOW);		//Turn off led
  delay(1000);				//Waits for a second
}	

//Frequency 3Hz = 3 seconds = 3000ms
int led = 13;				//Establish a int (number) variable called led and set to 13
void setup()				//This is the standard setup when reset is pressed
{
  pinMode(led, OUTPUT);		//The led variable (pin #13) is initialized as an output
}

void loop()				//This loop runs forever. Please check Question II for 
{					//further explanation about the loop() method
  digitalWrite(led, HIGH);		 //Turn on led
  delay(1500);				//Waits for 1.5 seconds
  digitalWrite(led, LOW);		//Turn off led
  delay(1500);				//Waits for 1.5 seconds
}	


