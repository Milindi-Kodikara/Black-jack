package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.interfaces.PlayingCard;

public class CardPanel extends JPanel {
	public CardPanel() {
		this.setLayout(new GridLayout(0, 1));
	}

	// A new panel is added for every card dealt
	public void addCard(PlayingCard card) {
		Border blueline;
		JPanel oneCardPanel = new JPanel();
		oneCardPanel.setLayout(new GridLayout(0, 1));
		JLabel displayCardSuit = new JLabel("Suit: " + card.getSuit());
		JLabel displayCardValue = new JLabel("Value: " + card.getValue());
		JLabel displayCardScore = new JLabel("Score: " + card.getScore());
		oneCardPanel.add(displayCardSuit);
		oneCardPanel.add(displayCardValue);
		oneCardPanel.add(displayCardScore);

		blueline = BorderFactory.createLineBorder(Color.blue);
		oneCardPanel.setBorder(blueline);
		oneCardPanel.setBackground(Color.WHITE);
		this.add(oneCardPanel);

	}

}
