package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {

	private Suit suit;
	private Value value;

	public PlayingCardImpl(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Value getValue() {
		return value;
	}

	// if the ordinal value is greater than 9 i.e if Jack, Queen, King then return
	// 10
	// else return (ordinal value + 1) as the index starts at 0
	@Override
	public int getScore() {
		return (value.ordinal() > 9) ? 10 : value.ordinal() + 1;
	}

	@Override
	public boolean equals(PlayingCard card) {
		// Check if the card exits
		if (card == null) {
			return false;
		}
		// Check if the face value and the suit is equal
		if (!(suit.equals(card.getSuit()))) {
			return false;
		}
		if (!(value.equals(card.getValue()))) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	// Check the card that was passed to the parameter for null and if the suit and
	// values aren't equal to the playing card return false
	// if the card passed to the parameter is equal to the playing card then return
	// true
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PlayingCard)) {
			return false;
		}

		PlayingCard other = (PlayingCard) obj;
		return equals(other);
	}

	public String toString() {

		return String.format("%-2s%s%-2s%s%-2s", "Suit: ", this.suit, ", Value: " + value, ", Score: ",
				this.getScore());
	}
}
