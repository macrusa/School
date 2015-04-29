// Assignment #3 Summation Puzzles
// CSE 2100 Fall 2014
// Nancy Cheng
// 10-9-14

public class Node<E>
{
	private Node<E> _next;
	private E _data;
	
	public Node()
	{
		_next = null;
	}
	
	public Node(E data)
	{
		_data = data;
	}
	
	public E getData()
	{
		return _data;
	}
	
	public void setNext(Node<E> next)
	{
		_next = next;
	}
	public Node<E> getNext()
	{
		return _next;
	}
}