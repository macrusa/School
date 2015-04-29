public class Entry
{
	private int _weight;
	private Edge _edge;
	
	public Entry(int weight, Edge edge)
	{
		_weight = weight;
		_edge = edge;
	}
	
	public int getWeight()
	{
		return _weight;
	}
	
	public Edge getEdge()
	{
		return _edge;
	}	
}