
public class Solver
{
	public Solver(Vertex[][] graph, int size, int startR, int startC, int endR, int endC)
	{
		Vertex location = graph[startR][startC];
		location.setData(" * ");
		Vertex potentialMove;
		System.out.println(startR + " " + startC);

		int n = 0;
		do
		{
			if(location.hasTop())
			{
				potentialMove = graph[location.getRow() - 1][location.getColumn()];
				System.out.println(potentialMove.getRow() + " " + potentialMove.getColumn());

				location = potentialMove;
				location.setData(" * ");
			}
			else if(location.hasRight())
			{
				potentialMove = graph[location.getRow()][location.getColumn() + 1];
				System.out.println(potentialMove.getRow() + " " + potentialMove.getColumn());
				location = potentialMove;
				location.setData(" * ");
			}
			else if(location.hasBottom())
			{
				potentialMove = graph[location.getRow() + 1][location.getColumn()];
				System.out.println(potentialMove.getRow() + " " + potentialMove.getColumn());
				location = potentialMove;
				location.setData(" * ");
			}
			else if(location.hasLeft())
			{
				potentialMove = graph[location.getRow()][location.getColumn() - 1];
				System.out.println(potentialMove.getRow() + " " + potentialMove.getColumn());
				location = potentialMove;
				location.setData(" * ");
			}
			else
			{
				location.setData("  ");
			}
			//n++;
			//System.out.println("n: " + n);
		}
		while((location.getRow() != endR) && (location.getColumn() != endC));

		location.setData(" * ");
		graph[endR][endC].setData(" * ");
		System.out.println(endR + " " + endC);
	}
}