
public class LinkedList 
{
	private Node _start;
	private Node _end;
	private int _capital = 0;
	private int _size = 0;

	//Start with empty Linked List
	public LinkedList()
	{
		_start = null;
		_end = null;
		_size = 0;
	}
	public Node getStart()
	{
		return _start;
	}
	public void setStart(Node start)
	{
		_start = start;
	}

	public Node getEnd()
	{
		return _end;
	}
	public void setEnd(Node end)
	{
		_end = end;
	}

	public int getCapital()
	{
		return _capital;
	}

	public int getSize()
	{
		return _size;
	}

	public boolean isEmpty()
	{
		return _start == null;
	}

	//enqueue - add node to end
	public void insertNode(int shares, int cost)
	{
		Node newNode = new Node(shares, cost);
		newNode.setNext(_start);

		if(isEmpty())
		{
			//works
			_start = newNode;
			newNode.setNext(_start);
			_end = newNode;
		}

		else
		{
			//works
			_end.setNext(newNode);
			_end = newNode;
			newNode.setNext(_start);
		}
		_size++;
		/**TEST
		System.out.println("Node: " + _size);
		System.out.println("Start shares: " + _start.getShares());
		System.out.println("Start cost: " + _start.getCost());
		System.out.println("Shares: " + newNode.getShares());
		System.out.println("Cost: " + newNode.getCost());
		 **/
	}

	//dequeue - remove node form front
	public void deleteNode(int shares, int cost)
	{
		if(_size == 0)
		{
			System.out.println("You are out of stocks...");
			shares = 0;
		}
		else
		{
			while(shares > 0)
			{
				System.out.println("Current shares: " + shares);	
				if(shares == _start.getShares())
				{	
					_capital = _capital + (shares * (cost - _start.getCost()));
					shares = shares - _start.getShares();
					//System.out.println("Shares left to nodes " + shares);
					_start = _start.getNext();
					_end.setNext(_start);
					_size--;
				}

				else if(shares > _start.getShares())
				{
					if(_size == 1)
					{
						System.out.println("You don't have enough shares for this transaction...");
						shares = 0;
					}
					else
					{
						_capital = _capital + (_start.getShares() * (cost - _start.getCost()));
						shares = shares - _start.getShares();
						//System.out.println("Shares left to nodes " + shares);
						_start = _start.getNext();
						_end.setNext(_start);
						_size--;
					}
				}


				else if(shares < _start.getShares())
				{
					_capital = _capital + (shares * (cost - _start.getCost()));
					_start.setShares(_start.getShares() - shares);
					//System.out.println("Start shares: " + _start.getShares());	
					shares = 0;
				}

				else
				{
				}

				//TEST
				//System.out.println("Capital: " + _capital);
				//System.out.println("Size: " + _size);
			}
		}
	}
}

