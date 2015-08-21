/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     The Game of Life – Mindvalley Challenge
**  Class        MainPanel
*/

/* Lightweight Swing Components */
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Cursor;

/* Event Handlers */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JFrame {
	private int colourCounter = 1;
	private int patternCounter = 0;
	private String patternName;
	private Boolean autoGenerating = false;

	private Canvas drawingCanvas;
	private String colourList[] = {"Red", "Green", "Blue"};
	private JComboBox<String> colourComboBox = new JComboBox<String> (colourList);

	private JPanel optionPanel = new JPanel ();
	private JPanel patternSelectionPanel = new JPanel ();

	private JButton selectColourButton = new JButton ("Select Colour");

	private GridBagConstraints constraint = new GridBagConstraints ();
	private Dimension preferredButtonSize = new Dimension (256, 30);
	private Dimension preferredCanvasSize = new Dimension (700, 500);

	private Timer autoGenerationTimer;

	public MainPanel () {
		super ("The Game of Life");
		setLayout (new BorderLayout ());

		/* Event Handler Instances */
		ButtonHandler buttonHandler = new ButtonHandler ();
		MouseHandler mouseHandler = new MouseHandler ();
		TimerHandler timerHandler = new TimerHandler ();

		autoGenerationTimer = new Timer (200, timerHandler);

		GridManager.createNewGrid (40, 60);
		GridManager.initialiseGrid ();
		LifePatternManager.readFromObjectFile ();

		drawingCanvas = new Canvas ();
		drawingCanvas.setPreferredSize (preferredCanvasSize);
		drawingCanvas.setColour (colourCounter);

		/* Add Event Handlers to GUI Components */
		drawingCanvas.addMouseListener (mouseHandler);
		drawingCanvas.addMouseMotionListener (mouseHandler);

		add (drawingCanvas, BorderLayout.CENTER);
	}

	private class ButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			//button presses
		}
	}

	private class MouseHandler extends MouseAdapter {
		public void mouseClicked (MouseEvent event) {
			int row;
			int column;

			row = (event.getY () - 30) / 15;
			column = (event.getX () - 30) / 15;

			if (row >= 0 && column >= 0) {
				if (row < GridManager.getNumberOfRows () && column < GridManager.getNumberOfColumns ()) {
					GridManager.activateCell (row, column);
				}
			}

			if (row >= 0 && row <= 2 && column >= 61 && column <= 70) {
				GridManager.scanGrid ();
			}

			else if (row >= 3 && row <= 5 && column >= 61 && column <= 70) {
				GridManager.resetGrid ();
			}

			else if (row >= 6 && row <= 8 && column >= 61 && column <= 70) {
				if (colourCounter > 0 && colourCounter < 3) {
					colourCounter++;
				}

				else if (colourCounter == 3) {
					colourCounter = 1;
				}

				drawingCanvas.setColour (colourCounter);
			}

			else if (row > 9 && row <= 11 && column >= 61 && column <= 70) {
				patternName = null;
				patternName = JOptionPane.showInputDialog (null, "Enter Pattern Name: ", "Pattern Creation", JOptionPane.INFORMATION_MESSAGE);

				if (patternName != null) {
					LifePattern newPattern = LifePatternManager.createNewLifePattern (GridManager.getCellGrid (), GridManager.getNumberOfRows (), GridManager.getNumberOfColumns (), patternName);
					
					if (newPattern != null) {
						LifePatternManager.getPatternNameArray ();
						LifePatternManager.saveList ();
						JOptionPane.showMessageDialog (null, "Pattern " + patternName + " has been successfully saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
					}

					else {
						JOptionPane.showMessageDialog (null, "Why save an empty grid?", "Error", JOptionPane.WARNING_MESSAGE);
					}
				}	
			}

			else if (row > 12 && row <= 15 && column >= 61 && column <= 70) {
				if (LifePatternManager.listOfPatterns.size () != 0) {
					if (patternCounter <= LifePatternManager.listOfPatterns.size () - 1) {
						LifePatternManager.applyPattern (patternCounter);
						drawingCanvas.setPatternCounter (patternCounter);
						System.out.printf ("\nCounter: %d", patternCounter);
						patternCounter++;
					}

					else {
						patternCounter = 0;
						LifePatternManager.applyPattern (patternCounter);
						drawingCanvas.setPatternCounter (patternCounter);
						patternCounter++;
					}
				}
				
				else {
						JOptionPane.showMessageDialog (null, "You have not saved any patterns.", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}

			else if (row > 16 && row <= 18 && column >= 61 && column <= 70) {
				if (autoGenerating) {
					autoGenerationTimer.stop ();
					autoGenerating = false;
				}

				else {
					autoGenerationTimer.start ();
					autoGenerating = true;
				}

				drawingCanvas.setAutoGenerate (autoGenerating);

			}
			
			drawingCanvas.repaint ();
			System.out.printf ("\nClicked [%d, %d] :: [%d, %d]", event.getX (), event.getY (), row, column);
		}

		public void mouseMoved (MouseEvent event) {
			int row;
			int column;

			row = (event.getY () - 30) / 15;
			column = (event.getX () - 30) / 15;

			if (row >= 0 && column >= 0 && row < GridManager.getNumberOfRows () && column < GridManager.getNumberOfColumns ()) {
				drawingCanvas.enableCellHover (row, column);
				drawingCanvas.setCursor (new Cursor (Cursor.HAND_CURSOR));
			}

			else {
				drawingCanvas.disableCellHover ();
				drawingCanvas.setCursor (new Cursor (Cursor.DEFAULT_CURSOR));
			}

			if (row >= 0 && row <= 2 && column >= 61 && column <= 70) {
				drawingCanvas.hoverNextGenerationButton (true);
			}

			else {
				drawingCanvas.hoverNextGenerationButton (false);
			}

			if (row >= 3 && row <= 5 && column >= 61 && column <= 70) {
				drawingCanvas.hoverResetGridButton (true);
			}

			else {
				drawingCanvas.hoverResetGridButton (false);
			}

			if (row >= 6 && row <= 8 && column >= 61 && column <= 70) {
				drawingCanvas.hoverSwitchColourButton (true);
			}

			else {
				drawingCanvas.hoverSwitchColourButton (false);
			}

			if (row > 9 && row <= 11 && column >= 61 && column <= 70) {
				drawingCanvas.hoverSavePatternButton (true);
			}

			else {
				drawingCanvas.hoverSavePatternButton (false);
			}

			if (row > 12 && row <= 15 && column >= 61 && column <= 70) {
				drawingCanvas.hoverApplyPatternButton (true);
			}

			else {
				drawingCanvas.hoverApplyPatternButton (false);
			}

			if (row > 16 && row <= 18 && column >= 61 && column <= 70) {
				drawingCanvas.hoverAutoButton (true);
			}

			else {
				drawingCanvas.hoverAutoButton (false);
			}

			drawingCanvas.repaint ();
		}
	}

	private class TimerHandler implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (event.getSource () == autoGenerationTimer) {

				if (GridManager.getNumberOfLivingCells () == 0) {
					autoGenerationTimer.stop ();
					autoGenerating = false;
				}

				else {
					GridManager.scanGrid ();
					drawingCanvas.repaint ();				
				}

				drawingCanvas.setAutoGenerate (autoGenerating);

			}
		}
	}
}