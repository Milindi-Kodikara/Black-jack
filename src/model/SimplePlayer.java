package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {

	private String playerID;
	private String playerName;
	private int points;
	private int bet;
	private int result;

	public SimplePlayer(String playerID, String playerName, int initialPoints) {

		this.playerID = playerID;
		this.playerName = playerName;
		this.points = initialPoints;
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;

	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;

	}

	@Override
	public String getPlayerId() {

		return this.playerID;
	}

	@Override
	public boolean placeBet(int bet) {

		if ((bet >= 0) && (points >= bet)) {
			this.bet = bet;
			return true;
		}

		return false;
	}

	@Override
	public int getBet() {

		return this.bet;
	}

	@Override
	public void resetBet() {
		this.bet = 0;

	}

	@Override
	public int getResult() {

		return this.result;
	}

	@Override
	public void setResult(int result) {

		this.result = result;

	}

	@Override
	public String toString() {

		return String.format("%-2s%s%-2s%s%-2s%s", "Player: id=", this.playerID, ", name=", this.playerName,
				", points=", this.points);
		

	}

}
