import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;

    public Player(String playername) {

	name = playername;
	hand = new ArrayList<Card>();

    }

    public String getName(){return name;}

    public ArrayList<Card> getHand(){return hand;}

    public void addCard(Card c){
	hand.add(c);

    }

}
