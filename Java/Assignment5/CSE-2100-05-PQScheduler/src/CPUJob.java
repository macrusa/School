public class CPUJob 
{
	private String _jobName;
	private int _priority;
	private int _length; 
	
	public CPUJob(String name, int length, int priority)
	{
		_jobName = name;
		_priority = priority;
		_length = length;
	}
	
	public String getName()
	{
		return _jobName;
	}
	
	public int getLength()
	{
		return _length;
	}
	
	public int getPriority()
	{
		return _priority;
	}
	
	public void doJob()
	{
		_length--;
	}

	public boolean jobDone()
	{
		return _length == 0;
	}

}
