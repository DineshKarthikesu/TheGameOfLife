/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     The Game of Life – Mindvalley Challenge
**  Class        GridManager
*/

import java.util.Arrays;

public class GridManager {
	public static Cell[][] cellGrid;	//store current generation
	public static Cell[][] newCellGrid; //store new generation
	public static int numberOfRows;
	public static int numberOfColumns;
	public static int numberOfLivingCells = 0;
	public static int numberOfGenerations = 0;

	public static void createNewGrid (int rows, int columns) {
		cellGrid = new Cell[rows][columns];
		newCellGrid = new Cell[rows][columns];

		numberOfRows = rows;
		numberOfColumns = columns;
	}

	public static void initialiseGrid () {
		Cell newCell;

		for (int row = 0; row < numberOfRows; row++) {
			for (int column = 0; column < numberOfColumns; column++) {
				newCell = new Cell();
				newCell.setAlive (false);
				cellGrid[row][column] = newCell;
 			}
		}

		resetNewGrid ();
	}

	public static void activateCell (int row, int column) {
		if (cellGrid[row][column].isCellAlive () == false) {
			cellGrid[row][column].setAlive (true);
			numberOfLivingCells++;
			System.out.printf ("\nNumber of Living Cells: %d", numberOfLivingCells);
		}

		else {
			cellGrid[row][column].setAlive (false);
			numberOfLivingCells--;
			System.out.printf ("\nNumber of Living Cells: %d", numberOfLivingCells);
		}
	}

	public static void printGrid () {
		System.out.printf ("\n  ");
		for (int counter = 0; counter < numberOfRows; counter++) {
			System.out.printf ("%d ", counter);
		}

		for (int row = 0; row < numberOfRows; row++) {
			System.out.printf ("\n%d", row);
			for (int column = 0; column < numberOfColumns; column++) {
				if (cellGrid[row][column].isCellAlive ()) {
					System.out.printf (" x");
				}

				else {
					System.out.printf (" -");
				}
			}
		}
	}

	public static int neighboursAlive (int row, int column) {
		int numberOfLivingNeighbours = 0;

		/* Top - only if the row isn't the first row */
		if (row != 0) {
			if (cellGrid[row - 1][column].isCellAlive ()) {
				numberOfLivingNeighbours++;
				//System.out.printf ("\nTOP");
			}
		}

		/* Bottom - only if the row isn't the last row */
		if (row != numberOfRows - 1) {
			if (cellGrid[row + 1][column].isCellAlive ()) {
				numberOfLivingNeighbours++;
				//System.out.printf ("\nBOTTOM");
			}
		}

		/* Left - only if the column isn't the first column */
		if (column != 0) {
			if (cellGrid[row][column - 1].isCellAlive ()) {
				numberOfLivingNeighbours++;
				//System.out.printf ("\nLEFT");
			}
		}

		/* Right - only if the column isn't the last column */
		if (column != numberOfColumns - 1) {
			if (cellGrid[row][column + 1].isCellAlive ()) {
				numberOfLivingNeighbours++;
				//System.out.printf ("\nRIGHT");
			}
		}

		/* Top Left - only if row isn't the first row && column isn't the first column */
		if (row != 0 && column != 0) {
			if (cellGrid[row - 1][column - 1].isCellAlive ()) {
				numberOfLivingNeighbours++;
				//System.out.printf ("\nTOP LEFT");
			}
		}

		/* Top Right - only if row isn't the first row && column isn't the last column */
		if (row != 0 && column != numberOfColumns - 1) {
			if (cellGrid[row - 1][column + 1].isCellAlive ()) {
				numberOfLivingNeighbours++;
				//System.out.printf ("\nTOP RIGHT");
			}
		}

		/* Bottom Left - only if the row isn't the last row && the column isn't the first column */
		if (row != numberOfRows - 1 && column != 0) {
			if (cellGrid[row + 1][column - 1].isCellAlive ()) {
				numberOfLivingNeighbours++;
				//System.out.printf ("\nBOTTOM LEFT");
			}
		}

		/* Bottom Right - only if the row isn't the last row && the column isn't the last column */
		if (row != numberOfRows - 1 && column != numberOfColumns - 1) {
			if (cellGrid[row + 1][column + 1].isCellAlive ()) {
				numberOfLivingNeighbours++;
				//System.out.printf ("\nBOTTOM RIGHT");
			}
		}

		return numberOfLivingNeighbours;
	}

	public static void resetNewGrid () {
		Cell newCell;

		for (int row = 0; row < numberOfRows; row++) {
			for (int column = 0; column < numberOfColumns; column++) {
				newCell = new Cell();
				newCell.setAlive (false);
				newCellGrid[row][column] = newCell;
 			}
		}
	}

	public static void resetGrid () {
		Cell newCell;

		for (int row = 0; row < numberOfRows; row++) {
			for (int column = 0; column < numberOfColumns; column++) {
				newCell = new Cell();
				newCell.setAlive (false);
				cellGrid[row][column] = newCell;
 			}
		}

		numberOfLivingCells = 0;
		numberOfGenerations = 0;
	}

	public static void scanGrid () {
		/* Scan the entire grid to see which cell should live on */

		boolean cellAlive;
		boolean liveOn;
		int numberOfLivingNeighbours = 0;

		for (int row = 0; row < numberOfRows; row ++) {
			for (int column = 0; column < numberOfColumns; column++) {
				/* Check the rules */
				cellAlive = cellGrid[row][column].isCellAlive ();
				numberOfLivingNeighbours = neighboursAlive (row, column);
				liveOn = false;

				/* Underpopulation Rule */
				if (cellAlive && numberOfLivingNeighbours < 2) {
					liveOn = false;
				}

				/* Normal Population Rule */
				if (cellAlive && (numberOfLivingNeighbours == 2 || numberOfLivingNeighbours == 3)) {
					liveOn = true;
				}

				/* Overcrowding Rule */
				if (cellAlive && numberOfLivingNeighbours > 3) {
					liveOn = false;
				}

				/* Reproduction Rule */
				if (cellAlive == false && numberOfLivingNeighbours == 3) {
					liveOn = true;
				}

				/* Set the state of the corresponding cell in the new 2D array */
				newCellGrid[row][column].setAlive (liveOn);	

				if (liveOn) {
					System.out.printf ("\n[%d][%d] lives on", row, column);
				}
			}
		}

		/* Copy the elements from the new array to the original array */
		for (int row = 0; row < numberOfRows; row++) {
			for (int column = 0; column < numberOfColumns; column++) {
				cellGrid[row][column] = newCellGrid[row][column];
			}
		} 

		/* Clear the contents of the new array */
		resetNewGrid ();

		if (getNumberOfLivingCells () > 0) {
			numberOfGenerations++;
		}
	}

	public static Cell[][] getCellGrid () {
		return cellGrid;
	}

	public static int getNumberOfRows () {
		return numberOfRows;
	}

	public static int getNumberOfColumns () {
		return numberOfColumns;
	}

	public static int getNumberOfLivingCells () {
		numberOfLivingCells = 0;

		for (int row = 0; row < numberOfRows; row++) {
			for (int column = 0; column < numberOfColumns; column++) {
				if (cellGrid[row][column].isCellAlive ()) {
					numberOfLivingCells++;
				}
			}
		} 

		return numberOfLivingCells;
	}
}