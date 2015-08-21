/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     The Game of Life – Mindvalley Challenge
**  Class        Canvas
*/

/* Lightweight Swing Components */
import javax.swing.JPanel;

/* Heavyweight Components */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Canvas extends JPanel {
	Graphics2D g2d;
	Cell[][] cellGrid;

	int numberOfRows;
	int numberOfColumns;
	int offset = 30;
	int squareWidth = 13;
	int squareHeight = 13;
	int hoverRow;
	int hoverColumn;
	int boxPosX;
	int red   = 0;
	int green = 0;
	int blue  = 0;
	int themeColour;
	int patternCounter = 0;

	String colourBoxString;
	String patternName;
	String autoGeneratingText;
	boolean nextGenHover = false;
	boolean resetGridHover = false;
	boolean cellHover = false;
	boolean switchColourHover = false;
	boolean savePatternHover = false;
	boolean applyPatternHover = false;
	boolean autoHover = false;

	public Canvas () {
		setBackground (Color.BLACK);
		cellGrid = GridManager.getCellGrid ();
		numberOfRows = GridManager.getNumberOfRows ();
		numberOfColumns = GridManager.getNumberOfColumns ();
		System.out.printf ("Rows: %d\nColumns: %d", numberOfRows, numberOfColumns);
		setAutoGenerate (false);
	}

	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		g2d = (Graphics2D) g;

		for (int row = 0; row < numberOfRows; row++) {
			for (int column = 0; column < numberOfColumns; column++) {
				themeColour = 20 * GridManager.neighboursAlive (row, column) + 80;

				if (cellGrid[row][column].isCellAlive ()) {
					g2d.setColor (new Color (themeColour * red, themeColour * green, themeColour * blue));

					if (row == hoverRow && column == hoverColumn && cellHover) {
						if (cellGrid[row][column].isCellAlive ()) {
							g2d.setColor (new Color (200 * red, 200 * green, 200 * blue, 100));
						}
					}

					g2d.fill (new Rectangle2D.Double ((column * 15) + offset, (row * 15) + offset, squareWidth, squareHeight));

				}

				else if (row == hoverRow && column == hoverColumn && cellHover) {
					g2d.setColor (new Color (100 * red, 100 * green, 100 * blue));
					g2d.fill (new Rectangle2D.Double ((column * 15) + offset, (row * 15) + offset, squareWidth, squareHeight));
				}

				else {
					if (blue == 1) {
						g2d.setColor (new Color (0, 200, 100, 150));
					}

					else if (green == 1) {
						g2d.setColor (new Color (0, 200, 100, 50));
					}

					else if (red == 1) {
						g2d.setColor (new Color (255, 230, 140, 100));
					}

					g2d.fill (new Rectangle2D.Double ((column * 15) + offset, (row * 15) + offset, squareWidth, squareHeight));
				}
			}
		}

		/* Buttons */

		boxPosX = (numberOfColumns * 15) + offset + 20;

		g2d.setColor (Color.GREEN);
		g2d.draw (new Rectangle2D.Double (boxPosX, 30, 140, 30));

		if (nextGenHover) {
			g2d.setColor (new Color (0, 100, 0));
			g2d.draw (new Rectangle2D.Double (boxPosX, 30, 140, 30));
		}

		g2d.drawString ("Next Generation", boxPosX + 20, 50);

		g2d.setColor (Color.GREEN);
		g2d.draw (new Rectangle2D.Double (boxPosX, 80, 140, 30));

		if (resetGridHover) {
			g2d.setColor (new Color (0, 100, 0));
			g2d.draw (new Rectangle2D.Double (boxPosX, 80, 140, 30));
		}

		g2d.drawString ("Reset Grid", boxPosX + 35, 100);

		g2d.setColor (Color.GREEN);
		g2d.draw (new Rectangle2D.Double (boxPosX, 130, 140, 30));

		if (switchColourHover) {
			g2d.setColor (new Color (0, 100, 0));
			g2d.draw (new Rectangle2D.Double (boxPosX, 130, 140, 30));
		}

		g2d.drawString (colourBoxString, boxPosX + 20, 150);

		g2d.setColor (Color.GREEN);
		g2d.draw (new Rectangle2D.Double (boxPosX, 180, 140, 30));

		if (savePatternHover) {
			g2d.setColor (new Color (0, 100, 0));
			g2d.draw (new Rectangle2D.Double (boxPosX, 180, 140, 30));
		}

		g2d.drawString ("Save Pattern", boxPosX + 30, 200);

		g2d.setColor (Color.GREEN);
		g2d.draw (new Rectangle2D.Double (boxPosX, 230, 140, 30));

		if (applyPatternHover) {
			g2d.setColor (new Color (0, 100, 0));
			g2d.draw (new Rectangle2D.Double (boxPosX, 230, 140, 30));
		}

		g2d.drawString ("Apply Next Pattern", boxPosX + 13, 250);

		g2d.setColor (Color.GREEN);
		g2d.draw (new Rectangle2D.Double (boxPosX, 280, 140, 30));

		if (autoHover) {
			g2d.setColor (new Color (0, 100, 0));
			g2d.draw (new Rectangle2D.Double (boxPosX, 280, 140, 30));
		}

		g2d.drawString (autoGeneratingText, boxPosX + 20, 300);

		/* Number of Generations + Cells Alive */
		g2d.setColor (Color.GREEN);

		g2d.drawString ("Pattern " + patternCounter + " out of " + LifePatternManager.listOfPatterns.size (), boxPosX, (numberOfRows * 12) + 25);

		if (LifePatternManager.listOfPatterns.size () > 0 && patternCounter >= 1) {
			g2d.drawString ("Pattern Name: " + LifePatternManager.listOfPatterns.get (patternCounter - 1).getPatternName (), boxPosX, (numberOfRows * 13) + 25);
		}

		g2d.drawString ("Number of Cells Alive\t: " + GridManager.numberOfLivingCells, boxPosX, (numberOfRows * 14) + 25);
		g2d.drawString ("Number of Generations: " + GridManager.numberOfGenerations, boxPosX, (numberOfRows * 15) + 25);
	}

	public void hoverNextGenerationButton (boolean state) {
		nextGenHover = state;
	}

	public void hoverResetGridButton (boolean state) {
		resetGridHover = state;
	}

	public void enableCellHover (int row, int column) {
		hoverRow = row;
		hoverColumn = column;
		cellHover = true;
	}

	public void disableCellHover () {
		cellHover = false;
	}

	public void hoverSwitchColourButton (boolean state) {
		switchColourHover = state;
	}

	public void hoverSavePatternButton (boolean state) {
		savePatternHover = state;
	}

	public void hoverApplyPatternButton (boolean state) {
		applyPatternHover = state;
	}

	public void hoverAutoButton (boolean state) {
		autoHover = state;
	}

	public void setColour (int colour) {
		if (colour == 1) {
			red = 1;
			green = 0;
			blue = 0;
			colourBoxString = "Switch to Green";
		}

		else if (colour == 2) {
			red = 0;
			green = 1;
			blue = 0;
			colourBoxString = "Switch to Blue";
		}

		else if (colour == 3) {
			red = 0;
			green = 0;
			blue = 1;
			colourBoxString = "Switch to Red";
		}
	}

	public void setPatternCounter (int patternCounter) {
		patternCounter += 1;
		this.patternCounter = patternCounter;
	}

	public void setAutoGenerate (boolean autoGenerating) {
		if (autoGenerating) {
			autoGeneratingText = "Stop Generation";
		}

		else {
			autoGeneratingText = "Start Generation";
		}
	}
}