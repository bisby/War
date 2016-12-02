import java.util.*;
public class War {
	private Deck dealerPile = new Deck(true);
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> pot = new ArrayList<Card>();
	private int battleCount = 0;
	
	public void play() {
		playerJoins(new Player("Player 1"));
		playerJoins(new Player("Player 2"));
		deal();

		while (!winConditions()) {
			battle();
			cardCount();
		}
		System.out.println(players.get(0).getName() + " loses");
		//	TODO - Filter "players" down to a single victor
		//		and System.out.println(victor.getName() + " wins");
	}
	
	public void playerJoins(Player person) {
		players.add(person);
	}

	public void deal() {
		while (dealerPile.cardsLeft() > players.size()) {
			for (Player p : players) {
				p.pickup(dealerPile.dealCard());
			}
		}
	}

	public boolean winConditions() {
		ArrayList<Player> remaining = new ArrayList<Player>();
		for (Player p : players) {
			if (p.cardsLeft() > 0) { remaining.add(p); }
		}
		return (remaining.size() <= 1);
	}

	public void battle() {
		battleCount += 1;
		pot.clear();
		ArrayList<Card> bestCards = new ArrayList<Card>();
		Player winner;


		for (int i = 0; i < players.size(); i++) {
			pot.add(players.get(i).drawCard());
		}

		System.out.println("BATTLE " + battleCount + "!");
		bestCards = compareCards(pot);

		if (bestCards.size() == 1) {
			winner = bestCards.get(0).getOwner();
			System.out.println(winner.getName() + " wins with " + bestCards.get(0));
		} else {
			ArrayList<Player> battlers = new ArrayList<Player>();
			for (Card c : bestCards) {
				battlers.add(c.getOwner());
			}
			winner = war(battlers);
		}
		winner.pickup(pot);
		System.out.println();
		return;
	}

	public ArrayList<Card> compareCards(ArrayList<Card> cards) {
		ArrayList<Card> bestCards = new ArrayList<Card>();
		for (Card c : cards) {
			if (bestCards.size() == 0 || bestCards.get(0) == null) {
				bestCards.add(c);
			} else if (c == null) {

			} else if (c.getValue() > bestCards.get(0).getValue()) {
				bestCards.clear();
				bestCards.add(c);
			} else if (c.getValue() == bestCards.get(0).getValue()) {
				bestCards.add(c);
			}
		}
		return bestCards;
	}

	public void cardCount() {
		for (Player p : players) {
			System.out.print(p.getName() + ": " + p.cardsLeft() + " ");
		}
		System.out.println();
	}

	public Player war (ArrayList<Player> battlers) {
		ArrayList<Card> pool = new ArrayList<Card>();
		ArrayList<Card> bestCards = new ArrayList<Card>();
		System.out.println("WAR!");

		for (Player p : battlers) {
			p.cacheCards(3);
			pot.addAll(p.getCache());
		}

		for (int i = 0; i < 3; i++) {
			pool.clear();
			for (Player p : battlers) { 
				if (p.getCache().get(i) != null) {
					System.out.println(p.getName()  + " " + p.getCache().get(i));
					pool.add(p.getCache().get(i));
				}
			}
			bestCards = compareCards(pool);
			System.out.println(bestCards.size());
			if (bestCards.size() == 1) {
				return bestCards.get(0).getOwner();
			} else if (bestCards.size() == 0) {
				return battlers.get(0);
			}
		}

		return war(battlers);
	}
}
