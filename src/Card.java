
public class Card {
	private int value;
	private String suit;

	public Card(int value, String suit) {
		this.value = value;
		this.suit = suit;
	}

	public int getValue() {
		return this.value;
	}

	public String getSuit() {
		return this.suit;
	}

	public String toString() {
		if (this.value < 11) {
			return this.value + " of " + this.suit;
		}
		else {
			String[] faceCards = {"Jack", "Queen", "King", "Ace"};
			return faceCards[this.value-11] + " of " + this.suit;
		}
	}
}
