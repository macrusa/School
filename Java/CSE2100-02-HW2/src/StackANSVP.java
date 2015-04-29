// Assignment #2 All Nearest Smaller Value Problem (ANSVP)
// CSE 2100 Fall 2014
// Nancy Cheng
// 9-25-14

import java.util.Scanner;

public class StackANSVP 
{
	public static void main(String[] args)
	{
		System.out.println("Please print the sequence you want to find the ANSVP for");
		System.out.println("To terminate, please type quit");
		int choice;
		Scanner sc = new Scanner(System.in);
		Stack<Integer> stack = new Stack<Integer>();

		while(sc.hasNextInt())
		{
			choice = sc.nextInt();
			//System.out.println("Choice: " + choice);
			if(stack.getSize() == 0)
			{
				System.out.print("- ");
				stack.push(choice);
			}
			else if(stack.getTop().getNumber() < choice)
			{
				System.out.print(stack.getTop().getNumber() + " ");
				stack.push(choice);
				//System.out.println("Got " + stack.getSize());
			}
			else if(stack.getTop().getNumber() > choice)
			{
				//System.out.println("Search through stack: ");
				boolean counter = true;
				while(counter)
				{
					if(stack.getSize() > 0)
					{
						if(stack.getTop().getNumber() < choice)
						{
							System.out.print(stack.getTop().getNumber() + " ");
							stack.push(choice);
							counter = false;
						}
						else if(stack.getTop().getNumber() > choice)
						{
							//System.out.println("pop");
							stack.pop();
						}
					}		
					else
					{
						System.out.print("- "); 
						counter = false;
					}
				}
			}
		}
	}
}


//Scanner Test
/**
		while(counter)
		{
			if(sc.hasNext())
			{
				choice = sc.nextInt();

				System.out.println(choice);
			}
			else
			{
				counter = false;

			}
		}
 **/

//Testing Stack and Node classes with pop and push methods
/**
				stack.push(4);
				System.out.println(stack.getTop().getNumber());
				stack.push(3);
				System.out.println(stack.getTop().getNumber());
				stack.pop();
				System.out.println(stack.getTop().getNumber());
				stack.pop();
				//empty stack now
				stack.pop();
				stack.push(2);
				System.out.println(stack.getTop().getNumber());
				stack.push(4);
				System.out.println(stack.getTop().getNumber());
				stack.push(6);
				System.out.println(stack.getTop().getNumber());
				stack.push(7);
				System.out.println(stack.getTop().getNumber());
				stack.pop();
				System.out.println(stack.getTop().getNumber());
				stack.pop();
				System.out.println(stack.getRemoved().getNumber());
				stack.pop();
				System.out.println(stack.getTop().getNumber());
				stack.pop();
 **/