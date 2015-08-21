/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     The Game of Life – Mindvalley Challenge
**  Class        Point
*/
import java.io.Serializable;

public class Point implements Serializable {
	private int row;
	private int column;

	public Point (int row, int column) {
		setRow (row);
		setColumn (column);
	}

	/* Getters and Setters */
	public void setRow (int row) {
		this.row = row;
	}

	public void setColumn (int column) {
		this.column = column;
	}

	public int getRow () {
		return this.row;
	}

	public int getColumn () {
		return this.column;
	}
}