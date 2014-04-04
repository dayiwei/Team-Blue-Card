import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Runtime;
import java.io.IOException;
public class ChinesePoker{
	private Trick topTrick;
	private Player owner;
	private int turn;
	private int passCtr;
	private ArrayList<Player> players;
	private ArrayList<Player> winners;
	public ChinesePoker(){
		players = new ArrayList<Player>();
		winners = new ArrayList<Player>();
		passCtr = 0;
	}
	public void setup(){
		clearscreen();
		System.out.println("Chinese Poker");
		System.out.println("Developed by Team Blue Card");
		System.out.println("\nVisit wikipedia.org/wiki/Big_Two\nfor rules on how to play.");
		Scanner x = new Scanner(System.in);
		int numplayers = 0;
		while(numplayers != 2 && numplayers != 3 && numplayers != 4){
			System.out.print("\nEnter number of players(2-4): ");
			numplayers = x.nextInt();
			if(numplayers != 2 && numplayers != 3 && numplayers != 4){
				System.out.println("Invalid number!");
			}
		}
		while(players.size() != numplayers){
			System.out.print("Enter Player " + Integer.toString(players.size() + 1) + "'s name: ");
			players.add(new Player(x.next()));
		}
		dealcards();
	}
	public void play(){
		clearscreen();
		firstturn();
		transition();
		while(! (playersleft() == 1)){
			System.out.println(this);
			if(free()){
				freeturn();
			}
			else{
				turn();
			}
			transition();
		}
	}
	private boolean free(){
		return owner == players.get(turn) || passCtr == playersleft();
	}
	public boolean fin(){
		System.out.println("Game over!");
		for(Player x : players){
			if(!x.won()){
				winners.add(x);
			}
		}
		for(int x = 0;x < winners.size();x++){
			if(x == 0){
				System.out.print("1st place winner: ");
			}
			else if(x == 1){
				System.out.print("2nd place: ");
			}
			else if(x == 2){
				System.out.print("3rd place: ");
			}
			else if(x == 3){
				System.out.print("4th place: ");
			}
			System.out.println(winners.get(x).getName());
		}
		System.out.println("Play again? (y/n): ");
		String a = new Scanner(System.in).next();
		return a.equals("y");
	}
	private void nextTurn(){
		turn++;
		if(turn >= players.size()){
			turn = 0;
		}
		if(players.get(turn).won()){
			nextTurn();
		}
	}
	private int playersleft(){
		int x = 0;
		for(Player y : players){
			if(!(y.won())){
				x++;
			}
		}
		return x;
	}
	private void dealcards(){
		ArrayList<Card> deck = new ArrayList<Card>();
		for(int i=1;i<5;i++){
			for (int x=1; x<14;x++){
				deck.add(new Card(x,i));
			}
		}
		int numcards = 13;
		if (players.size() == 3){
			numcards = 17;
		}
		for(;numcards != 0;numcards--){
			for(Player p : players){
				p.add(deck.remove((int)(Math.random() * deck.size())));
			}
		}
		for(Player p : players){
			Trick.sort(p);
		}
	}
	private void firstturn(){
		Scanner s = new Scanner(System.in);
		turn = findlowest();
		Player p = players.get(turn);
		System.out.println("It is " + p.getName() + "'s turn.");
		boolean loop = false;
		while(!loop){
			System.out.println("You MUST play your lowest card in this turn.");
			System.out.println(p);
			System.out.print("Select card(s) to play(1-13) seperated by commas:");
			ArrayList<Card> cards = new ArrayList<Card>();
			String[] input = s.next().split(",");
			ArrayList<Integer> added = new ArrayList<Integer>();
			if (!(input.length == 1 && Integer.parseInt(input[0]) == 0)){
				for(String x : input){
					int cindex = Integer.parseInt(x) - 1;
					if(! added.contains(cindex)){
						added.add(cindex);
						cards.add(p.get(cindex));
					}
				}
			}
			if(Trick.validate(cards) && cards.contains(p.get(0))){
				loop = true;
				topTrick = p.play(cards);
				owner = p;
				clearscreen();
				System.out.println(p.getName() + " played a " + topTrick + ".");
				System.out.println(GraphicCards.toString(topTrick));
				nextTurn();
			}
			else{
				System.out.println("Invalid Trick! Try again!");
			}
		}
	}
	private void freeturn(){
		Scanner s = new Scanner(System.in);
		Player p = players.get(turn);
		boolean loop = false;
		while (! loop){
			System.out.println("You have a free turn. You may play any valid combo.");
			System.out.print("Select card(s) to play(1-"+ p.size() +") seperated by commas:");
			ArrayList<Card> cards = new ArrayList<Card>();
			String[] input = s.next().split(",");
			ArrayList<Integer> added = new ArrayList<Integer>();
			if(twoOfSpadesClause(new Trick(cards))){
				System.out.println("You cannot win with the Deuce of Spades!");
			}
			else if (!(input.length == 1 && Integer.parseInt(input[0]) == 0)){
				for(String x : input){
					int cindex = Integer.parseInt(x) - 1;
					if(! added.contains(cindex)){
						added.add(cindex);
						cards.add(p.get(cindex));
					}
				}
			}
			if(Trick.validate(cards)){
				passCtr = 0;
				topTrick = p.play(cards);
				owner = p;
				clearscreen();
				System.out.println(p.getName() + " played a " + topTrick + ".");
				System.out.println(GraphicCards.toString(topTrick));
				p.checkwin();
				if (p.won()){
					System.out.println(p.getName() + " has won!");
					winners.add(p);
				}
				loop = true;
				nextTurn();
			}
			else{
				System.out.println("Invalid Trick! Try again!");
			}
		}
	}
	private boolean twoOfSpadesClause(Trick x){
		ArrayList<Card> pcards = players.get(turn);
		ArrayList<Card> tcards = x.getCards();
		return (tcards.size() <= 2) && 
		       (tcards.size() == pcards.size()) &&
		       (tcards.contains(new Card(2,4)));
	}
	private void turn(){
		Scanner s = new Scanner(System.in);
		Player p = players.get(turn);
		boolean loop = false;
		while (! loop){
			System.out.print("What action will you take?(Pass or Go):");
			String action = s.next();
			if (action.equals("Pass")){
				passCtr++;
				loop = true;
				nextTurn();
				clearscreen();
				System.out.println(p.getName() + " passed!");
			}
			else if(action.equals("Go")){
				System.out.println("Enter 0 to cancel or");
				System.out.print("Select card(s) to play(1-"+ p.size() +") seperated by commas: ");
				ArrayList<Card> cards = new ArrayList<Card>();
				String[] input = s.next().split(",");
				ArrayList<Integer> added = new ArrayList<Integer>();
				if (!(input.length == 1 && Integer.parseInt(input[0]) == 0)){
					for(String x : input){
						int cindex = Integer.parseInt(x) - 1;
						if(! added.contains(cindex)){
							added.add(cindex);
							cards.add(p.get(cindex));
						}
					}
				}
				if(twoOfSpadesClause(new Trick(cards))){
					System.out.println("You cannot win with the Deuce of Spades!");
				}
				else if(Trick.validate(cards) && (new Trick(cards).beats(topTrick))){
					passCtr = 0;
					loop = true;
					topTrick = p.play(cards);
					owner = p;
					clearscreen();
					System.out.println(p.getName() + " played a " + topTrick + ".");
					System.out.println(GraphicCards.toString(topTrick));
					p.checkwin();
					if (p.won()){
						System.out.println(p.getName() + " has won!");
						winners.add(p);
					}
					nextTurn();
				}
				else{
					System.out.println("Invalid Trick! Try again!");
				}
			}
		}
	}
	public void transition(){
		System.out.println(players.get(turn).getName() + "'s turn!");
		System.out.println("(Hit Enter to continue)");
		new Scanner(System.in).nextLine();
		clearscreen();
	}
	public static void clearscreen(){
		for(int x = 0;x<100;x++){
			System.out.println();
		}
	}
	public String toString(){
		String ret = "\nIt is " + players.get(turn).getName() + "'s turn.\n";
		ret += "Turn order is: \n";
		for(Player p : players){
			String a = p.getName() + " : ";
			while (a.length() != 14){
				a += " ";
			}
			ret += a;
			if(p.won()){
				ret += "Won.";
			}
			else{
				ret += p.size() + " cards left.";
			}
			ret += "\n";
		}
		ret += "The last combo played is a " + topTrick + " by " + owner.getName() + ".	";
		ret += "\n" + GraphicCards.toString(topTrick) + "\n";
		ret += players.get(turn);
		return ret;
	}
	private int findlowest(){
		int index = 0;
		Card lowest = players.get(0).get(0);
		for(int x = 1;x < players.size();x++){
			if(players.get(x).get(0).compareTo(lowest) < 0){
				index = x;
				lowest = players.get(x).get(0);
			}
		}
		return index;
	}
	public static void main(String[] args){
		boolean keepplaying = true;
		while(keepplaying){
			ChinesePoker x = new ChinesePoker();
			x.setup();
			x.play();
			keepplaying = x.fin();
		}
	}
}