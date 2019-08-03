package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainFrame;

public class AddPlayerListener implements ActionListener {

	private MainFrame frame;

	public AddPlayerListener(MainFrame frame) {

		this.frame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Calls the method on the frame for the add player details dialog box
		frame.enterPlayerDetails();
	}
}
