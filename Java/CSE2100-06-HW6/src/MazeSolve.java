import java.util.Scanner;
//Note to self: PUSH ENTER twice (to space it nicer)
/**
4 1 0 2 3
0 0 0 1
0 0 1 0
0 1 0 2
0 1 1 1
0 2 0 3
0 2 1 2
0 3 1 3
1 0 2 0
1 1 2 1
1 2 2 2
1 3 2 3
2 0 3 0
2 1 3 1
2 2 3 2
2 3 3 3
 **/

public class MazeSolve
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		//System.out.println("Input: " + input);
		String[] parts = input.split("\\s+");
		int size = Integer.parseInt(parts[0]);
		int startR = Integer.parseInt(parts[1]);
		int startC = Integer.parseInt(parts[2]);
		int endR = Integer.parseInt(parts[3]);
		int endC = Integer.parseInt(parts[4]);

		int weight = 1;
		int n = size - 1;
		int vertices = size * size;
		int edges = vertices - 1;
		boolean[][] wasHere = new boolean[size][size];
		boolean[][] correctPath = new boolean[size][size];

		//Make all vertices
		Vertex[][] graph = new Vertex[size][size];
		for(int r = 0; r < size; r++)
		{
			for(int c = 0; c < size; c++)
			{
				graph[r][c] = new Vertex("   ", r, c);
			}
		}

		//Initial Row (Row 0)
		for(int i = 0; i < size; i++)
		{
			if(i == 0)
			{
				Edge edge = new Edge(weight, 0, i, 0, i + 1, "|");
				Edge down = new Edge(weight, 0, i, 1, i, " -- ");
				graph[0][i].setTop(null);
				graph[0][i].setRight(edge);
				graph[0][i].setLeft(null);
				graph[0][i].setBottom(down);
			}
			else if(i == n)
			{
				Edge down = new Edge(weight, 0, i, 1, i, " -- ");
				graph[0][i].setTop(null);
				graph[0][i].setRight(null);
				graph[0][i].setLeft(graph[0][i - 1].getRight());
				graph[0][i].setBottom(down);
			}
			else
			{
				Edge edge = new Edge(weight, 0, i, 0, i + 1, "|");
				graph[0][i].setTop(null);
				graph[0][i].setRight(edge);
				graph[0][i].setLeft(graph[0][i - 1].getRight());
				Edge down = new Edge(weight, 0, i, 1, i, " -- ");
				graph[0][i].setBottom(down);
			}
		}
		//Row 1 and after index to size (vertices)
		for(int r = 1; r < n; r++)
		{
			for(int c = 0; c < size; c++)
			{
				Edge edge = new Edge(weight, r, c, r - 1, c, " -- ");
				Edge down = new Edge(weight, r, c, r + 1, c, " -- ");
				if(c == 0)
				{
					graph[r][c].setTop(edge);
					graph[r][c].setBottom(down);
					graph[r][c].setLeft(null);
				}
				else if(c == n)
				{
					graph[r][c].setBottom(down);
					graph[r][c].setTop(edge);
					graph[r - 1][c].setBottom(graph[r][c].getTop());
				}
				else
				{
					graph[r][c].setTop(edge);
					graph[r - 1][c].setBottom(graph[r][c].getTop());
				}
			}
			for(int c = 0; c < size; c++)
			{
				if(c == 0)
				{
					Edge edge = new Edge(weight, r, c, r, c + 1, "|");
					graph[r][c].setRight(edge);
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
					Edge edge = new Edge(weight, r, c, r, c + 1, "|");
					graph[r][c].setRight(edge);
					graph[r][c].setLeft(graph[r][c - 1].getRight());
				}
			}
		}

		//Last Column and Row (nth row)
		//For | | | in nth row
		for(int c = 0; c < size; c++)
		{
			Edge edge = new Edge(weight, n, c, n - 1, c, " -- ");
			graph[n][c].setTop(edge);
			graph[n - 1][c].setBottom(graph[n][c].getTop());
		}

		for(int z = 0; z < size; z++)
		{
			if(z == 0)
			{
				Edge edge = new Edge(weight, n, z, n, z + 1, "|");
				graph[n][z].setBottom(null);
				graph[n][z].setRight(edge);
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
				Edge edge = new Edge(weight, n, z, n, z + 1, "|");
				graph[n][z].setBottom(null);
				graph[n][z].setRight(edge);
				graph[n][z].setLeft(graph[n][z - 1].getRight());
			}
		}

		String coorInput = sc.nextLine();
		String[] parts2 = coorInput.split("\\s+");
		int row = Integer.parseInt(parts2[0]);
		int column = Integer.parseInt(parts2[1]);
		int nextRow = Integer.parseInt(parts2[2]);
		int nextColumn = Integer.parseInt(parts2[3]);

		for(int a = 0; a < size; a++)
		{
			for(int b = 0; b < size; b++)
			{
				//System.out.println("(" + a + ", " + b + ")");
				//System.out.println(row + " " + column + " " + nextRow + " " + nextColumn);
				//System.out.println("Input: " + coorInput);
				Vertex vertex = graph[a][b];

				if(vertex.hasRight())
				{
					Edge right = vertex.getRight();
					int rightR = right.getRow();
					int rightC = right.getColumn();
					int rightNR = right.getNextRow();
					int rightNC = right.getNextColumn();
					if((row == rightR && column == rightC && nextRow == rightNR && nextColumn == rightNC)
							|| (row == rightNR && column == rightNC && nextRow == rightR && nextColumn == rightC))
					{
						right.setData(" ");
						right.setNull();

						coorInput = sc.nextLine();
						parts2 = coorInput.split("\\s+");
						if(parts2.length > 3)
						{
							row = Integer.parseInt(parts2[0]);
							column = Integer.parseInt(parts2[1]);
							nextRow = Integer.parseInt(parts2[2]);
							nextColumn = Integer.parseInt(parts2[3]);
						}
					}
				}

				if(vertex.hasBottom())
				{
					Edge bottom = vertex.getBottom();
					int bottomR = bottom.getRow();
					int bottomNR = bottom.getNextRow();
					int bottomC = bottom.getColumn();
					int bottomNC = bottom.getNextColumn();
					if((row == bottomR && column == bottomC && nextRow == bottomNR && nextColumn == bottomNC)
							|| (row == bottomNR && column == bottomNC && nextRow == bottomR && nextColumn == bottomC))
					{
						bottom.setData(" ");
						bottom.setNull();

						coorInput = sc.nextLine();
						parts2 = coorInput.split("\\s+");
						if(parts2.length > 3)
						{
							row = Integer.parseInt(parts2[0]);
							column = Integer.parseInt(parts2[1]);
							nextRow = Integer.parseInt(parts2[2]);
							nextColumn = Integer.parseInt(parts2[3]);
						}
					}
				}
			}
		}


		//SHORTEST PATH ALGORITHM HERE
		Solver solver = new Solver(graph, size, startR, startC, endR, endC);

		//GRID OUTPUT HERE
		System.out.println("Here shows that the MazeConstruct prints out the nxn grid with its start and end borders removed");
		System.out.println("Please not that '--' means a single horizontal bar and '|' is a vertical bar. I '--' spaced the cells better.");
		//Top Border
		for(int g = 0; g < size; g++)
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
		for(int y = 0; y < size; y++)
		{
			System.out.print(" -- ");
		}
		System.out.println();
		//System.out.println("Heap Size: " + heap.getSize());
	}
	
}