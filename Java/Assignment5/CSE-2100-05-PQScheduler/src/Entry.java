public class Entry
{
	private int _key;
	private CPUJob _value;
	
	public Entry(int key, CPUJob value)
	{
		_key = key;
		_value = value;
	}
	
	public int getKey()
	{
		return _key;
	}
	
	public Object getValue()
	{
		return _value;
	}
	
}
