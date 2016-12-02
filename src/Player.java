import java.util.*;
public class Player {
	private int score;
	private Deck cards = new Deck(false);
	private String name;
	private ArrayList<Card> cache = new ArrayList<Card>();

	public Player (String name) {
		this.name = name;
	}
	
	public Card drawCard() {
		return cards.dealCard();
	}
	
	public String getName() {
		return name;
	}
	
	public int cardsLeft() {
		return cards.cardsLeft();
	}
	
	public ArrayList<Card> getCache() {
		return cache;
	}
	
	public void cacheCards(int count) {
		cache.clear();
		for (int i = 0; i < count; i++) {
			cache.add(drawCard());
		}
	}

	public void pickup(Card card) {
		card.setOwner(this);
		cards.getCard(card);
	}
	
	public void pickup(ArrayList<Card> card) {
		cards.getCard(card);
	}
}
