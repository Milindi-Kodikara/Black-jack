package view;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private MainFrame guiView;

	public GameEngineCallbackGUI(MainFrame guiView) {
		this.guiView = guiView;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Calls the next card for the player from the frame
				guiView.nextPlayerCard(player, card);
				guiView.setSummaryText();

			}
		});
	}

	public void result(Player player, int result, GameEngine engine) {
		// Player results are to be displayed after house is dealt
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				guiView.nextPlayerCard(player, card);
				guiView.bustPlayerMessage(player);
				guiView.setSummaryText();

			}
		});

	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Deal the next house card to house
				guiView.nextHouseCard(card, engine);

			}
		});

	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				guiView.nextHouseCard(card, engine);
				guiView.bustHouseMessage();
				guiView.setSummaryText();
			}
		});
	}

	@Override
	public void houseResult(int result, GameEngine engine) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				guiView.getResults(result, engine);

			}
		});
	}
}
