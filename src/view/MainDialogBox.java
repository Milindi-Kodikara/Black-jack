package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import controller.PlayerSubmitDocumentListener;
import controller.UpdatePlayerListener;
import model.interfaces.GameEngine;

public class MainDialogBox extends JDialog {
	private GameEngine gameEngine;
	private MainFrame frame;
	private JButton submitButton;
	private JTextField playerIDText;
	private JTextField nameText;
	private JTextField initialPointsText;
	private int initalPointsInt = 0;

	public MainDialogBox(GameEngine gameEngine, MainFrame frame, boolean isModal) {
		this.frame = frame;
		this.gameEngine = gameEngine;

		setModal(isModal);
		setTitle("New Player Dialog Box");
		setPreferredSize(new Dimension(400, 200));

		String[] labels = { "PlayerId: ", "Name: ", "Initial Points: " };
		int numPairs = labels.length;

		// Create panel to add
		JPanel playerPanel = new JPanel(new SpringLayout());

		JLabel labelId = new JLabel(labels[0], JLabel.TRAILING);
		playerPanel.add(labelId);
		playerIDText = new JTextField();
		labelId.setLabelFor(playerIDText);
		playerPanel.add(playerIDText);

		JLabel labelName = new JLabel(labels[1], JLabel.TRAILING);
		playerPanel.add(labelName);
		nameText = new JTextField();
		labelName.setLabelFor(nameText);
		playerPanel.add(nameText);

		JLabel labelPoints = new JLabel(labels[2], JLabel.TRAILING);
		playerPanel.add(labelPoints);
		initialPointsText = new JTextField();
		initialPointsText.getDocument().addDocumentListener(new PlayerSubmitDocumentListener(frame, this));

		labelPoints.setLabelFor(initialPointsText);
		playerPanel.add(initialPointsText);
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(playerPanel, numPairs, 2, 6, 6, 6, 6);

		submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(new UpdatePlayerListener(gameEngine, frame, this));
		add(playerPanel, BorderLayout.CENTER);
		add(submitButton, BorderLayout.PAGE_END);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public String getIDLabelText() {
		return playerIDText.getText();
	}

	public String getNameLabelText() {
		return nameText.getText();
	}

	public String getPointsTextField() {
		return initialPointsText.getText();
	}

	// Convert the points to integer
	public void setIfIntDialogBox() {
		initalPointsInt = Integer.parseInt(initialPointsText.getText());
	}

	public int getPointsLabelTextDialogBox() {
		return initalPointsInt;
	}

	// Error messages for invalid details
	public void setIfNotIntegerDialogBox() {

		JOptionPane.showMessageDialog(frame, "Please Enter a valid Integer for points!");
	}

	public void ifEmpty() {
		JOptionPane.showMessageDialog(frame, "Please enter all the details!");

	}

	public void closeWindow() {
		setVisible(false);
	}

}
