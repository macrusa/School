// Assignment #1 Stock Company
// CSE 2100 Fall 2014
// Nancy Cheng
// 9-14-14

import java.util.Scanner;

public class StockTran 
{
	public static void main(String[] args)
	{	
		Scanner sc = new Scanner(System.in);
		boolean _counter = true;
		LinkedList list = new LinkedList();

		while(_counter)
		{
			String choice;
			System.out.println("Buy: b, # shares, $# each");
			System.out.println("Sell: s # shares, $# each");
			System.out.println("Print capital gain: c");
			System.out.println("Quit program: q");
			System.out.print("Input: ");

			choice = sc.next();			
			if(choice.equals("b"))
			{
				if(sc.hasNext())
				{
					if(sc.hasNextInt())
					{
						int shares;
						shares = sc.nextInt();
						if(sc.hasNextInt())
						{
							int cost;
							cost = sc.nextInt();
							list.insertNode(shares, cost);
							//System.out.println("Capital: " + n.getCapital());
						}
					}

					else
					{
						System.out.println("Illegal operator " + choice);
					}
				}
			}

			else if(choice.equals("s"))
			{
				int shares;
				shares = sc.nextInt();
				int cost;
				cost = sc.nextInt();
				list.deleteNode(shares, cost);
			}

			else if(choice.equals("c"))
			{
				System.out.println("Capital: " + list.getCapital());
			}

			else if(choice.equals("q"))
			{
				_counter = false;
			}

			else
			{
				System.out.println("Illegal operator " + choice);
			}
		}
	}
}