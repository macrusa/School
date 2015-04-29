
public class Vertex
{
	int _row;
	int _column;
	String _data;
	Edge _top;
	Edge _bottom;
	Edge _right;
	Edge _left;
	boolean _null;

	public Vertex(String data, int row, int column)
	{
		_top = null;
		_bottom = null;
		_right = null;
		_left = null;
		_data = data;
		_null = true;
		_row = row;
		_column = column;
	}

	public int getRow()
	{
		return _row;
	}
	public int getColumn()
	{
		return _column;
	}
	public String getData()
	{
		return _data;
	}
	public Edge getTop()
	{
		return _top;
	}
	public Edge getBottom()
	{
		return _bottom;
	}
	public Edge getRight()
	{
		return _right;
	}
	public Edge getLeft()
	{
		return _left;
	}

	public void setRow(int row)
	{
		_row = row;
	}
	public void setColumn(int column)
	{
		_column = column;
	}
	public void setData(String data)
	{
		_data = data;
	}
	public void setTop(Edge top)
	{
		_top = top;
	}
	public void setBottom(Edge bottom)
	{
		_bottom = bottom;
	}
	public void setLeft(Edge left)
	{
		_left = left;
	}
	public void setRight(Edge right)
	{
		_right = right;
	}

	public boolean hasTop()
	{
		if(_top != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean hasBottom()
	{
		if(_bottom != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean hasLeft()
	{
		if(_left != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean hasRight()
	{
		if(_right != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean getNull()
	{
		return _null;
	}

	public void setNull()
	{
		_null = false;
	}
}