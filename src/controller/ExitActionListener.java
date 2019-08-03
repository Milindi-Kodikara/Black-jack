package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//Menu item to exit the game
			System.exit(0);
		}
	
}
