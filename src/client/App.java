package client;

import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackImpl;
import view.MainFrame;

public class App {
    private static Logger logger = Logger.getLogger("Assignment2");

    public static void main(String args[]) {
	//Validator.validate(true);
	new App();
    }

    public App() {
	final GameEngine gameEngine = new GameEngineImpl();
	gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		MainFrame guiView = new MainFrame(gameEngine);
	    }
	});

    }

}