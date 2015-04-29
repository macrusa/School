import java.util.Arrays;
import java.util.Scanner;

/**Assignment details.
 * Compute all 5th powers of N (linear time process b/c 4 multiplications)
 * 4xN total so O(N) run time (linear)
 * Check if 5-tuples N^5 run time comes from O(N^5) straight-forward
 * A^5 + B^5 + C^5 + D^5 + E^5 = F^5
 * Match is answer no match is no answer
 * O(N^3 logN) more efficient
 * sort all of A^5 + B^5 + C^5 in one group
 * sort all of F^5 - (D^5 + E^5)
 * (non-deceasing order) roughly N^3
 * Check if ordered ones match. If they match, it is answer
 * Merge sort has logN^3 because it is very consistent sorting algorithm.
 * O(N^3 logN^3)
 * O(N^3 3logN)
 * O(N^3 logN)
 * O(N^5) --> O(N^3 logN)
 * How many 3-tuples (A, B, C) are there, where 0 < A <= B <= C <= N? 
 * Combination (r+n-1 choose r)
 * = (3 + n - 1 choose 3)
 * = (n+2 choose 3)
 * = (N(N+1)(2N+1)) / 6 
 * 
 * @author Nancy
 *
 **/

public class EquationSolver
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		EquationSolver solver = new EquationSolver(input);
	}

	private SinglyLinkedList<String> _alphabet;

	public EquationSolver(int input)
	{
		_alphabet = new SinglyLinkedList<String>();
		_alphabet.insertNode("A");
		_alphabet.insertNode("B");
		_alphabet.insertNode("C");
		_alphabet.insertNode("D");
		_alphabet.insertNode("E");
		_alphabet.insertNode("F");
		//All input^5 (WORKS)
		SinglyLinkedList<Long> assignmentList = new SinglyLinkedList<Long>();

		SinglyLinkedList<Long> unusedNumbers = new SinglyLinkedList<Long>();
		for(int i = 0; i <= input; i++)
		{
			unusedNumbers.insertNode((long) Math.pow(i, 5));
			//System.out.println(unusedNumbers[i]);
			//System.out.println("unusedNumbers size: " + unusedNumbers.getSize());
		}
		/**Test for unusedNumbers (WORKS)
		Node<Long> testCurrent = unusedNumbers.getHead();
		for(int i = 0; i < unusedNumbers.getSize(); i++)
		{
			if(i == 0)
			{
				System.out.println("Test: " + testCurrent.getData());
			}
			else
			{
				testCurrent = testCurrent.getNext();
				System.out.println(testCurrent.getData());
			}
		}
		 **/
		//System.out.println("unusedNumbers size: " + unusedNumbers.getSize());
		long[] U = convertList(unusedNumbers);
		long[] output = new long[6];
		combinations(U, 6, 0, output);
	}

	public static void evaluate(long[] assignmentList)
	{
		mergeSort(assignmentList);

		/**for(int i = 0; i < assignmentList.length; i++)
			{
				System.out.println(assignmentList[i]);
			}**/

		long left;
		long right;
		//Need to use 1.0/5 because 1/5 will become 1 because it is a integer. 1.0 makes it a decimal.
		int A = (int) Math.pow(assignmentList[0], (1.0/5));
		int B = (int) Math.pow(assignmentList[1], (1.0/5));
		int C = (int) Math.pow(assignmentList[2], (1.0/5));
		int D = (int) Math.pow(assignmentList[3], (1.0/5));
		int E = (int) Math.pow(assignmentList[4], (1.0/5));
		int F = (int) Math.pow(assignmentList[5], (1.0/5));
		left = assignmentList[0] + assignmentList[1] + assignmentList[2];
		//System.out.println("Left: " + left);
		right = assignmentList[5] - assignmentList[4] - assignmentList[3];
		//System.out.println("Right: " + right);
		/**for(int i = 0; i < assignmentList.length; i++)
		{
			System.out.println("A: " + A);
			System.out.println("B: " + B);
			System.out.println("C: " + C);
			System.out.println("D: " + D);
			System.out.println("E: " + E);
			System.out.println("F: " + F);				
		}**/


		if(left == right)
		{
			System.out.println(A + " " + B + " " + C + " " + D + " " + E + " " + F);
		}
		else
		{
			//System.out.println("Move");
		}
	}

	//Makes all A-F combinations using N (WORKS)
	public static void combinations(long[] array, int size, int start, long[] result)
	{
		if(size == 0)
		{
		}
		else
		{
			for(int i = start; i <= array.length - size; i ++)
			{
				result[result.length - size] = array[i];
				combinations(array, size - 1, i + 1, result);
			}
		}
		//System.out.println("Combination");
		/**for(int i = 0; i < 6; i++)
		{
			System.out.println(result[i]);
		}**/
		evaluate(result);
	}

	public boolean isEmpty(SinglyLinkedList<Long> x)
	{
		if(x.getSize() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static SinglyLinkedList<Long> convertArray(long[] array)
	{
		int length = array.length;
		SinglyLinkedList<Long> list = new SinglyLinkedList<Long>();
		for(int i = 0; i < length; i++)
		{
			list.insertNode(array[i]);
		}
		return list;
	}

	public long[] convertList(SinglyLinkedList<Long> list)
	{
		int length = list.getSize();
		long[] array = new long[length];
		for(int i = 0; i < length; i++)
		{
			Node<Long> current = list.getHead();
			array[i] = current.getData();
			list.removeFirst();
		}
		return array;
	}

	//sequence should be assignmentList with length 6
	//sequence should be assignmentList with length 6
	public static long[] mergeSort(long[] sequence)
	{
		if(sequence.length <= 1)
		{
			return sequence;
		}
		long[] S1 = new long[(int) Math.floor(sequence.length / 2)];
		//System.out.println("S1: " + S1.length);
		long[] S2 = new long[sequence.length - S1.length];
		//System.out.println("S2: " + S2.length);
		System.arraycopy(sequence, 0, S1, 0, S1.length);
		System.arraycopy(sequence, S1.length, S2, 0, S2.length);

		mergeSort(S1);
		mergeSort(S2);

		//System.out.println("merge");
		SinglyLinkedList<Long> list1 = convertArray(S1);
		SinglyLinkedList<Long> list2 = convertArray(S2);
		mergeTwo(list1, list2);
		//System.out.println("Sequence sorted: " + sequence.length);
		return sequence;
	}

	//Sorts by taking 2 SinglyLinkedLists and creates the sorted Array (WORKS)
	public static void mergeTwo(SinglyLinkedList<Long> A, SinglyLinkedList<Long> B)
	{
		long[] S = new long[A.getSize() + B.getSize()];
		//System.out.println("Merge length: " + S.length);
		int x = 0;
		while(!A.isEmpty() && !B.isEmpty())
		{
			//System.out.println("S length: " + x);
			if(A.getHead().getData() < B.getHead().getData())
			{
				S[x] = A.getHead().getData();
				x++;
				A.removeFirst();
				//System.out.println("A now: " + A.getHead().getData());
			}
			else
			{
				S[x] = B.getHead().getData();
				x++;
				B.removeFirst();
				//System.out.println("B now: " + A.getHead().getData());
			}
		}
		while(!A.isEmpty())
		{
			S[x] = A.getHead().getData();
			x++;
			A.removeFirst();
		}
		while(!B.isEmpty())
		{
			S[x] = B.getHead().getData();
			x++;   
			B.removeFirst();
		}
	}
}