
public class Node 
{
	private int _shares;
	private int _cost;
	private Node _next;

	public Node(int shares, int cost)
	{
		_shares = shares;
		_cost = cost;
	}

	public void setShares(int shares)
	{
		_shares = shares;
	}
	public int getShares()
	{
		return _shares;
	}

	public void setCost(int cost)
	{
		_cost = cost;
	}
	public int getCost()
	{
		return _cost;
	}
	
	public Node getNext()
	{
		return _next;
	}
	public void setNext(Node next)
	{
		_next = next;
	}
}
