package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.BetDialogBox;
import view.MainFrame;

public class UpdateBetListener implements ActionListener {
	private GameEngine gameEngine;
	private MainFrame frame;
	private BetDialogBox betDialogBox;

	public UpdateBetListener(GameEngine gameEngine, MainFrame mainFrame, BetDialogBox betDialogBox) {

		this.gameEngine = gameEngine;
		this.frame = mainFrame;
		this.betDialogBox = betDialogBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player player = (Player) frame.getSelectedItemCombo();
		if (player != null) {
			int betValue = betDialogBox.getActualBet();
			// If a bet has been placed already or if the bet value equals to zero error
			// messages are displayed
			if (((player.getBet() > 0)) || (frame.getBet(betDialogBox).equals("0"))) {
				if (frame.getBet(betDialogBox).equals("0")) {
					frame.betZero(betDialogBox);
				} else {
					frame.existingBet(betDialogBox);
					frame.closeBetWindow(betDialogBox);
				}
			} else {
				// Place the bet in gameEngine for the player
				boolean canSetBet = gameEngine.placeBet(player, betValue);
				if (canSetBet == true) {
					// Update the summary panel with the player details
					frame.setSummaryText();
					frame.closeBetWindow(betDialogBox);
				} else {
					frame.cannotSetBet(betDialogBox);
				}
			}

		}
		// If there is no player display an error message
		else {
			frame.getIfNoPlayer();
		}
	}

}
