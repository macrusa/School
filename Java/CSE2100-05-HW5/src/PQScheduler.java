import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * add job job1 with length 3 and priority 0
 * no new job this slice
 * add job job2 with length 2 and priority -3
 * add job job3 with length 1 and priority 3
 * no new job this slice
 * no new job this slice
 * no new job this sliceEOF
 * @author Nancy
 *
 */


public class PQScheduler
{

	public static void main(String[] args)
	{
		/**
		System.out.println("Heap is ");
		HeapSort heap = new HeapSort();
		heap.insert(0);
		heap.insert(-1);
		heap.insert(3);
		heap.insert(-2);
		heap.insert(-5);

		System.out.println("The Min value if " + heap.getRoot());
		heap.removeRoot();
		System.out.println("The Min value if " + heap.getRoot());
		heap.removeRoot();
		System.out.println("The Min value if " + heap.getRoot());
		heap.removeRoot();
		System.out.println("The Min value if " + heap.getRoot());
		heap.removeRoot();
		System.out.println("The Min value if " + heap.getRoot());
		heap.removeRoot();

	}
}
		 **/


		HeapSort priorityHeap = new HeapSort();

		Scanner sc = new Scanner(System.in);
		String input;

		/**
		String part1 = parts[0];
		System.out.println(parts[0]);
		String part2 = parts[1];
		System.out.println(parts[1]);
		 **/

		boolean counter = true;

		int[] lengthArray = new int[40];
		String[] nameArray = new String[40];
		while(counter)
		{
			input = sc.nextLine();
			//System.out.println("Input: " + input);
			String[] parts = input.split("\\s+");
			if(parts[0].equals("add"))
			{
				String jobName = parts[2];
				int length = Integer.parseInt(parts[5]);
				int priority = Integer.parseInt(parts[8]);
				priorityHeap.insert(priority);

				/**Organize data into arrays will use index to get the priority
				 * that will be extracted from tree (always add 20 from root to
				 * find the data index **/

				int index = priority + 20;
				lengthArray[index] = length;
				nameArray[index] = jobName;

				//CPU doing job
				int root = priorityHeap.getRoot();
				int dataIndex = root + 20;
				int slice = lengthArray[dataIndex];
				if(slice == 0)
				{
					priorityHeap.removeRoot();
					root = priorityHeap.getRoot();
					dataIndex = root + 20;
					slice = lengthArray[dataIndex];
				}
				slice = slice - 1;
				lengthArray[dataIndex] = slice;
				//System.out.println("Slice: " + lengthArray[dataIndex]);
				//System.out.println(priorityHeap.getRoot());
				//"Job Name: " + 
				System.out.println(nameArray[dataIndex]);
			}

			else if(parts[0].equals("no"))
			{
				//EOF: end of file
				if(parts[4].equals("sliceEOF"))
				{
					System.out.println();
					counter = false;
				}
				else
				{
					int root = priorityHeap.getRoot();
					int index = root + 20;
					int length = lengthArray[index];
					if(length == 0)
					{
						priorityHeap.removeRoot();
						root = priorityHeap.getRoot();
						//System.out.println("New root: " + root);
						index = root + 20;
						length = lengthArray[index];

					}
					else
					{
					}
					length = length - 1;
					lengthArray[index] = length;
					//System.out.println(priorityHeap.getRoot());
					//"Job Name: " + 
					System.out.println(nameArray[index]);
					//System.out.println("Slice: " + lengthArray[index]);
				}
			}

			else
			{
				System.out.println("Illegal operator " + input);
			}
			//System.out.println("The Min Heap is ");
			//priorityHeap.print();
			//System.out.println("The Min val is " + priorityHeap.getRoot());
		}
	}
}