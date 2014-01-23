import java.util.ArrayList;
import java.util.Scanner;

public class BigTwo {
    ArrayList<ArrayList<Card>> players;
    int turn=0;
    public BigTwo(int numplayers) {

	ArrayList<Card> deck = new ArrayList<Card>();
	for(int i=1;i<5;i++){
	    for (int x=1; x<14;x++){
		Card c = new Card(x,i);
		deck.add(c);
	    }
	}
	for(;numplayers != 0; numplayers--){
	    players.add(new ArrayList<Card>());
	}
	while(deck.size() >= players.size()){
	    for(ArrayList<Card> x : players){
		x.add(deck.remove((int)(Math.random() * deck.size())));
	    }
	}
    }

    

    public void play(){
	int turn = players.findLowest();
	
	
		
	Combo oldpile = new Combo(hand, turn);//combo of the 1st player with diamond 3
	while(players.size()!=1){
	    /*let player choose cards and put it on pile, need something to loop around the turns and let the player use pass(), need something to tell which player actually won, stops when there is only 1 player left
	     */
	    Scanner scanner = new Scanner(System.in); 
	    String input = scanner.next();
	    if(input.equals("pass"))
		pass();
	    else
		//use input to put out cards
		ArrayList<Card> hand = new ArrayList<Card>();
	    for(int i=0;i<;i++)//add stuff from imput to hand
		hand.add(player.get(turn).get(i));
	    Combo newpile = new Combo(hand,turn);
	    if(newpile.compareTo(oldpile)>0)
		oldpile = newpile;
	    else
			
		turn++;
	}

    }
    
    private int findLowest(){
	Card lowest = new Card(2,4)
	int minindex;
	for(int x = 0; x < players.size(); x++){
	    Combo.sort(players.get(x));
	    if(players.get(x).get(0).compareTo(lowest) <= 0){
		lowest = players.get(x).get(0);
		minindex = x;
	    }
	return minindex;
    }

	public void pass() {
	turn++;
    }

    public static void main(String args[]) {
	Scanner scanner = new Scanner(System.in);
	System.out.print("Number of Players: ");
	int numplayers = Integer.parseInt(scanner.next());

	BigTwo game = new BigTwo(numplayers);
	game.play();
    }

}
