public class HeapSort
{
	private int[] _sequence;
	private int _index;
	private int _size;
	private final int _root = 0;

	public HeapSort()
	{
		_index = 0;
		_sequence = new int[0];
		_size = _sequence.length;
	}

	public boolean isEmpty()
	{
		return _index == 0;
	}

	public int parent(int position)
	{
		return position / 2;
	}

	public int leftChild(int position)
	{
		return (2 * position);
	}

	public int rightChild(int position)
	{
		return (2 * position) + 1;
	}

	public boolean isLeaf(int position)
	{
		if(position >= (_index / 2) && position <= _index)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void swap(int pos1, int pos2)
	{
		int temp;
		temp = _sequence[pos1];
		_sequence[pos1] = _sequence[pos2];
		_sequence[pos2] = temp;
	}

	public void bubble(int position)
	{
		if(!isLeaf(position))
		{
			if(_sequence[position] > _sequence[leftChild(position)] || _sequence[position] > _sequence[rightChild(position)])
			{
				if(_sequence[leftChild(position)] < _sequence[rightChild(position)])
				{
					swap(position, leftChild(position));
					bubble(leftChild(position));
				}
				else
				{
					swap(position, rightChild(position));
					bubble(rightChild(position));
				}
			}
		}
	}

	public void insert(int element)
	{
		_index = _index + 1;
		_size = _size + 1;
		int[] newSequence = new int[_sequence.length + 1];
		for(int i = 0; i < _sequence.length; i++)
		{
			newSequence[i] = _sequence[i];
		}
		_sequence = newSequence;
		//System.out.println(_sequence.length);
		_sequence[_index - 1] = element;
		//System.out.println("Index: " + _index);
		//System.out.println(_sequence[_index]);
		int current = _index - 1;

		while(_sequence[current] < _sequence[parent(current)])
		{
			swap(current, parent(current));
			current = parent(current);
		}
		//System.out.println(_sequence[_root]);
	}

	public void print()
	{
		for(int i = 1; i <= _index / 2; i++)
		{
			System.out.println("Parent: " + _sequence[i]);
			System.out.println("Left Child: " + _sequence[2 * i]);
			System.out.println("Right Child: " + _sequence[2 * i + 1]);
			System.out.println();
		}
	}

	public void minHeap()
	{
		for(int position = (_index / 2); position >= 1; position--)
		{
			bubble(position);
		}
	}

	public int getRoot()
	{
		return _sequence[0];
	}

	public void removeRoot()
	{
		if(isEmpty())
		{
			System.out.println("Your tree is empty");
		}
		else
		{
			int last = _sequence.length - 1;
			_sequence[_root] = _sequence[last];
			bubble(_root + 1);
			int[] newSequence = new int[_sequence.length - 1];
			for(int i = 0; i < _sequence.length - 1; i++)
			{
				newSequence[i] = _sequence[i + 1];
			}
			_sequence = newSequence;
			//System.out.println(newSequence[1]);
			_index--;
		}
		/**for(int z = 0; z < _sequence.length; z++)
		{
			System.out.println("Sequence: " + _sequence[z]);
		}**/
	}
}
