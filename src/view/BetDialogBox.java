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
import controller.BetSubmitDocumentListener;
import controller.UpdateBetListener;
import model.interfaces.GameEngine;

public class BetDialogBox extends JDialog {
	private GameEngine gameEngine;
	private MainFrame frame;
	private JTextField playerBetText;
	private JButton betSubmitButton;
	private int playerBetInt;

	public BetDialogBox(GameEngine gameEngine, MainFrame mainFrame, boolean isModal) {
		this.gameEngine = gameEngine;
		this.frame = mainFrame;
		setModal(isModal);
		setTitle("Add bet");
		setPreferredSize(new Dimension(400, 100));
		JPanel betPanel = new JPanel(new SpringLayout());

		JLabel labelId = new JLabel("Enter betting value", JLabel.TRAILING);
		betPanel.add(labelId);
		playerBetText = new JTextField();
		playerBetText.getDocument().addDocumentListener(new BetSubmitDocumentListener(frame, this));
		labelId.setLabelFor(playerBetText);
		betPanel.add(playerBetText);

		// Spring layout used to get the positions of the Jlabels and the JTextFields
		// positioned properly
		SpringUtilities.makeCompactGrid(betPanel, 1, 2, 6, 6, 6, 6);
		betSubmitButton = new JButton("SUBMIT");
		betSubmitButton.addActionListener(new UpdateBetListener(gameEngine, mainFrame, this));
		add(betPanel, BorderLayout.CENTER);
		add(betSubmitButton, BorderLayout.PAGE_END);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public String getBet() {
		return playerBetText.getText();
	}

	public int getActualBet() {
		// User entered bet value which was converted to an integer is returned
		return playerBetInt;
	}

	public void setIfInt() {
		// Convert the bet value to an integer
		playerBetInt = Integer.parseInt(playerBetText.getText());

	}

	public void setIfNotInteger() {
		// Error message when the bet entered cannot be converted to an integer
		JOptionPane.showMessageDialog(frame, "Please Enter a valid Integer for bet!");
	}

	public void cannotSetBet() {
		JOptionPane.showMessageDialog(frame, "Invalid Bet!");
	}

	public void existingBet() {
		JOptionPane.showMessageDialog(frame, "You've already placed a bet");
	}

	public void betZero() {
		JOptionPane.showMessageDialog(frame, "Please enter a bet which is greater than zero!");
	}

	public void closeWindow() {
		setVisible(false);
	}

}
