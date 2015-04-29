import java.util.Scanner;

import javax.swing.JOptionPane;
public class Test
{
	public static void main(String[] args)
	{
		//Scanner sc = new Scanner(System.in);
		//int i = sc.nextInt();
		//System.out.println("Love this is " + i);
		String name = JOptionPane.showInputDialog("Enter your username:");
		Users Macrusa = new Users("Macrusa", "Mickety Mac", "Admin");
		Users Laludia = new Users("Laludia", "Lalu", "President");
	}
}
