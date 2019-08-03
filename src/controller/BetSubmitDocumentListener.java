package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.BetDialogBox;
import view.MainFrame;

public class BetSubmitDocumentListener implements DocumentListener {

	private MainFrame frame;
	private BetDialogBox dialogBox;

	public BetSubmitDocumentListener(MainFrame mainFrame, BetDialogBox betDialogBox) {
		this.frame = mainFrame;
		this.dialogBox = betDialogBox;
	}

	public void insertUpdate(DocumentEvent e) {
		checkIfInt();
	}

	public void removeUpdate(DocumentEvent e) {
		checkIfInt();
	}

	public void changedUpdate(DocumentEvent e) {
		checkIfInt();
	}

	/*
	 * If the content inside the frame is not empty
	 * 		convert the bet value from string to an integer 
	 *			If the integer value is zero
	 * 				a dialog box is popped up notifying the user to enter a valid bet
	 *else
	 *		dialog box is popped notifying the user to enter an integer
	 */
	public void checkIfInt() {
		try {
			if ((frame.getBet(dialogBox).isEmpty())) {
				Integer.parseInt(frame.getBet(dialogBox));
				if (Integer.parseInt(frame.getBet(dialogBox)) == 0) {
					frame.betZero(dialogBox);
				}
			} else {
				frame.setIfInt(dialogBox);
			}
		} catch (Exception e) {
			frame.setIfNotInteger(dialogBox);
		}
	}

}
