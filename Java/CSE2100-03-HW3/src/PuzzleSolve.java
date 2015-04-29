// Assignment #3 Summation Puzzles
// CSE 2100 Fall 2014
// Nancy Cheng
// 10-9-14

import java.util.Scanner;

public class PuzzleSolve
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		//input is original input
		String input = sc.next();
		//System.out.println("Input: " + input);
		PuzzleSolve solver = new PuzzleSolve(input);
	}

	private String _puzzleString;
	private SinglyLinkedList<String> _alphabet;
	private String[] _strParts;
	private String[] _valueOfParts;
	public PuzzleSolve(String puzzle)
	{
		_puzzleString = puzzle;
		//System.out.println("_puzzleString: " + puzzle);
		int _puzzleLength = puzzle.length();
		_alphabet = new SinglyLinkedList<String>();

		//GOOD WORKING _alphabet
		String character;	
		for(int p = 0; p < _puzzleLength; p++)
		{
			//System.out.println("Input string: " + input);
			character = String.valueOf(puzzle.charAt(p));
			//System.out.println("Current character " + p + " : " + character);
			if(_alphabet.getSize() == 0)
			{
				_alphabet.insertNode(character);
				//System.out.println("Inserted: " + character);
				//System.out.println("_alphabet Head: " + _alphabet.getHead().getData());
			}
			else if(_alphabet.getSize() > 10)
			{
				//Message, quit, and return _alphabet of not solvable
				System.out.println("This puzzle is not solvable");
				Node<String> oneOutput = _alphabet.getHead();
				for(int i = 0; i < _alphabet.getSize(); i++)
				{
					if(i == 0)
					{
						System.out.print(oneOutput.getData());
					}
					else
					{
						oneOutput = oneOutput.getNext();
						System.out.print(oneOutput.getData());
					}
				}
				p = _puzzleLength;
			}
			else
			{
				Node<String> current = _alphabet.getHead();
				for(int i = 0; i < _alphabet.getSize(); i++)
				{
					//System.out.println("What character am I? " + i + " : " + character);
					if(character.equals(current.getData()))
					{
						i = _alphabet.getSize();
					}
					else if(character.equals("+"))
					{
						i = _alphabet.getSize();
					}
					else if(character.equals("="))
					{
						i = _alphabet.getSize();
					}
					else
					{
						if(current.getData().equals(character))
						{
							i = _alphabet.getSize();
						}
						else if(current == _alphabet.getEnd())
						{
							_alphabet.insertNode(character);
							//System.out.println("Inserted: " + character);
						}
						else
						{
							current = current.getNext();
						}
					}
				}
			}
		}
		//System.out.println(_alphabet.getHead().getData());
		//System.out.println(_alphabet.getHead().getNext().getData());
		//System.out.println(_alphabet.getHead().getNext().getNext().getData());

		//ASSIGNMENTLIST CONTAINS VALUES ACCORDING TO ALPHABET
		SinglyLinkedList<Integer> assignmentList = new SinglyLinkedList<Integer>();		

		//GOOD WORKING _strParts
		_strParts = new String[puzzle.length()];
		for(int i = 0; i < puzzle.length(); i++)
		{
			_strParts[i] = Character.toString(puzzle.charAt(i));
			//System.out.println("i: " + _strParts[i]);
		}

		//GOOD WORKING unusedNumbers
		SinglyLinkedList<Integer> unusedNumbers = new SinglyLinkedList<Integer>();
		for(int i = 0; i < 10; i++)
		{
			unusedNumbers.insertNode(i);
		}

		/** Test for unusedNumbers
		Node<Integer> testCurrent = unusedNumbers.getHead();
		for(int i = 0; i < unusedNumbers.getSize(); i++)
		{
			if(i == 0)
			{
				System.out.println(testCurrent.getData());
			}
			else
			{
				testCurrent = testCurrent.getNext();
				System.out.println(testCurrent.getData());
			}
		}**/
		//System.out.println("unusedNumbers size: " + unusedNumbers.getSize());

		recursiveSolve(0, unusedNumbers, assignmentList);
	}

	public void evaluate(SinglyLinkedList<Integer> assignmentList)
	{
		String testPuzzle = _puzzleString;

		Node<Integer> assignmentListCurrent = assignmentList.getHead();
		Node<String> alphabetCurrent = _alphabet.getHead();
		for(int i = 0; i < _alphabet.getSize(); i++)
		{
			if(i == 0)
			{
				int digit = assignmentListCurrent.getData();
				String letter = alphabetCurrent.getData();
				testPuzzle = testPuzzle.replaceAll(letter, String.valueOf(digit));
				//System.out.println("Replace: " + digit + " with " + letter);
				//System.out.println(testPuzzle);
			}
			else
			{
				assignmentListCurrent = assignmentListCurrent.getNext();
				alphabetCurrent = alphabetCurrent.getNext();
				int digit = assignmentListCurrent.getData();
				String letter = alphabetCurrent.getData();
				testPuzzle = testPuzzle.replaceAll(letter, String.valueOf(digit));
				//System.out.println(testPuzzle);
				//System.out.println("Replace: " + digit + " with " + letter);
			}
		}	

		_valueOfParts = testPuzzle.split("[\\+=]");
		//System.out.println(_valueOfParts[0]);
		//System.out.println(_valueOfParts[1]);
		//System.out.println(_valueOfParts[2]);

		int sum = 0;
		for(int i = 0; i < _valueOfParts.length; i++)
		{
			int convertedInt = Integer.parseInt(_valueOfParts[i]);
			//System.out.println("Converted Int: " + convertedInt);
			if(i == 0)
			{
				sum = convertedInt;
			}
			else if(i == _valueOfParts.length - 1)
			{
				if(sum == convertedInt)
				{
					Node<String> current = _alphabet.getHead();
					System.out.println("--Possible output --");
					for(int p = 0; p < _alphabet.getSize(); p++)
					{
						if(p == 0)
						{
							System.out.print(current.getData());
						}
						else
						{
							current = current.getNext();
							System.out.print(current.getData());
						}
					}
					System.out.println();

					SinglyLinkedList<String> output;
					output = new SinglyLinkedList<String>();
					for(int a = 0; a < _valueOfParts.length; a++)
					{
						//System.out.println("Loop number: " + a);
						if(a == 0)
						{
							output.insertNode(_valueOfParts[a]);
						}
						else
						{
							Node<String> currentO = output.getHead();
							//System.out.println(output.getSize());
							for(int o = 0; o < output.getSize(); o++)
							{
								if(_valueOfParts[a].equals(currentO.getData()))
								{
									o = output.getSize();
								}
								else if(o == output.getSize() - 1)
								{
									if(_valueOfParts[a].equals(currentO.getData()))
									{
										o = output.getSize();
									}
									else
									{
										output.insertNode(_valueOfParts[a]);
										o = output.getSize();
									}
								}
								else
								{
									currentO = currentO.getNext();
								}
							}
						}
					}

					//System.out.println("Final size: " + output.getSize());
					Node<String> outputCurrent = output.getHead();
					for(int b = 0; b < output.getSize(); b++)
					{
						System.out.print(outputCurrent.getData());
						outputCurrent = outputCurrent.getNext();
					}
					System.out.println();
				}
			}
			else
			{
				sum = sum + convertedInt;
			}
		}
	}

	public void recursiveSolve(int d, SinglyLinkedList<Integer> unusedNumbers, SinglyLinkedList<Integer> assignmentList)
	{
		System.out.println(" assign: " + assignmentList.getSize());
		System.out.println("unused: " + unusedNumbers.getSize());
		int k = _alphabet.getSize();
		int n = unusedNumbers.getSize();

		/**Removing is unused. n is 10. k is _alphabet size
		for(int i = 0; i < n; i++)
		{
			if(i == 0)
			{
				System.out.println("Removing: " + removing.getData());
				System.out.println("N: " + n);
				System.out.println("K: " + k);
			}
			else
			{
				removing = removing.getNext();
				System.out.println("Removing: " + removing.getData());
				System.out.println("N: " + n);
				System.out.println("K: " + k);
			}
		}**/

		//System.out.println("d: " + d);
		//System.out.println(" assign: " + assignmentList.getSize());
		//System.out.println("unused: " + unusedNumbers.getSize());
		if(d == k)
		{
			//System.out.println("d: " + d);
			//System.out.println("k: " + k);
			/**System.out.println("Evaluate: " + assignmentList.getHead().getData());
			System.out.println(assignmentList.getHead().getNext().getData());
			System.out.println(assignmentList.getHead().getNext().getNext().getData());
			**/
			//System.out.println("Evaluate: ");
			evaluate(assignmentList);
			//System.out.println("Current head: " + assignmentList.getHead().getData());
		}
		else
		{
			for(int i = 0; i < n; i++)
			{
				Node<Integer> removing = unusedNumbers.getHead();
				unusedNumbers.removeFirst();
				//System.out.println("Current head: " + unusedNumbers.getHead().getData());
				assignmentList.addFirst(removing);

				recursiveSolve(d+1, unusedNumbers, assignmentList);

				assignmentList.removeFirst();
				unusedNumbers.addLast(removing);
				//System.out.println("Current head: " + unusedNumbers.getHead().getData());
			}
		}
		/**
		System.out.println("Length: " + assignmentList.getSize());
		System.out.println(assignmentList.getHead().getData());
		System.out.println(assignmentList.getHead().getNext().getData());
		System.out.println(assignmentList.getHead().getNext().getNext().getData());
		**/
	}
}

/**_alphabet SinglyLinkedList Test
System.out.println(_alphabet.getHead().getData());
System.out.println(_alphabet.getHead().getNext().getData());
System.out.println(_alphabet.getHead().getNext().getNext().getData());
System.out.println(_alphabet.getHead().getNext().getNext().getNext().getData());
 **/

/**SinglyLinkedList Test

	public static void main(String[] args)
	{
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		list.insertNode(0);
		System.out.println("Head: " + list.getHead().getData());
		System.out.println("End: " + list.getEnd().getData());
		list.insertNode(1);
		System.out.println("Head: " + list.getHead().getData());
		System.out.println("End: " + list.getEnd().getData());
		list.insertNode(2);
		System.out.println("Head: " + list.getHead().getData());
		System.out.println("End: " + list.getEnd().getData());
		list.insertNode(3);
		System.out.println("Head: " + list.getHead().getData());
		System.out.println("End: " + list.getEnd().getData());
		list.setLast(list.getHead().getNext().getNext());
		list.setFirst(list.getHead().getNext());
		System.out.println("Head: " + list.getHead().getData());
		System.out.println("End: " + list.getEnd().getData());
 **/