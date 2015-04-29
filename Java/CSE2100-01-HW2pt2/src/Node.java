// Assignment #2 All Nearest Smaller Value Problem (ANSVP) Analysis
// CSE 2100 Fall 2014
// Nancy Cheng
// 9-25-14

public class Node<E>
{
	private E _number;
	private Node<E> _below;

	public Node(E number)
	{
		this._number = number;
	}

	public E getNumber()
	{
		return _number;
	}

	public void setNumber(E number)
	{
		this._number = number;
	}

	public Node<E> getBelow()
	{
		return _below;
	}
	public void setBelow(Node<E> bottom)
	{
		_below = bottom;
	}
}
