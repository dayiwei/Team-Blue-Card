import java.util.ArrayList;

public class BigTwo {

    public BigTwo(int players) {
	
	Arraylist<Card> deck = new ArrayList<Card>();
	for(int i=1;i<5;i++)
	    for (int x=1; x<14;x++){
		Card c = new Card(x,i);
		deck.add(c);
	    }
        ArrayList<Card> player1 = new ArrayList<Card>();
	ArrayList<Card> player2 = new ArrayList<Card>();
	ArrayList<Card> player3 = new ArrayList<Card>();
	ArrayList<Card> player4 = new ArrayList<Card>();
        ArrayList<Card> btPlayers = new ArrayList<Card>();
	btPlayers.add(player1);
	btPlayers.add(player2);
	btPlayers.add(player3);
	btPlayers.add(player4);
	for(int i=3;i>players;i--){
	    btPlayers.remove(i);
	}
	for(ArrayList<Card> a : btPlayers) {
	    for(int x=0;x<52/players;x++)
		btPlayers.set(i,btPlayers.get(i).add(deck.get(x)));
	}
    }

    public void play() {

	
	while(b
    }
    
    public static void main(String args[]) {
	BigTwo game = new BigTwo;
	play();
    }
    
}
