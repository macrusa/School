// Assignment #2 All Nearest Smaller Value Problem (ANSVP) Analysis
// CSE 2100 Fall 2014
// Nancy Cheng
// 9-25-14

public class SinglyLinkedList<E> 
{
	private Node<E> _start;
	private Node<E> _end;
	public int _size;

	public SinglyLinkedList()
	{
		_start = null;
		_end = null;
		_size = 0;
	}

	public boolean isEmpty()
	{
		return _size == 0;
	}

	public int getSize()
	{
		return _size;
	}

	public Node<E> getStart()
	{
		return _start;
	}
	public void setStart(Node<E> start)
	{
		_start = start;
	}

	public Node<E> getEnd()
	{
		return _end;
	}
	public void setEnd(Node<E> end)
	{
		_end = end;
	}

	public void insertNode(E number)
	{
		Node<E> newNode = new Node<E>(number);
		if (isEmpty())
		{
			_start = newNode;
			newNode.setBelow(_start);
			_end = newNode;
		}
		else
		{
			newNode.setBelow(_end);
			_end = newNode;
			_start.setBelow(_end);
		}
		_size++;
	}

	public void removeFirst()
	{
		if(_size <= 0)
		{
			System.out.println("Sorry you are out of items");
		}
		else if (_size > 0)
		{
			_start = _start.getBelow();
			_end.setBelow(_start);
			_size--;
		}
	}
}