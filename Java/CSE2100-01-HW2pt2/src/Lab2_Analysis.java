// Assignment #2 All Nearest Smaller Value Problem (ANSVP) Analysis
// CSE 2100 Fall 2014
// Nancy Cheng
// 9-25-14

import java.util.Scanner;

public class Lab2_Analysis
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

		while(sc.hasNext())
		{
			int current = sc.nextInt();
			list.insertNode(current);
			if(list.getSize() == 1)
			{
				System.out.print("- ");
			}
			else if(list.getSize() > 1)
			{
				Node<Integer> below;
				below = list.getEnd().getBelow();
				//System.out.println("Bellow " + below.getNumber());
				//System.out.println("Current " + current);

				if(below.getNumber() < current)
				{
					System.out.print(below.getNumber() + " ");
				}
				else if(below.getNumber() > current)
				{
					int index = 0;
					//int count = 0;
					boolean counter = true;
					while(counter)
					{
						if(index < list.getSize())
						{
							//System.out.println("Inserting nodes");
							if(below.getNumber() < current)
							{
								System.out.print(below.getNumber() + " ");
								//count++;
								//System.out.println("Loop #: " + count);
								counter = false;
							}
							else if(below.equals(list.getStart()))
							{
								System.out.print("- ");
								//count++;
								//System.out.println("Loop #: " + count);
								counter = false;
							}
							else
							{
								below = below.getBelow();
								//count++;
								//System.out.println("Loop #: " + count);
							}
						}
					}
				}
			}
		}
	}
}

/**
SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
list.insertNode(0);
System.out.println("End: " + list.getEnd().getNumber());
list.insertNode(1);
System.out.println("End: " + list.getEnd().getNumber());
list.insertNode(2);
System.out.println("End: " + list.getEnd().getNumber());
list.insertNode(3);
System.out.println("End: " + list.getEnd().getNumber());

System.out.println("End below: " + list.getEnd().getBelow().getNumber());
System.out.println("End below: " + list.getEnd().getBelow().getBelow().getNumber());
System.out.println("End below: " + list.getEnd().getBelow().getBelow().getBelow().getNumber());
 **/

/**
		while(sc.hasNextInt())
		{
			before = sc.nextInt();

			if(before < sc.nextInt())
			{
				System.out.println(before + " ");
			}
			else
			//before > sc.nextInt()
			{
				//loop
				while()
			}			
		}
 **/
