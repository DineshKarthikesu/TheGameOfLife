/*
**	Author:      Dinesh Karthikesu
**	Email:       dineshkarthikesu@live.com
**	Text Editor: Sublime Text 2
**	Project:     The Game of Life – Mindvalley Challenge
**  Class        InterfaceDriver
*/

import javax.swing.JFrame;

public class InterfaceDriver {
	public static void main (String[] arrayOfArguments) {
		MainPanel mainPanel = new MainPanel ();
		mainPanel.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainPanel.setSize (1200, 670);
		mainPanel.setVisible (true);
	}
}