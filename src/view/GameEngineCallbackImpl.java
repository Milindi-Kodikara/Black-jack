package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java
 * logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public GameEngineCallbackImpl() {
		// FINE shows dealing output, INFO only shows result
		logger.setLevel(Level.FINE);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE, String.format("%-2s%-2s%-2s%s", "Card was dealt to ", player.getPlayerName(), " .. ",
				card.toString()));

	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		// final results logged at Level.INFO
		logger.log(Level.INFO, String.format("%-2s%-2s%-2s%s", player.getPlayerName(), ",", "Final result=", result));
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE, String.format("%-2s%-2s%-2s%-2s%s", "Card was dealt to ", player.getPlayerName(), "..",
				card.toString(), "... YOU BUSTED!"));
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE, String.format("%-2s%-2s%s", "Card was dealt to ", "House ..", card.toString()));
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE,
				String.format("%-2s%-2s%-2s%s", "Card was dealt to ", "House ..", card.toString(), "... YOU BUSTED!"));

	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		logger.log(Level.INFO, String.format("%-2s%-2s%-2s%s", "House", ",", "Final result=", result));
		// Using String builder class to append the toString method in Player
		StringBuilder newString = new StringBuilder("Final Player Results ");
		// Iterate through the players and append each player's toString to the addition
		// of all the toString methods (newString)
		for (Player playerGet : engine.getAllPlayers()) {
			newString.append("\n" + playerGet.toString());
		}
		logger.log(Level.INFO, newString.toString());

	}
}
