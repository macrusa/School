//Alicia Affinito
//CSE 2100
//Lab Assignment 5

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PQScheduler 
{
	private HeapPriorityQueue _schedulerPQ = new HeapPriorityQueue();

	private boolean readJobInput(BufferedReader reader) throws Exception
	{
		try{
			String noNewJob = "no new job this slice";
			String jobNameBreak = "add job ";
			String lengthBreak = " with length ";
			String priorityBreak = " and priority ";
			String input = reader.readLine();
			if(input == null)
			{
				return false;
			}
			input = input.trim();
			if(!input.equals(noNewJob))
			{
				int jobLengthBeginPos = input.indexOf(lengthBreak) + lengthBreak.length();
				int jobPriorityBeginPos = input.indexOf(priorityBreak) + priorityBreak.length();
				String jobName = input.substring(jobNameBreak.length(), input.indexOf(lengthBreak));
				int length = Integer.parseInt(input.substring(jobLengthBeginPos, input.indexOf(priorityBreak)));
				int priority = Integer.parseInt(input.substring(jobPriorityBeginPos));
				CPUJob job = new CPUJob(jobName, length, priority);
				_schedulerPQ.insert(new Entry(priority, job));
			}
		}
		catch(StringIndexOutOfBoundsException s){
			System.out.println("The string input you entered has an error. Please try again.");
		}
		return true;
	}

	public void runScheduler() throws Exception
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			if(!readJobInput(input))
			{
				break;
			}
			else if(_schedulerPQ.isEmpty())
			{
				System.out.println();
			}
			else
			{
				CPUJob job = (CPUJob) _schedulerPQ.min().getValue();
				job.doJob();
				System.out.println(job.getName());
				if(job.jobDone()){
					_schedulerPQ.removeMin();
				}
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("Enter your jobs:");
		PQScheduler scheduler = new PQScheduler();
		scheduler.runScheduler();
	}

}
