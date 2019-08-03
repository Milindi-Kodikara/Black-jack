package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class StatusBar extends JPanel{

	private JLabel status = new JLabel("        ");
	
	public StatusBar() {
		status = new JLabel ("START GAME", SwingConstants.CENTER);
		Border border = BorderFactory.createRaisedBevelBorder();
		status.setBorder(border);
		
		setLayout(new GridLayout (1,0));
		add(status);
	}

	public void statusAddPlayer() {
		status.setText("A new player has been added to the game");
	}
	public void statusRemovePlayer() {
		status.setText("The selected player has been removed from the game");
	}
	public void statusDealPlayer() {
		status.setText("Dealing to the player!");
	}
	public void statusDealHouse() {
		status.setText("Dealing to the house!");
	}
	public void statusHouseBust() {
		status.setText("BUSTED!");
	}
	public void statusResults() {
		status.setText("GAME OVER!");
	}
}
