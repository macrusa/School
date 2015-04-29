import java.util.Scanner;

//Please note I used "--" as a single horizontal bar
//Note to self: PUSH ENTER after data is inputted
/**
4 1 0 2 3
1 2 3
4 5 6 7
8 9 10
11 12 13 14
15 16 17
18 19 20 21
22 23 24

 */
/**
5 1 0 2 4
1 2 3 4
5 6 7 8 9
10 11 12 13
14 15 16 17 18
19 20 21 22
23 24 25 26 27
28 29 30 31
32 33 34 35 36
37 38 39 40
41 42 43 44 45
 **/

public class MazeConstruct
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int startR = sc.nextInt();
		int startC = sc.nextInt();
		int endR = sc.nextInt();
		int endC = sc.nextInt();
		HeapPriorityQueue heap = new HeapPriorityQueue();

		int vertices = size * size;
		int edges = vertices - 1;

		Vertex[][] graph = new Vertex[size][size];
		for(int r = 0; r < size; r++)
		{
			for(int c = 0; c < size; c++)
			{
				graph[r][c] = new Vertex("   ", r, c);
			}
		}

		//Initial Row (Row 0)
		int n = size - 1;
		for(int i = 0; i < size; i++)
		{
			if(i == 0)
			{
				int weight = sc.nextInt();
				Edge edge = new Edge(weight, 0, i, 0, i + 1, "|");
				Entry entry = new Entry(weight, edge);
				heap.insert(entry);

				graph[0][i].setTop(null);
				graph[0][i].setRight(edge);
				graph[0][i].setLeft(null);
			}
			else if(i == n)
			{
				graph[0][i].setTop(null);
				graph[0][i].setRight(null);
				graph[0][i].setLeft(graph[0][i - 1].getRight());
			}
			else
			{
				int weight = sc.nextInt();
				Edge edge = new Edge(weight, 0, i, 0, i + 1, "|");
				Entry entry = new Entry(weight, edge);
				heap.insert(entry);

				graph[0][i].setTop(null);
				graph[0][i].setRight(edge);
				//System.out.println(array[0][i].getRight().getWeight());
				graph[0][i].setLeft(graph[0][i - 1].getRight());
			}
		}
		//Row 1 and after index to size (vertices)
		for(int r = 1; r < n; r++)
		{
			for(int c = 0; c < size; c++)
			{
				int weight = sc.nextInt();
				Edge edge = new Edge(weight, r, c, r - 1, c, " -- ");
				Entry entry = new Entry(weight, edge);
				heap.insert(entry);
				if(c == 0)
				{
					graph[r][c].setTop(edge);
					//System.out.println(array[r][c].getTop().getWeight());
					graph[r - 1][c].setBottom(graph[r][c].getTop());
					graph[r][c].setLeft(null);
				}
				else if(c == n)
				{
					graph[r][c].setTop(edge);
					//System.out.println(array[r][c].getTop().getWeight());
					graph[r - 1][c].setBottom(graph[r][c].getTop());
				}
				else
				{
					graph[r][c].setTop(edge);
					//System.out.println(array[r][c].getTop().getWeight());
					graph[r - 1][c].setBottom(graph[r][c].getTop());
				}
			}
			for(int c = 0; c < size; c++)
			{
				if(c == 0)
				{
					int weight = sc.nextInt();
					Edge edge = new Edge(weight, r, c, r, c + 1, "|");
					Entry entry = new Entry(weight, edge);
					heap.insert(entry);
					graph[r][c].setRight(edge);
					//System.out.println(array[r][c].getRight().getWeight());
					graph[r][c].setLeft(null);
				}
				else if(c == n)
				{
					graph[r][c].setBottom(graph[r + 1][c].getTop());
					graph[r][c].setRight(null);
					graph[r][c].setLeft(graph[r][c - 1].getRight());
				}
				else
				{
					int weight = sc.nextInt();
					Edge edge = new Edge(weight, r, c, r, c + 1, "|");
					Entry entry = new Entry(weight, edge);
					heap.insert(entry);
					graph[r][c].setRight(edge);
					//System.out.println(array[r][c].getRight().getWeight());
					graph[r][c].setLeft(graph[r][c - 1].getRight());
				}
			}
		}

		//Last Column and Row (nth row)
		for(int c = 0; c < size; c++)
		{
			int weight = sc.nextInt();
			Edge edge = new Edge(weight, n, c, n - 1, c, " -- ");
			Entry entry = new Entry(weight, edge);
			heap.insert(entry);
			graph[n][c].setTop(edge);
			//System.out.println(array[n][c].getTop().getWeight());
			graph[n - 1][c].setBottom(graph[n][c].getTop());
		}
		//HERE PROBLEM
		for(int z = 0; z < size; z++)
		{
			if(z == 0)
			{
				int weight = sc.nextInt();
				Edge edge = new Edge(weight, n, z, n, z + 1, "|");
				Entry entry = new Entry(weight, edge);
				heap.insert(entry);
				graph[n][z].setBottom(null);
				graph[n][z].setRight(edge);
				//System.out.println(array[n][z].getRight().getWeight());
				graph[n][z].setLeft(null);
			}
			else if(z == n)
			{
				graph[n][z].setBottom(null);
				graph[n][z].setRight(null);
				graph[n][z].setLeft(graph[n][z - 1].getRight());
			}
			else
			{
				int weight = sc.nextInt();
				Edge edge = new Edge(weight, n, z, n, z + 1, "|");
				Entry entry = new Entry(weight, edge);
				heap.insert(entry);
				graph[n][z].setBottom(null);
				graph[n][z].setRight(edge);
				//System.out.println(array[n][z].getRight().getWeight());
				graph[n][z].setLeft(graph[n][z - 1].getRight());
			}
		}

		System.out.println();
		PrimJarnik primAlgo = new PrimJarnik(graph, heap, vertices, edges);

		//GRID OUTPUT HERE
		System.out.println();
		System.out.println("Here shows that the MazeConstruct prints out the nxn grid with its start and end borders removed");
		System.out.println("Please not that '--' means a single horizontal bar and '|' is a vertical bar. I '--' spaced the cells better.");
		//Top Border
		for(int row = 0; row < size; row++)
		{
			System.out.print(" -- ");
		}
		System.out.println();

		for(int r = 0; r < size; r++)
		{
			//Left border
			if(r == startR && startC == 0)
			{
				System.out.print(" ");
			}
			else
			{
				System.out.print("|");
			}
			for(int c = 0; c < size; c++)
			{
				if(c == n)
				{
					System.out.print(graph[r][c].getData());
				}
				else
				{
					System.out.print(graph[r][c].getData());
					System.out.print(graph[r][c].getRight().getData());
				}
			}
			if(r == endR && n == endC)
			{
				System.out.print(" ");
			}
			else
			{
				System.out.print("|");
			}
			System.out.println();
			for(int c = 0; c < size; c++)
			{
				if(r == n)
				{
				}
				else
				{
					System.out.print(graph[r][c].getBottom().getData());
				}
			}
			if(r != n)
			{
				System.out.println();
			}
		}

		//Bottom Border
		for(int row = 0; row < size; row++)
		{
			System.out.print(" -- ");
		}
		System.out.println();
		//System.out.println("Heap Size: " + heap.getSize());
	}
}