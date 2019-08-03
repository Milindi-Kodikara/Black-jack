package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import controller.AddPlayerListener;
import controller.ComboBoxListener;
import controller.DealListener;
import controller.PlaceBetListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class MainToolBar extends JToolBar {

	private GameEngine gameEngine;
	private MainFrame frame;

	private JButton addPlayer, placeBet, deal;
	private JComboBox<Player> playerCombo;

	public MainToolBar(GameEngine gameEngine, MainFrame frame) {
		this.gameEngine = gameEngine;
		this.frame = frame;

		setRollover(true);
		addPlayer = new JButton("Add Player");
		addPlayer.addActionListener(new AddPlayerListener(frame));

		placeBet = new JButton("Place Bet");
		placeBet.addActionListener(new PlaceBetListener(gameEngine, frame));
		deal = new JButton("Deal");
		deal.addActionListener(new DealListener(gameEngine, frame));
		deal.setEnabled(false);

		playerCombo = new JComboBox<>();
		playerCombo.addActionListener(new ComboBoxListener(gameEngine, frame));

		add(addPlayer);
		add(placeBet);
		add(deal);
		add(playerCombo);
		addSeparator();
		revalidate();

	}

	// Add items to the combo box
	public void populateComboBox(Player player) {
		playerCombo.addItem(player);
		updateComboBox();
		playerCombo.setRenderer(new ComboBoxListCellRenderer());
		revalidate();
	}

	public Object getSelectedItemCombo() {
		return playerCombo.getSelectedItem();
	}

	// Error message when the player is not found
	public void getIfNoPlayer() {

		JOptionPane.showMessageDialog(frame, "No player selected!");
	}

	//Error message if the bet isn't set
	public void getIfNoBet() {

		JOptionPane.showMessageDialog(frame, "Bet is zero or no bet has been placed!");
	}

	public JButton getDealButton() {
		return deal;
	}

	public void updateComboBox() {
		playerCombo.removeAllItems();

		for (Player player : gameEngine.getAllPlayers()) {
			playerCombo.addItem(player);
		}
	}

}
