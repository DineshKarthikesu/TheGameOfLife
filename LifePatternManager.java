/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     The Game of Life – Mindvalley Challenge
**  Class        LifePattern
*/

import java.util.ArrayList;

/* File Handling */
import java.io.File;
import java.io.FileInputStream;     //reading
import java.io.ObjectInputStream;   //reading
import java.io.FileOutputStream;    //writing
import java.io.ObjectOutputStream;  //writing
import java.io.PrintStream;			//writing

import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import java.lang.SecurityException;

public class LifePatternManager {
	public static ArrayList<LifePattern> listOfPatterns = new ArrayList<LifePattern> ();
	public static File patternFile = new File ("patterns.data");
	public static boolean patternFileExists;

	public static LifePattern createNewLifePattern (Cell[][] cellGrid, int numberOfRows, int numberOfColumns, String patternName) {
		Point newPoint;
		LifePattern newPattern = null;

		if (GridManager.getNumberOfLivingCells () > 0) {
			newPattern = new LifePattern (patternName);

			for (int row = 0; row < numberOfRows; row++) {
				for (int column = 0; column < numberOfColumns; column++) {
					if (cellGrid[row][column].isCellAlive ()) {
						newPoint = new Point (row, column);
						newPattern.addPoint (newPoint);
					}
				}
			}
		}

		listOfPatterns.add (newPattern);
		return newPattern;
	}

	public static void printLifePattern (LifePattern lifePattern) {
		ArrayList<Point> listOfPoints = lifePattern.getListOfPoints ();

		for (int counter = 0; counter < listOfPoints.size (); counter++) {
			System.out.printf ("\n[%d][%d]", listOfPoints.get (counter).getRow (), listOfPoints.get (counter).getColumn ());
		}
	}

	public static String[] getPatternNameArray () {
		String[] patternNameArray = new String[listOfPatterns.size ()];

		for (int counter = 0; counter < listOfPatterns.size (); counter++) {
			patternNameArray[counter] = listOfPatterns.get (counter).getPatternName ();
			System.out.printf ("\nPattern: %s", listOfPatterns.size (), listOfPatterns.get (counter).getPatternName ());
		}

		return patternNameArray;
	}

	public static void applyPattern (int patternCounter) {
		LifePattern patternToBeApplied = listOfPatterns.get (patternCounter);
		ArrayList<Point> listOfPoints = patternToBeApplied.getListOfPoints ();
		GridManager.resetGrid ();

		for (int counter = 0; counter < listOfPoints.size (); counter++) {
			GridManager.activateCell (listOfPoints.get (counter).getRow (), listOfPoints.get (counter).getColumn ());
		}
	}

	public static void saveList () {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream ("patterns.data");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream (fileOutputStream);

			objectOutputStream.writeObject (listOfPatterns);
			objectOutputStream.close ();
		}
		
		catch (IOException ioe) {
			System.out.println (ioe);
		}

		catch (SecurityException se) {
			System.out.printf ("\nSecurity Exception");
		}
	}

	public static void readFromObjectFile () {
		patternFileExists = patternFile.exists ();

		try {
			if (patternFileExists) {
				FileInputStream fileInputStream = new FileInputStream (patternFile);
				ObjectInputStream objectInputStream = new ObjectInputStream (fileInputStream);

				listOfPatterns = (ArrayList<LifePattern>) objectInputStream.readObject ();
				objectInputStream.close ();
			}

			else {
				listOfPatterns = (ArrayList<LifePattern>) new ArrayList<LifePattern> ();
			}
		}
			
		catch (IOException ioe) {
			System.out.printf ("\nIO Exception");
		}

		catch (ClassNotFoundException cnfe) {
			System.out.printf ("\nClass Not Found Exception");
		}
	}
}