package client;

import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.PlayingCardImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
//import validate.Validator;
import view.GameEngineCallbackImpl;
import view.GameEngineCallbackGUI;
import view.interfaces.GameEngineCallback;

/**
 * Simple console client for SADI assignment 2, 2018
 * NOTE: This code will not compile until you have implemented code for the supplied interfaces!
 * 
 * You must be able to compile your code WITHOUT modifying this class.
 * Additional testing should be done by copying and adding to this class while ensuring this class still works.
 * 
 * The provided Validator.jar will check if your code adheres to the specified interfaces!
 * 
 * @author Caspar Ryan
 * 
 */
public class SimpleTestClient
{
   private static Logger logger = Logger.getLogger("assignment1");

   public static void main(String args[])
   {
      final GameEngine gameEngine = new GameEngineImpl();
      
      

      // call method in Validator.jar to test *structural* correctness
      // just passing this does not mean it actually works .. you need to test yourself!
      // pass false if you want to disable logging .. (i.e. once it passes)
      //Validator.validate(true);

      // create two test players
      Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000), new SimplePlayer("2", "The Loser",
         500),new SimplePlayer("3", "The Avenger", 800),new SimplePlayer("4", "The Revenger", 1900),new SimplePlayer("5", "Notice", 400),new SimplePlayer("6", "Moi", 2000) };

      // add logging callback
      gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
      //gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameEngine));


      // Uncomment this to DEBUG your deck of cards creation
      		Deque<PlayingCard> shuffledDeck = gameEngine.getShuffledDeck();
      		printCards(shuffledDeck);
      		int count = 0;
      		
      	//gameEngine.getAllPlayers().clear();	
      // main loop to add players, place a bet and receive hand
      	
      for (int i = 0 ; i <= 1 ; i++) {
 
      for (Player player : players) {
    	  
    	 gameEngine.removePlayer(players[2]);
         gameEngine.addPlayer(player);
         gameEngine.placeBet(player, 100);
         gameEngine.dealPlayer(player, 100);
         
         
      }
      		

      // all players have played so now house deals 
      // GameEngineCallBack.houseResult() is called to log all players (after results are calculated)
      gameEngine.dealHouse(10);
   
      		
      		if (shuffledDeck.getFirst().equals(shuffledDeck.getFirst())) {
      		
      		System.out.println("EQUALS WORKS OR NAH ?" + count );
      		}
      		else {
      			System.out.println("SUCKAH!"+ count);
      		}
      		count++;
     }
   }
  
   
   @SuppressWarnings("unused")
   private static void printCards(Deque<PlayingCard> deck)
   {
      for (PlayingCard card : deck)
         logger.log(Level.INFO, card.toString());
   }
}
