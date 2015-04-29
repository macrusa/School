import java.util.Scanner;
public class Balloon
{
	public static void main(String[]args)
	{
		Scanner user = new Scanner(System.in);
		double start = 0;
		System.out.print("Please enter the start time: ");
		start = user.nextDouble();
		double end = 0;
		System.out.print("Please enter the end time: ");
		end = user.nextDouble();
		double interval = 0;
		System.out.print("Please enter the sample interval: ");
		interval = user.nextDouble();
		for (double t=start;t<=end;t=t+interval)
		{
			double alt = altitude(t);
			double vel = velocity(t)/3600;
			System.out.println(t + "\t" + alt + "\t" + vel);

		}
	}
	public static double altitude(double t)
	{
		double alt = 0;
		alt = -0.12*Math.pow(t,4) + 12*Math.pow(t,3) - 380*Math.pow(t,2) + 4100*t + 220;
		return alt;
	}
	public static double velocity(double t)
	{
		double vel = 0;
		vel = -0.48*Math.pow(t,3) + 36*Math.pow(t,2) - 760*t + 4100;
		return vel;
	}

}

