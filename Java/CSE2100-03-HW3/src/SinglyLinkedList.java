// Assignment #3 Summation Puzzles
// CSE 2100 Fall 2014
// Nancy Cheng
// 10-9-14

public class SinglyLinkedList<E>
{
	private Node<E> _head;
	private Node<E> _end;
	private int _size;

	public SinglyLinkedList()
	{
		_head = null;
		_end = null;
		_size = 0;
	}

	public boolean isEmpty()
	{
		return _size == 0;
	}

	public void setHead(Node<E> newHead)
	{
		_head = newHead;
	}
	public Node<E> getHead()
	{
		return _head;
	}

	public void setEnd(Node<E> newEnd)
	{
		_end = newEnd;
	}
	public Node<E> getEnd()
	{
		return _end;
	}

	public int getSize()
	{
		return _size;
	}

	public void insertNode(E value)
	{
		Node<E> newNode = new Node<E>(value);
		if(isEmpty())
		{
			_head = newNode;
			newNode.setNext(_end);
			_end = newNode;
		}
		else
		{
			_end.setNext(newNode);
			_end = newNode;
			newNode.setNext(_head);
		}
		_size++;
	}

	public void removeFirst()
	{
		if(isEmpty())
		{
			System.out.println("You have no more nodes...");
		}
		else
		{
			_head = _head.getNext();
			_end.setNext(_head);
		}
		_size--;
	}
	
	public void addFirst(Node<E> next)
	{
		if(isEmpty())
		{
			_head = next;
			next.setNext(_end);
			_end = next;
		}
		else
		{
			next.setNext(_head);
			_head = next;
		}
		_size++;
	}

	public void addLast(Node<E> next)
	{
		if(isEmpty())
		{
			_head = next;
			next.setNext(_end);
			_end = next;
		}
		else
		{
			_end.setNext(next);
			_end = next;
		}
		_size++;

	}
}