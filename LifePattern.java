/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     The Game of Life – Mindvalley Challenge
**  Class        LifePattern
*/

import java.util.ArrayList;
import java.io.Serializable;

public class LifePattern implements Serializable {
	String patternName;
	private ArrayList<Point> listOfPoints;

	public LifePattern (String patternName) {
		listOfPoints = new ArrayList<Point> ();
		setPatternName (patternName);
	}

	/* Getters and Setters */
	public void setPatternName (String patternName) {
		this.patternName = patternName;
	}

	public void addPoint (Point newPoint) {
		if (newPoint != null) {
			listOfPoints.add (newPoint);
		}
	}

	public String getPatternName () {
		return this.patternName;
	}

	public ArrayList<Point> getListOfPoints () {
		return this.listOfPoints;
	}
}