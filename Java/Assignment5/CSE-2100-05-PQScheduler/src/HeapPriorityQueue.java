import java.util.Arrays;

public class HeapPriorityQueue 
{
	private Entry[] _pQueueArray = new Entry[10];
	private int _size;
	
	public int getSize()
	{
		return _size;
	}
	
	public boolean isEmpty()
	{
		return _size == 0;
	}
	
    public void insert(Entry e) 
    {
        if (_size >= _pQueueArray.length - 1) 
        {
            _pQueueArray = resize();
        }        
        _size++;
        int index = _size;
        _pQueueArray[index] = e;
        upHeap();
    }
    
    public Entry[] resize() 
    {
        return Arrays.copyOf(_pQueueArray, _pQueueArray.length * 2);
    }
    
    public Entry min() 
    {
        if(isEmpty()) 
        {
        	return null;
        }
        return _pQueueArray[1];
    }

    public Entry removeMin() 
    {
    	Entry min = min();
       	_pQueueArray[1] = _pQueueArray[_size];
    	_pQueueArray[_size] = null;
    	_size--;
    	downHeap();
    	return min;
    }
    
    public void swap(int i, int j)
    {
        Entry tempEntry = _pQueueArray[i];
        _pQueueArray[i] = _pQueueArray[j];
        _pQueueArray[j] = tempEntry;        
    }
    
    public boolean hasParent(int i)
    {
        return i > 1;
    }
    
    public int leftIndex(int i) 
    {
        return i * 2;
    }
    
    public int rightIndex(int i) 
    {
        return i * 2 + 1;
    }
    
    public boolean hasLeft(int i) 
    {
        return leftIndex(i) <= _size;
    }
    
    public boolean hasRight(int i)
    {
        return rightIndex(i) <= _size;
    }
    
    public Entry parent(int i)
    {
        return _pQueueArray[parentIndex(i)];
    }
    
    public int parentIndex(int i) 
    {
        return i / 2;
    } 

    public void downHeap() 
    {
        int index = 1;
        while (hasLeft(index)) 
        {
            int smallerChild = leftIndex(index);
            if (hasRight(index) && _pQueueArray[leftIndex(index)].getKey() > _pQueueArray[rightIndex(index)].getKey()) 
            {
            	smallerChild = rightIndex(index);
            } 
            if (_pQueueArray[index].getKey() > _pQueueArray[smallerChild].getKey()) 
            {
                swap(index, smallerChild);
            } 
            index = smallerChild;
        }        
    }
 
    public void upHeap() 
    {
        int index = _size;
        while (hasParent(index) && parent(index).getKey() > _pQueueArray[index].getKey()) 
        {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }        
    }
    
}

	
