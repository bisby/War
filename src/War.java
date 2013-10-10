import java.util.*;
public class War {
	private Deck dealerPile = new Deck(true);
	private Player player1 = new Player("Player 1");
	private Player player2 = new Player("Player 2");
	
	private int battleCount = 0;
	
	public void play() {
		deal();
		while (player1.cardsLeft() != 0 && player2.cardsLeft() != 0) {
			battle(player1, player2);
			System.out.println("1: " + player1.cardsLeft() + " - 2: " + player2.cardsLeft());
		}
		
		if (player1.cardsLeft() > player2.cardsLeft()) {
			System.out.println(player2.getName() + " wins the war");
		} else if (player2.cardsLeft() > player1.cardsLeft()) {
			System.out.println(player2.getName() + " wins the war");
		} else {
			System.out.println("The game is a draw");
		}
	}
	
	public void deal() {
		while (dealerPile.cardsLeft() > 0) {
			player1.pickup(dealerPile.dealCard());
			player2.pickup(dealerPile.dealCard());
		}		
	}
	
	public int battle (Player player1, Player player2) {
		battleCount += 1;
		player1.drawCard();
		player2.drawCard();
		System.out.println("BATTLE " + battleCount + "!");
		System.out.println("1: " + player1.battleCard().toString() + " vs 2: " + player2.battleCard().toString());
		if (player1.battleCard().getValue() > player2.battleCard().getValue()) {
			player1.win(player2.lose());
			return 1;
		} else if (player2.battleCard().getValue() > player1.battleCard().getValue()) {
			player2.win(player1.lose());
			return 2;
		} else {
			if (player1.drawWarCards() == null) {
				player2.win(player1.lose());
				return -1;
			}
			if (player2.drawWarCards() == null) {
				player1.win(player2.lose());
				return -1;
			}
			return war(player1.warCards(), player2.warCards());
		}	
	}
	
	public int war (ArrayList<Card> deck1, ArrayList<Card> deck2) {
		int[] values1 = new int[3];
		int[] values2 = new int[3];
		System.out.print("1: ");
		int x = 0;
		for (Card c : deck1) {
			System.out.print(c.toString() + " ");
			values1[x] = c.getValue();
			x++;
		}
		System.out.print("vs 2: ");
		x = 0;
		for (Card c : deck2) {
			System.out.print(c.toString() + " ");
			values2[x] = c.getValue();
			x++;
		}
		Arrays.sort(values1);
		Arrays.sort(values2);
		System.out.println();
		if (values1[2] > values2[2]) {
			player1.win(player2.lose());
			return 1;
		} else if (values2[2] > values1[2]) {
			player2.win(player1.lose());
			return 2;
		} else {
			if (values1[1] > values2[1]) {
				player1.win(player2.lose());
				return 1;
			} else if (values2[1] > values1[1]) {
				player2.win(player1.lose());
				return 2;
			} else {
				if (values1[0] > values2[0]) {
					player1.win(player2.lose());
					return 1;
				} else if (values2[0] > values1[0]) {
					player2.win(player1.lose());
					return 2;
				} else {
					player1.tie();
					player2.tie();
					System.out.println("The war was a stalemate");
					return 0;
				}
			}
		}	
	}
}
