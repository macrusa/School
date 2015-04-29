import javax.swing.JOptionPane;

public class Users
{
	private String[] _users;
	private String[] _passwords;
	private String[] _ranks;
	//users[0] = "Macrusa";
	//users[1] = "Laludia";
	private int count = 2;
	
	public Users(String name, String password, String rank)
	{
		_users[count] = name;
		_passwords[count] = password;
		_ranks[count] = rank;
		count++;
	}

	private void copy(String password, String name, String newUser)
	{
		name = JOptionPane.showInputDialog("Enter your username:");


	}

	public String logIn(String name, String password)
	{
		int x;
		for(x = 0; x < _users.length; x++)
		{
			if(_users[x] == name)
			{
			}
		}
		return ("Name authorized! Welcome " + _users[x]);
	}
}


