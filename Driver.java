/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     The Game of Life – Mindvalley Challenge
**  Class        Driver
*/

import java.util.Scanner;

public class Driver {
	public static void main (String arrayOfArguments[]) {
		Scanner input = new Scanner (System.in);

		int userChoice;

		GridManager.createNewGrid (10, 10);
		GridManager.initialiseGrid ();
				
		/* TEST
		GridManager.activateCell (1, 1);
		GridManager.activateCell (1, 2);
		GridManager.activateCell (2, 1);
		GridManager.activateCell (2, 2);
		GridManager.activateCell (3, 3);
		GridManager.activateCell (3, 4);
		GridManager.activateCell (4, 3);
		GridManager.activateCell (4, 4);*/

		GridManager.activateCell (2, 1);
		GridManager.activateCell (2, 2);
		GridManager.activateCell (2, 3);

		GridManager.printGrid ();

		do {
			System.out.printf ("\nChoice: ");
			userChoice = input.nextInt ();

			if (userChoice == 0) {
				GridManager.scanGrid ();
				GridManager.printGrid ();
			}
		} while (userChoice != -1);

		System.out.printf ("\n");

		System.out.printf ("\n[0][0]'s Neighbours: %d\n", GridManager.neighboursAlive (0, 0));
	}
}