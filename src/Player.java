import java.util.*;
public class Player {
	private int score;
	private Deck cards;
	private String name;
	private ArrayList<Card> pot;
	
	public Player (String initname) {
		name = initname;
		cards = new Deck(false);
		pot = new ArrayList<Card>();
	}
	
	public Card drawCard() {
		Card temp = cards.dealCard();
		if (temp == null) return null;
		pot.add(temp);
		return temp;
	}
	
	public String getName() {
		return name;
	}
	
	public int cardsLeft() {
		return cards.cardsLeft();
	}
	
	public int win(ArrayList<Card> prize) {
		score += 1;
		cards.getCard(prize);
		cards.getCard(pot);
		pot = new ArrayList<Card>();
		System.out.println(name + " won this battle!");
		return score;
	}
	
	public void tie() {
		cards.getCard(pot);
		pot = new ArrayList<Card>();
	}
	
	public Card battleCard() {
		return pot.get(0);
	}
	
	public ArrayList<Card> drawWarCards() {
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(cards.dealCard());
		if (temp.get(0) == null) return null;
		temp.add(cards.dealCard());
		if (temp.get(1) == null) return null;
		temp.add(cards.dealCard());
		if (temp.get(2) == null) return null;
		pot.addAll(temp);
		return temp;
	}
	
	public ArrayList<Card> warCards() {
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(pot.get(1));
		temp.add(pot.get(2));
		temp.add(pot.get(3));
		return temp;
	}
	
	public ArrayList<Card> lose() {
		ArrayList<Card> temp = pot;
		pot = new ArrayList<Card>();
		return temp;
	}
	
	public void pickup(Card card) {
		cards.getCard(card);
	}
	
	public void pickup(ArrayList<Card> card) {
		cards.getCard(card);;
	}
}
