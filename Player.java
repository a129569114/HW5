import java.util.ArrayList;

public class Player extends Person {

	private int chips;
	private String name;
	private int bet;
	private ArrayList<Card> oneRoundCard = new ArrayList<Card>();

	public Player(String name1, int chips1) {
		name = name1;
		chips = chips1;

	}

	public void say_hello() {

		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");

	}

	public int make_bet() {
		bet = 0;
		if (chips != 0)
			bet = 1;
		return bet;
	}

	boolean hit_me(Table table) {
		boolean a = true;
		int TotalValue = getTotalValue();
		if (TotalValue > 16) {
			a = false;
		}
		// TODO Auto-generated method stub
		return a;
	}

	public String get_name() {
		// TODO Auto-generated method stub
		return name;
	}

	public void increase_chips(int p1Bet) {
		chips += p1Bet;
		
		// TODO Auto-generated method stub

	}

	public void decrease_chips(int p1Bet) {
		chips -= p1Bet;
		// TODO Auto-generated method stub

	}

	public int get_current_chips() {
		// TODO Auto-generated method stub
		return chips;
	}

	// TODO Auto-generated method stub

}
