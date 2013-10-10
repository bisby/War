import java.util.*;

public class Deck {
	private final String[] CARD_SUITS = {"Spades", "Hearts", "Diamonds", "Clubs"};
	private final int[] CARD_VALUES = {2,3,4,5,6,7,8,9,10,11,12,13,14};
	private ArrayList<Card> deck;

	public Deck(boolean preload) {
		deck = new ArrayList<Card>();
		if (preload) { makeDeck(); }
	}

	public void makeDeck() {
		for (String suit : CARD_SUITS) {
			for (int value : CARD_VALUES) {
				deck.add(new Card(value, suit));
			}
		}
		shuffleDeck();
	}

	public Card dealCard() {
		if (!deck.isEmpty()) {
			Card card = deck.get(0);
			deck.remove(card);
			return card;
		} else {
			return null;
		}
	}
	
	public void getCard(Card card) {
		deck.add(card);
	}

	public void getCard(ArrayList<Card> cards) {
		deck.addAll(cards);
	}

	public int cardsLeft() {
		return deck.size(); 
	}
	
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for(Card card : deck) {
			sb.append(card.toString() + "\n");
		}

		return sb.toString();
	}
}
