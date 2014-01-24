import java.util.ArrayList;
import java.util.Scanner;

public class BigTwo {
    ArrayList<ArrayList<Card>> players;
    int turn;
    int numplayers;
    boolean freeturn;

    public BigTwo(int playernumb) {
	players = new ArrayList<ArrayList<Card>>();
	numplayers=playernumb;
	freeturn = true;
	ArrayList<Card> deck = new ArrayList<Card>();
	for(int i=1;i<5;i++){
	    for (int x=1; x<14;x++){
		Card c = new Card(x,i);
		deck.add(c);
	    }
	}
	for(int i=numplayers;i != 0; i--){
	    ArrayList<Card> x = new ArrayList<Card>();
	    players.add(x);
	}
	while(deck.size() >= players.size()){
	    for(ArrayList<Card> x : players){
		x.add(deck.remove((int)(Math.random() * deck.size())));
	    }
	}
    }


    public void pass() {
	freeturn=true;
    }
    

    public void play(){
	turn = findLowest();
	int oldnum = players.size();
	ArrayList<Card> hand = new ArrayList<Card>();
	//combo of the 1st player with diamond 3	
	Combo oldpile = new Combo(hand);
	Combo newpile = new Combo(hand);
	while(players.size()!=1){
	    /*let player choose cards and put it on pile, need something to loop around the turns and let the player use pass(), need something to tell which player actually won, stops when there is only 1 player left
	     */
	    turn = turn%numplayers;
	    System.out.println("Player "+(1+turn)+"'s turn!");
	    System.out.println("Hand to beat :");
	    System.out.println(oldpile);
	    System.out.print("Your hand: ");
	    System.out.println(players.get(turn));

	    Scanner scanner = new Scanner(System.in); 

	    for(int i=0;i<6;i++){
		System.out.print("Play: ");
		String input = scanner.next();
		if(input.equals("yes"))
		    pass();
		if(input.equals("enter")||input.equals("play")){
		    newpile = new Combo(hand);
		    if(freeturn||newpile.beats(oldpile)){
			oldpile = newpile;
			break;
		    }
		    else
			System.out.println("Invalid input");
		}
		else
		    if(Integer.parseInt(input)<players.get(turn).size()){
			hand.add(players.get(turn).get(Integer.parseInt(input)));
		    }
	    }
	    turn++;
	    freeturn=false;
	}
    }
    // System.out.println("player " + turn +"won!");

	
	
    private int findLowest(){
	Card lowest = new Card(2,4);
	int minindex=0;
	for(int x = 0; x < players.size(); x++){
	    Combo.sort(players.get(x));
	    if(players.get(x).get(0).compareTo(lowest) <= 0){
		lowest = players.get(x).get(0);
		minindex = x;
	    }
	}
	return minindex;
    }
    
    
    public static void main(String args[]) {
	Scanner scanner = new Scanner(System.in);
	System.out.print("Number of Players: ");
	int numplayers = scanner.nextInt();
	while(numplayers<2 || numplayers>4){
	    System.out.println("Invalid number of players");
	    System.out.print("Number of Players: ");
	    numplayers = scanner.nextInt();
	}
	// System.out.println(numplayers);

	BigTwo game = new BigTwo(numplayers);
	game.play();
    }
    
}
