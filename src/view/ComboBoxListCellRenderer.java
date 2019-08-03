package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.interfaces.Player;

//Code adapted from TL8-ComboBox example
public class ComboBoxListCellRenderer extends JLabel implements ListCellRenderer<Player> {

	private Color background;

	public ComboBoxListCellRenderer() {
		background = getBackground();
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, Player value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// Set the text inside the combo box to the name of the player
		setText(value.getPlayerName());
		setBackground(isSelected ? Color.GRAY : background);
		return this;
	}

}
