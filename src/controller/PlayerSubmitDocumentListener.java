package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.MainDialogBox;
import view.MainFrame;

public class PlayerSubmitDocumentListener implements DocumentListener {

	private MainFrame frame;
	private MainDialogBox dialogBox;

	public PlayerSubmitDocumentListener(MainFrame mainFrame, MainDialogBox mainDialogBox) {
		this.frame = mainFrame;
		this.dialogBox = mainDialogBox;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkIfInt();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkIfInt();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		checkIfInt();
	}

	public void checkIfInt() {
		try {
			// Check if the points entered is an integer
			// If not an integer an error dialog box is popped up
			Integer.parseInt(frame.getPointsTextField(dialogBox));
			frame.setIfIntDialogBox(dialogBox);
		} catch (Exception e) {
			frame.setIfNotIntegerDialogBox(dialogBox);
		}
	}

}
