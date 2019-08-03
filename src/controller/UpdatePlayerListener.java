
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainDialogBox;
import view.MainFrame;

public class UpdatePlayerListener implements ActionListener {

	private GameEngine gameEngine;
	private MainFrame frame;
	private MainDialogBox dialogBox;

	public UpdatePlayerListener(GameEngine gameEngine, MainFrame frame, MainDialogBox mainDialogBox) {
		this.gameEngine = gameEngine;
		this.frame = frame;
		this.dialogBox = mainDialogBox;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String playerId = frame.getIDLabelText(dialogBox);
		String playerName = frame.getNameLabelText(dialogBox);
		int initialPoints = frame.getPointsLabelTextDialogBox(dialogBox);
		// If the text fields aren't empty and the initial points entered is not zero
		// Close the dialog box to enter the player details and add a new player to
		// Simple Player
		// Add a new player to the playerMap in gameEngine
		if ((!playerId.isEmpty()) && (!playerName.isEmpty()) && (initialPoints != 0)) {
			frame.closeWindow(dialogBox);
			Player player = new SimplePlayer(playerId, playerName, initialPoints);
			gameEngine.addPlayer(player);
			// The map to check if the house can be dealt if the player is dealt is updated
			// with the value false initially

			frame.setIfCanDeal(player, false);
			// Add the player to the combo box
			frame.populateComboBox(player);
			// Add the summary panel with the player details
			frame.addSummaryText(player);
			// Update the summary panel each time a player is added
			frame.setSummaryText();

		} else {
			// An error messge pops up if the fields aren't filled
			frame.isEmpty(dialogBox);
		}
	}

}
