package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class ComboBoxListener implements ActionListener {

	GameEngine gameEngine;
	MainFrame frame;

	public ComboBoxListener(GameEngine gameEngine, MainFrame frame) {
		this.gameEngine = gameEngine;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.getDeal().setEnabled(true);
		Player player = (Player) frame.getSelectedItemCombo();
		if (player != null) {
			//Switch the content on the player panel when a player in the combo box is selected 
			frame.getMainPanel().panelOnSelect(player);
		}
	}
}
