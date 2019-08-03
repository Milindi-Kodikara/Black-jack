package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class MainPanel extends JPanel {

	private GameEngine gameEngine;
	private MainFrame frame;
	private JPanel cardPanel;
	private JPanel housePanel;
	private JPanel resultPanel;
	private JPanel summaryPanel;
	private JLabel summaryOfPlayer;
	private JLabel message;
	private Boolean reset = false;
	private Map<Player, CardPanel> playerCards = new HashMap<Player, CardPanel>();
	// Map to keep track of the player being dealt or not
	private Map<Player, Boolean> playerIfDealt = new HashMap<Player, Boolean>();
	private TitledBorder title;
	private TitledBorder summaryTitle;
	private TitledBorder houseTitle;
	private TitledBorder resultTitle;

	public MainPanel(GameEngine gameEngine, MainFrame frame) {
		Border blackline;
		this.gameEngine = gameEngine;
		this.frame = frame;

		blackline = BorderFactory.createLineBorder(Color.black);

		summaryTitle = BorderFactory.createTitledBorder("Summaries");
		summaryTitle.setTitleJustification(TitledBorder.CENTER);

		title = BorderFactory.createTitledBorder("Player panel");
		title.setTitleJustification(TitledBorder.CENTER);

		houseTitle = BorderFactory.createTitledBorder("House panel");
		houseTitle.setTitleJustification(TitledBorder.CENTER);
		resultTitle = BorderFactory.createTitledBorder("Results");
		resultTitle.setTitleJustification(TitledBorder.CENTER);

		summaryPanel = new JPanel();
		summaryPanel.setLayout(new GridLayout(0, 1));
		summaryPanel.setBorder(summaryTitle);
		summaryPanel.setBackground(Color.WHITE);

		cardPanel = new CardPanel();
		cardPanel.setLayout(new GridLayout(0, 1));
		cardPanel.setBorder(title);
		cardPanel.setBackground(Color.WHITE);

		housePanel = new JPanel();
		housePanel.setLayout(new GridLayout(0, 1));
		housePanel.setBorder(houseTitle);
		housePanel.setBackground(Color.WHITE);

		resultPanel = new JPanel();
		resultPanel.setLayout(new GridLayout(0, 1));
		resultPanel.setBorder(resultTitle);
		resultPanel.setBackground(Color.WHITE);

		this.setLayout(new GridLayout(0, 4));
		this.add(summaryPanel);
		this.add(cardPanel);
		this.add(housePanel);
		this.add(resultPanel);
		revalidate();
		repaint();
	}

	public void setCard(Player player, PlayingCard card) {
		CardPanel playerCardPanel;
		// Add a new card to the card panel with the player as the key
		if (playerCards.get(player) == null) {
			playerCardPanel = new CardPanel();
			playerCards.put(player, playerCardPanel);
		} else {
			// Get a new card according to the key
			playerCardPanel = playerCards.get(player);
		}
		// Display the card dealt to the player
		playerCardPanel.addCard(card);
		panelOnSelect(player);
		revalidate();
		repaint();
	}

	// Cards dealt to the house panel
	public void setHouseCard(PlayingCard card, GameEngine gameEngine) {
		Border redline;
		JPanel displayHousePanel = new JPanel();
		displayHousePanel.setLayout(new GridLayout(0, 1));
		JLabel displayCardSuit = new JLabel("Suit: " + card.getSuit());
		JLabel displayCardValue = new JLabel("Value: " + card.getValue());
		JLabel displayCardScore = new JLabel("Score: " + card.getScore());
		displayHousePanel.add(displayCardSuit);
		displayHousePanel.add(displayCardValue);
		displayHousePanel.add(displayCardScore);

		redline = BorderFactory.createLineBorder(Color.red);
		housePanel.add(displayHousePanel);
		displayHousePanel.setBackground(Color.WHITE);
		displayHousePanel.setBorder(redline);
		repaint();
		revalidate();
	}

	public void panelOnSelect(Player player) {
		if (playerCards.get(player) == null) {
			playerCards.put(player, new CardPanel());
		}
		// Add the selected player's cards
		if (frame.getSelectedItemCombo() == player) {
			cardPanel.removeAll();
			cardPanel.add(playerCards.get(player));
		}
		cardPanel.setBackground(Color.WHITE);
		repaint();
		revalidate();
	}

	public void getBustPlayerMessage(Player player) {
		message = new JLabel("You BUSTED!");
		CardPanel tempCardPanel = playerCards.get(player);
		tempCardPanel.add(message);
		tempCardPanel.setBackground(Color.WHITE);
	}

	public void getBustHouseMessage() {
		JLabel message = new JLabel("You BUSTED!");
		housePanel.add(message);
	}

	// Add the results of the players after house has dealt to each player
	public void result(int result, GameEngine gameEngine) {
		resultPanel.removeAll();
		for (Player resultsPlayer : gameEngine.getAllPlayers()) {
			JLabel resultOfPlayer = new JLabel(
					"Player " + resultsPlayer.getPlayerName() + " result = " + resultsPlayer.getResult());
			resultPanel.add(resultOfPlayer);
		}
		JLabel houseResult = new JLabel("House Final Result = " + result);
		resultPanel.add(houseResult);
	}

	public void addSummaryText(Player player) {
		summaryOfPlayer = new JLabel(
				"Player: " + player.getPlayerName() + " Points: " + player.getPoints() + " Bet: " + player.getBet());
		summaryPanel.add(summaryOfPlayer);

	}

	public void setSummaryText() {
		summaryPanel.removeAll();
		for (Player summaryPlayers : gameEngine.getAllPlayers()) {
			summaryOfPlayer = new JLabel("Player: " + summaryPlayers.getPlayerName() + " Points: "
					+ summaryPlayers.getPoints() + " Bet: " + summaryPlayers.getBet());
			summaryPanel.add(summaryOfPlayer);
		}
		revalidate();
		repaint();

	}

	// Add to the playerIfDealt map to check if house can be dealt or not
	public void setIfCanDeal(Player player, Boolean ifCanDeal) {
		playerIfDealt.put(player, ifCanDeal);
	}

	public Boolean getIfCanDeal(Player player) {
		return playerIfDealt.get(player);
	}

	public Boolean getIfCanReset() {
		return reset;
	}

	public void setIfCanReset(Boolean canReset) {
		reset = canReset;
	}

	public JPanel getCardPanel() {
		return cardPanel;
	}

	// Reset the game when add bet was clicked
	public void reset() {
		playerCards.clear();
		playerIfDealt.clear();
		cardPanel.removeAll();
		housePanel.removeAll();
		resultPanel.removeAll();
		for (Player players : gameEngine.getAllPlayers()) {
			setIfCanDeal(players, false);
		}
	}

}