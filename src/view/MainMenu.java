package view;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.ExitActionListener;

public class MainMenu extends JMenuBar{

	public MainMenu(MainFrame mainFrame) {
		JMenu menu = new JMenu ("Menu");
		menu.setMnemonic(KeyEvent.VK_I);
		this.add(menu);
		
		JMenuItem exit = new JMenuItem ("Exit");
		exit.addActionListener(new ExitActionListener());
		exit.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_DOWN_MASK));
		menu.add(exit);
	}

}
