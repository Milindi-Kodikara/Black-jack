package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class DealListener implements ActionListener {
	private GameEngine gameEngine;
	private MainFrame frame;
	private boolean canDealHouse = true;

	public DealListener(GameEngine gameEngine, MainFrame frame) {
		this.gameEngine = gameEngine;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (frame.getSelectedItemCombo() != null) {
			new Thread() {
				@Override
				public void run() {

					Player player = (Player) frame.getSelectedItemCombo();
					frame.getDeal().setEnabled(false);
					// If the player's bet amount is greater than 0 and the player's points are
					// greater than zero a player is dealt cards
					if ((player.getBet() > 0) && (player.getPoints() > 0)) {
						// Set if the player has been dealt in the hash map so that house could deal
						frame.setIfCanDeal(player, true);
						gameEngine.dealPlayer(player, 1000);
					}

					for (Player playerInEng : gameEngine.getAllPlayers()) {
						// If the player has not been dealt then canDealHouse is false
						if ((frame.getIfCanDeal(playerInEng) == false)) {
							System.out.println(playerInEng + frame.getIfCanDeal(playerInEng).toString());
							// If the player has not been dealt but the player points are zero then the
							// house could be dealt
							if (playerInEng.getPoints() == 0) {
								canDealHouse = true;
								break;
							} else {
								canDealHouse = false;
								break;
							}
						} else {
							canDealHouse = true;
						}
					}

					dealHouse(canDealHouse);
				}
			}.start();
		} else {
			// If there is no player selected
			frame.getIfNoPlayer();
		}
	}

	public void dealHouse(boolean canDealHouse) {
		if (canDealHouse == true) {
			gameEngine.dealHouse(1000);
			// Boolean set to true after house is dealt so as to reset the game
			frame.canReset(true);
		}
	}

}
