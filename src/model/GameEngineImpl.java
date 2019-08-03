package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {

	private Map<String, Player> playerMap = new HashMap<String, Player>();
	private List<GameEngineCallback> gameEngineCallbackList = new ArrayList<GameEngineCallback>();
	private Deque<PlayingCard> deck = new LinkedList<PlayingCard>();

	private int result;
	private int actualResult;
	private int actualHouseResult;
	private PlayingCard card;
	private int count = 0;

	@Override
	public void dealPlayer(Player player, int delay) {
		actualResult = 0;
		deal(player, delay);

	}

	@Override
	public void dealHouse(int delay) {
		deal(null, delay);
	}

	private void deal(Player player, int delay) {
		// reset the total points of the player/house for every new player
		result = 0;

		while (result <= BUST_LEVEL) {
			// Call method to deal a card
			dealCard();
			delay(delay);
			// if the addition of all the scores of the cards for the player (result) is
			// less than the bust level value
			// call the dealNextCard method
			// iterate until the player busts
			if (result <= BUST_LEVEL) {
				dealNextCard(player);
				continue;
			} else {
				bust(player);
				result(player);
				if (player != null) {
					player.setResult(actualResult);
				}

			}
		}
	}

	private void dealCard() {
		// if deque is empty create a new deque and return a card
		if ((deck.size() <= 0)) {
			deck = getShuffledDeck();
		}
		// Deal a card from the deque
		card = deck.pop();
		// Get the score of the card
		count = card.getScore();
		// result is the addition of all the scores of the cards with every iteration of
		// the loop for each player/house
		result = result + count;
	}

	private void dealNextCard(Player player) {
		// Loop through the callbacks and call the appropriate method depending on
		// player or house
		for (GameEngineCallback gameEngineCall : gameEngineCallbackList) {
			if (!(player == null)) {
				gameEngineCall.nextCard(player, card, this);
			} else {
				gameEngineCall.nextHouseCard(card, this);
			}

		}
	}

	private void bust(Player player) {
		// actualResult = addition of the scores of the dealt cards - the score of the
		// card which caused the player/house to bust
		actualResult = result - count;
		// loop through the GameEngineCallbacks and the appropriate method is called
		// depending on player or house
		for (GameEngineCallback gameEngineCall : gameEngineCallbackList) {
			if (!(player == null)) {
				gameEngineCall.bustCard(player, card, this);
			} else {
				gameEngineCall.houseBustCard(card, this);

			}
		}
	}

	private void result(Player player) {
		for (GameEngineCallback gameEngineCall : gameEngineCallbackList) {
			if (!(player == null)) {
				gameEngineCall.result(player, actualResult, this);
			} else {
				actualHouseResult = actualResult;
				// method to set the total points of the players
				setFinalPlayerPoints();
				gameEngineCall.houseResult(actualHouseResult, this);

			}
		}
	}

	private void setFinalPlayerPoints() {
		// Compute the points depending on the player's result
		// Set the points of the player
		for (Player player : playerMap.values()) {
			if (player.getResult() > actualHouseResult) {
				player.setPoints(player.getPoints() + player.getBet());
			}
			if (player.getResult() < actualHouseResult) {
				player.setPoints(player.getPoints() - player.getBet());
			}
			if (player.getResult() == actualHouseResult) {
				player.setPoints(player.getPoints());
			}
			// reset the bet
			player.resetBet();
		}
	}

	private void delay(int delay) {
		// delay between each deal of cards in milliseconds(ms)
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addPlayer(Player player) {
		playerMap.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		// return the player if player exists
		// return null if player doesn't exist
		return playerMap.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		// return true if player is removed
		return playerMap.remove(player.getPlayerId(), player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gameEngineCallbackList.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// return true if the gameEngineCallback existed
		if (gameEngineCallback != null) {
			gameEngineCallbackList.remove(gameEngineCallback);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// return an unmodifiable collection of players
		return Collections.unmodifiableCollection(playerMap.values());
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		// Call the placeBet method of SimplePlayer class
		// return true if the bet can be placed
		return player.placeBet(bet);
	}

	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		// Loop through the Suits
		// Loop through the values and make a new playing card
		for (PlayingCard.Suit suit : Suit.values()) {
			for (PlayingCard.Value value : PlayingCard.Value.values()) {
				deck.add(new PlayingCardImpl(suit, value));
			}

		}
		// Shuffle the deck
		Collections.shuffle((List<PlayingCard>) deck);
		return deck;

	}

}
