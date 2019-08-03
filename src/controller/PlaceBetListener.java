package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class PlaceBetListener implements ActionListener {

	private GameEngine gameEngine ;
	private MainFrame frame ;

	public PlaceBetListener(GameEngine gameEngine, MainFrame frame) {
		this.gameEngine = gameEngine;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Calls the method from the frame to reset the game when the bet button is clicked after the house is dealt
		if (frame.getIfcanReset()) {
			frame.reset();
		}
		//Deal button is enabled to deal to player when bet is set
		frame.getDeal().setEnabled(true);
		for (Player player : gameEngine.getAllPlayers()) {
			if (frame.getSelectedItemCombo().equals(player)) {
				frame.enterBetValue(player);
			
	}
	}
	}
		
}
