/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     The Game of Life – Mindvalley Challenge
**  Class        Cell
*/

import java.util.ArrayList;

public class Cell {
	private int cellSize;	//drawing
	private boolean alive;	//state of the cell
	private int numberOfLivingNeighbours;

	/* Getters and Setters */

	public void setCellSize (int cellSize) {
		this.cellSize = cellSize;
	}

	public void setAlive (boolean state) {
		this.alive = state;
	}

	public void setNumberOfLivingNeighbours (int numberOfLivingNeighbours) {
		this.numberOfLivingNeighbours = numberOfLivingNeighbours;
	}
	
	public int getCellSize () {
		return this.cellSize;
	}

	public boolean isCellAlive () {
		return this.alive;
	}

	public int getNumberOfLivingNeighbours () {
		return this.numberOfLivingNeighbours;
	}
}
