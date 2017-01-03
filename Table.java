import java.util.ArrayList;
import java.util.Random;

public class Table {

	Player[] players;
	Dealer dealer;
	int[] pos_betArray = new int[MAXPLAYER];
	final static int MAXPLAYER = 4;
	Deck allcard;

	Table(int nDeck) {
		allcard = new Deck(nDeck);
		players = new Player[MAXPLAYER];
	}

	public void set_player(int pos, Player p) {
		players[pos] = p;
	}

	public Player[] get_player() {
		return players;
	}

	public void set_dealer(Dealer d) {
		dealer = d;
	}

	public Card get_face_up_card_of_dealer() {
		return dealer.getOneRoundCard().get(1);
	}

	private void ask_each_player_about_bets() {
		int sy = 0;
		for (Player c : players) {
			c.say_hello();
			pos_betArray[sy] = c.make_bet();
			sy++;
			if (sy >= MAXPLAYER)
				break;
		}

	}

	private void distribute_cards_to_dealer_and_players() {
		for (int i = 0; i < players.length; i++) {
			ArrayList<Card> anshan = new ArrayList<Card>();
			anshan.add(allcard.getOneCard(true));
			anshan.add(allcard.getOneCard(true));
			players[i].setOneRoundCard(anshan);

		}
		ArrayList<Card> anshan = new ArrayList<Card>();
		anshan.add(allcard.getOneCard(false));
		anshan.add(allcard.getOneCard(true));
		dealer.setOneRoundCard(anshan);
		System.out.println("Dealer's face up card is ");
		get_face_up_card_of_dealer().printCard();

	}

	private void ask_each_player_about_hits() {
		for (int i = 0; i < players.length; i++) {
			players[i].hit_me(this);
			boolean hit = false;
			do {
				hit = players[i].hit_me(this); // this
				if (hit) {
					ArrayList<Card> anshan = players[i].getOneRoundCard();
					anshan.add(allcard.getOneCard(true));
					players[i].setOneRoundCard(anshan);
					System.out.print("Hit! ");
					System.out.println(players[i].get_name() + "'s Cards now:");
					for (Card c : players[i].getOneRoundCard()) {
						c.printCard();
					}
				} else {
					System.out.println(players[i].get_name() + ", Pass hit!");
					System.out.println(players[i].get_name() + ", Final Card:");
					for (Card c : players[1].getOneRoundCard()) {
						c.printCard();
					}
				}
			} while (hit);

			hit = false;

		}

	}

	private void ask_dealer_about_hits() {
		dealer.hit_me(this);
		boolean hit = false;
		hit = dealer.hit_me(this);
		System.out.println("Dealer's hit is over!");

	}

	private void calculate_chips() {
		System.out.println("Dealer's card value is " + dealer.getTotalValue());
		System.out.print("Cards,");
		dealer.printAllCard();
		for (Player c : players) {
			System.out.println(c.get_name() + " card value is " + c.getTotalValue());
			if (dealer.getTotalValue() <= 21) {
				if (dealer.getTotalValue() > c.getTotalValue()   ||c.getTotalValue()>21) {
					System.out.print(", Loss " + c.make_bet() + " Chips, the Chips now is: ");
					c.decrease_chips(c.make_bet());
					System.out.println(c.get_current_chips());

				}
				if (dealer.getTotalValue() < c.getTotalValue()  &&c.getTotalValue()<=21) {
					System.out.println(",Get " + c.make_bet() + " Chips, the Chips now is: ");
					c.increase_chips(c.make_bet());
					System.out.println(c.get_current_chips());

				}
				if (dealer.getTotalValue() == c.getTotalValue()) {
					System.out.println(",chips have no change! The Chips now is: " + c.get_current_chips());
				}
			} else {
				if (c.getTotalValue() > 21) {
					System.out.println(",chips have no change! The Chips now is: " + c.get_current_chips());
				} else if (c.getTotalValue() < 21) {
					System.out.println(",Get " + c.make_bet() + " Chips, the Chips now is: ");
					c.increase_chips(c.make_bet());
					System.out.println(c.get_current_chips());
				}

			}

		}

	}

	public int[] get_palyers_bet() {
		// TODO Auto-generated method stub
		return pos_betArray;
	}

	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();

		// TODO Auto-generated method stub

	}

}
