public class PrimJarnik
{
	int _size;
	public PrimJarnik(Vertex[][] graph, HeapPriorityQueue heap, int vertices, int edges)
	{
		while(heap.getSize() != 0)
		{
			int min = heap.min().getWeight();
			Edge minEdge = heap.min().getEdge();
			int row = minEdge.getRow();
			int column = minEdge.getColumn();
			int nextRow = minEdge.getNextRow();
			int nextColumn = minEdge.getNextColumn();

			//System.out.println("Min: " + minEdge.getWeight());
			//System.out.println("Heap size: " + heap.getSize());
			if(graph[row][column].getNull() == true || graph[nextRow][nextColumn].getNull() == true)
			{
				//Lol means not null
				minEdge.setData(" ");

				graph[row][column].setNull();
				graph[nextRow][nextColumn].setNull();
				minEdge.setNull();

				System.out.println(row + " " + column + " " + nextRow + " " + nextColumn);
				heap.removeMin();
			}
			else
			{
				//minEdge is null
				heap.removeMin();
			}
		}
	}
}