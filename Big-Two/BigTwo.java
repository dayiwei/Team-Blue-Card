import java.util.ArrayList;

public class BigTwo {
	ArrayList<ArrayList<Card>> players;
	public BigTwo(int numplayers) {

		Arraylist<Card> deck = new ArrayList<Card>();
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
		// Find Diamond 3
		int turn;
		
	}

	public static void main(String args[]) {
		BigTwo game = new BigTwo;
		play();
	}

}
