// Assignment #2 All Nearest Smaller Value Problem (ANSVP)
// CSE 2100 Fall 2014
// Nancy Cheng
// 9-25-14

public class Stack<E>
{
	private int _size = 0;
	private Node<E> _top;
	private Node<E> _removed;

	public Stack()
	{
		_size = 0;
		_top = null;
	}

	public int getSize()
	{
		return _size;
	}

	public Node<E> getTop()
	{
		return _top;
	}
	public void setTop(Node<E> top)
	{
		_top = top;
	}

	public Node<E> getRemoved()
	{
		return _removed;
	}

	public boolean isEmpty()
	{
		return _top == null;
	}

	public void push(E number)
	{
		Node<E> newNode = new Node<E>(number);
		if(isEmpty())
		{
			_top = newNode;
			newNode.setBelow(null);
		}
		else
		{
			newNode.setBelow(_top);
			_top = newNode;
		}
		_size++;
	}

	public void pop()
	{
		if(isEmpty())
		{
			System.out.println("There are no elements in your stack...");
		}
		else
		{
			_removed = _top;
			_top = _top.getBelow();
		}
		_size--;
	}
}
