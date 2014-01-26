import java.util.ArrayList;
import java.util.Scanner;

public class BigTwo {
    ArrayList<Player> players;
    int turn;
    int numplayers;
    boolean freeturn;

    public BigTwo(int playernumb) {
	players = new ArrayList<Player>();
	numplayers=playernumb;
	freeturn = true;
	ArrayList<Card> deck = new ArrayList<Card>();
	for(int i=1;i<5;i++){
	    for (int x=1; x<14;x++){
		Card c = new Card(x,i);
		deck.add(c);
	    }
	}
	for(int i=0;i< numplayers; i++){
	    Player p = new Player("Player "+ (1+i));
	    players.add(p);
	}
	while(deck.size() >= players.size()){
	    for(Player x : players){
		x.addCard(deck.remove((int)(Math.random() * deck.size())));
	    }
	}
    }

    

    public void play(){
	turn = findLowest();
	int oldnum = players.size();
	ArrayList<Card> hand = new ArrayList<Card>();
	//combo of the 1st player with diamond 3	
	Combo oldpile = new Combo(hand);
	Combo newpile = new Combo(hand);
	
	while(players.size()>1){
	    //let player choose cards and put it on pile, need something to loop around the turns and let the player use pass(), need something to tell which player actually won, stops when there is only 1 player left
	    hand = new ArrayList<Card>();
	    turn = turn%numplayers;
    	    
	    System.out.println(players.get(turn).getName()+"'s turn!");
	    if(freeturn) {
		System.out.println("Free turn");
	    }
	    else {
		System.out.println("Hand to beat :");
		System.out.println(oldpile);
	    }
	    System.out.print("Your hand: ");
	    System.out.println(players.get(turn).getHand());

	    Scanner scanner = new Scanner(System.in); 

	    for(int i=0;i<6;i++){
		System.out.print("Play: ");
		String input = scanner.next();
		if(input.equals("pass")){
		    freeturn=true;
		    break;
		}
		else if(input.equals("enter")||input.equals("play")){
		    newpile = new Combo(hand);
		    if((freeturn && Combo.validate(hand)) || newpile.beats(oldpile)){
			oldpile = newpile; 
			freeturn=false;
			break;
		    }
		    else {
			System.out.println("Invalid input\n" + "Please choose your cards again");
			turn=turn+numplayers-1;
			break;
		    }
		}
		else
		    if(Integer.parseInt(input)-1<players.get(turn).getHand().size()){
			hand.add(players.get(turn).getHand().get(Integer.parseInt(input)-1));
		    }
	    }
	    players.get(turn).getHand().removeAll(hand);
	    if(players.get(turn).getHand().size()==0){
    	        System.out.println("Player " + (1+turn)+" Won");
		players.remove(turn);
		freeturn=true;
		numplayers--;
	    }
	    turn++;	
	}

	System.out.println("Fin");
    }

	
	
    private int findLowest(){
	Card lowest = new Card(2,4);
	int minindex=0;
	for(int x = 0; x < players.size(); x++){
	    Combo.sort(players.get(x).getHand());
	    if(players.get(x).getHand().get(0).compareTo(lowest) <= 0){
		lowest = players.get(x).getHand().get(0);
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

	BigTwo game = new BigTwo(numplayers);
	game.play();
    }
    
}
