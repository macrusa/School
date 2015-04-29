
public class Edge
{
	int _weight;
	int _row;
	int _column;
	int _nextRow;
	int _nextColumn;
	String _data;
	//There is a wall
	boolean _null = true;
	
	public Edge(int weight, int row, int column, int nextRow, int nextColumn, String data)
	{
		this._weight = weight;
		this._row = row;
		this._column = column;
		this._nextRow = nextRow;
		this._nextColumn = nextColumn;
		this._data = data;
	}

	public int getWeight()
	{
		return _weight;
	}
	public int getRow()
	{
		return _row;
	}
	public int getColumn()
	{
		return _column;
	}
	public int getNextRow()
	{
		return _nextRow;
	}
	public int getNextColumn()
	{
		return _nextColumn;
	}
	public String getData()
	{
		return _data;
	}
	public boolean getNull()
	{
		return _null;
	}

	public void setWeight(int weight)
	{
		_weight = weight;
	}
	public void setRow(int row)
	{
		_row = row;
	}
	public void setColumn(int column)
	{
		_column = column;
	}
	public void setNextRow(int nextRow)
	{
		_nextRow = nextRow;
	}
	public void setNextColumn(int nextColumn)
	{
		_nextColumn = nextColumn;
	}
	public void setData(String data)
	{
		_data = data;
	}
	public void setNull()
	{
		_null = false;
	}

	public void insertEdge(int row, int column, int nextRow, int nextColumn)
	{
		if((nextRow == (row - 1)) || (nextRow == (row + 1)))
		{
			_data =  "| ";
		}
		else
		{
			_data = "-- ";
		}
	}
}
