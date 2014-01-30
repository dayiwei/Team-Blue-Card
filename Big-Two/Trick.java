/* 
  Rules 

   Single cards: Any card from the deck, ordered by rank with suit being the tie-breaker. (For instance, A of Spades beats A of Hearts, which beats K of Spades.)

   Pairs: Any two cards of matching rank, ordered as with singular cards by the card of the higher suit. (A pair consisting of the K of Spades and K of Clubs beats a pair consisting of K of Hearts and K of Diamonds.)

   Triples: Three equal ranked cards, three twos are highest, then aces, kings, etc. down to three threes, which is the lowest triple. In some variations, a triple can only be played as part of a 5-card hand.

   5-card hand: There are five (var. 2) different valid 5-card poker hands, ranking, from low to high, as follows (the same ranking as in poker):

   Straight (also known as a snake in Cantonese): Any 5 cards in a sequence (but not all of the same suit). Rank is determined by the value of the last card in the sequence, with the suit used only as a tie-breaker. Therefore 3-4-5-6-7 > 2-3-4-5-6, since 6 is considered the largest card in the 2-3-4-5-6 straight. The largest straight is 10-J-Q-K-A (J-Q-K-A-2 is invalid in some variations), while the smallest straight is A-2-3-4-5.

   Flush (also known as a flower): Any 5 cards of the same suit (but not in a sequence). Rank is determined by highest value card and then by highest suit. In some popular variations, flushes are not permitted as a playable hand, and thus it is the lowest possible combination.

   Full House: a composite of a three-of-a-kind combination and a pair. Rank is determined by the value of the triple, regardless of the value of the pair. Also known as a Fullen.

   Four of a kind + One card (nicknamed King Kong, tiki, or Bomb): Any set of 4 cards of the same rank, plus any 5th card. (A 4 of a kind cannot be played unless it is played as a 5-card hand) Rank is determined by the value of the 4 card set, regardless of the value of the 5th card. It is also known as a poker. (Some play the Four of a kind hand as the beat all, therefore nicknamed the bomb, King Kong, or also tiki.)

   Straight Flush: A composite of the straight and flush: five cards in sequence in the same suit. Ranked the same as straights, suit being a tie-breaker. (Sometimes also played as a bomb or tiki, larger than a Four of a Kind)
*/
import java.util.ArrayList;
import java.util.Arrays;
public class Trick{

	private ArrayList<Card> cards;
	private int type;
	private Card value;

	public Trick(ArrayList<Card> input) {
		cards = input;
		sort(cards);
		if(!(validate(cards))){
			type = 0;
			value = null;
		}
		else {
			sort(cards);
			if (cards.size() == 1){
				type = 1;
				value = cards.get(0);
			}
			else if (pair(cards)) {
				type = 2;
				value = cards.get(0);
			}
			else if (triple(cards)){
				type = 3;
				value = cards.get(0);
			}
			else if (house(cards)) {
				type = 6;
				if (cards.get(2) == cards.get(4))
					value = cards.get(4);
				else 
					value = cards.get(2);
			}
			else if (bomb(cards)){
				if(cards.get(0).getV()==(cards.get(1).getV()))
					value = cards.get(0);
				else
					value = cards.get(1);
				type = 7;
			}
			else if (straightflush(cards)){
				type = 8;
				value = cards.get(4);
			}   
			else if (straight(cards)){
				type = 4;
				value = cards.get(4);
			}
			else if (flush(cards)){
				type = 5;
				value = cards.get(4);
			}
		}

	}
	public Trick(Card[] input){
		this(new ArrayList(Arrays.asList(input)));
	}
	public ArrayList<Card> getCards(){
		return cards;
	}
	public int getCT() {
		return type;
	}
	public Card getCV() {
		return value;
	}
	public static boolean validate(ArrayList<Card> input){
		return (input.size() > 0 && (
			input.size()==1||
			pair(input) ||
			triple(input) ||
			straight(input) ||
			flush(input) ||
			house(input) ||
			bomb(input)
			)
		);
	}

	public static void sort(ArrayList<Card> input){
		for( int partition = 1; partition < input.size(); partition++ ) {
			for( int i = partition; i > 0; i-- ) {
				if ( input.get(i).compareTo( input.get(i-1) ) < 0 ) {
					input.set( i, input.set( i-1, input.get(i) ) ); 
				}
				else 
					break; 
			}
		}
	}

	public static boolean sameSuit(ArrayList<Card> input){
		boolean ret = true;
		for(int x = 1;x<input.size();x++){
			if(!(input.get(x -1).equalS(input.get(x)))){
				ret = false;
			}
		}
		return ret;
	}

	private static boolean sameValue(ArrayList<Card> input){
		boolean ret = true;
		for(int x = 1;x<input.size();x++){
			if(!(input.get(x -1).equalV(input.get(x)))){
				ret = false;
				break;
			}
		}
		return ret;
	}

	private static boolean pair(ArrayList<Card> input){
		return (input.size() == 2 && sameValue(input));
	}
	private static boolean triple(ArrayList<Card> input){
		return (input.size() == 3 && sameValue(input));
	}
	private static boolean flush(ArrayList<Card> input){
		if(input.size() != 5)
			return false;
		boolean ret = true;
		for(int x = 1;x<input.size();x++){
			if(!(input.get(x -1).equalS(input.get(x)))){
				ret = false;
			}
		}
		return ret;
	}
	private static void rotatelist(ArrayList<Card> x){
		for(int index = 0; index < (x.size() - 1); index++){
			x.set(index,x.set(index + 1,x.get(index)));
		}
	}
	private static boolean correctorder(ArrayList<Card> input){
		int value = input.get(0).getV();
		if (11 >= value && value >= 13){
			return false;
		}
		for(Card x : input){
			if(!(value == x.getV())){
				return false;
			}
			value++;
			if (value == 14){
				value = 1;
			}
		}
		return true;
	}
	private static boolean straight(ArrayList<Card> input){
		sort(input);
		for(int count = 0;count < 5;count++){
			if(correctorder(input)){
				return true;
			}
			rotatelist(input);
		}
		return false;
	}
	private static boolean house(ArrayList<Card> input) {
		if(input.size()!=5)
			return false;
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(Card a : input){
			if (! (values.contains(a.getV()))){
				values.add(a.getV());
			}
		}
		if (values.size() != 2){
			return false;
		}
		int count = 0;
		for(Card a : input){
			if (a.getV() == values.get(0).intValue()){
				count++;
			}
		}
		return (count == 2 || count == 3);
	}
	private static boolean bomb(ArrayList<Card> input) {
		if(input.size()!=5)
			return false;
		ArrayList values = new ArrayList();
		for(Card a : input){
			if (! (values.contains(a.getV()))){
				values.add(a.getV());
			}
		}
		if (values.size() != 2){
			return false;
		}
		int count = 0;
		for(Card a : input){
			if (a.getV() == values.get(0)){
				count++;
			}
		}
		return (count == 1 || count == 4);
	}
	private static boolean straightflush(ArrayList<Card> input){
		return (sameSuit(input) && straight(input));
	}
	public boolean beats(Trick x){
		if (type == x.type){
			if (value.compareTo(x.value) > 0){
				return true;
			}
		}
		if (type > 3 && type > x.type){
			return true;
		}
		return false;
	}
	public String toString(){
		String retStr;
		if (type == 1){
			retStr= value.toString();
		}
		else if (type == 2){
			if (value.getV() == 6){
				retStr= "Pair of " + value.stringV() + "es";
			}
			else
				retStr= "Pair of " + value.stringV() + "s";
		}
		else if (type == 3){
			if (value.getV() == 6){
				retStr= "Triple " + value.stringV() + "es";
			}
			else
				retStr= "Triple " + value.stringV() + "s";
		}
		else if (type == 4){
			retStr= "Straight to " + value; 
		}
		else if (type == 5){
			retStr= value.stringS() + " Flush " + "(" + value + " high)";
		}
		else if (type == 6){
			if (value.getV() == 6){
				retStr= "House of " + value.stringV() + "es";
			}
			else
				retStr= "House of " + value.stringV() + "s";
		}
		else if (type == 7){
			retStr= value.stringV() + " Bomb";
		}
		else if (type == 8){
			retStr= "Straight Flush to " + value;
		}
		else
			retStr = null;
		return retStr;
	}
	public static void main(String[] args){
		ArrayList<Card> hand = new ArrayList<Card>();
		for(int x = 1;x != 4;x++){
// Adds three aces to the hand.
			hand.add(new Card(1,x));
		}
		System.out.println("Current hand: " + hand);
		System.out.println("Trick of hand: " + new Trick(hand));
		System.out.println("Same value of hand: " + flush(hand));
		hand = new ArrayList<Card>();
		for(int x = 1;x <= 5;x++){
			hand.add(new Card(x,4));   
		}
		System.out.println("Current hand: " + hand);
		System.out.println("Same suit of hand: " + sameSuit(hand));
		System.out.println("Straight of hand: " + straight(hand));
		System.out.println("Straight Flush of hand: " + straightflush(hand));
		hand = new ArrayList<Card>();
		hand.add(new Card(1,1));
		hand.add(new Card(1,2));
		hand.add(new Card(1,3));
		hand.add(new Card(1,4));
		hand.add(new Card(2,2));
		System.out.println("Current hand: " + hand);
		System.out.println("Trick of hand: " + new Trick(hand));
		System.out.println("Bomb of hand: " + bomb(hand));

		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card(3,4));
		hand2.add(new Card(3,1));
		hand2.add(new Card(3,2));
		hand2.add(new Card(4,1));
		hand2.add(new Card(4,2));
		System.out.println("Current hand: "+ hand2);
		System.out.println("Trick of hand: " + new Trick(hand2));
		System.out.println("Is house? " + house(hand2));
		System.out.println("Current hand: "+ hand2);
		System.out.println(new Trick(hand) + " beats " + new Trick(hand2) + ": " + (new Trick(hand)).beats(new Trick(hand2)));
		System.out.println(new Trick(hand2) + " beats " + new Trick(hand) + ": " + (new Trick(hand2)).beats(new Trick(hand)));
		hand = new ArrayList<Card>();
		for(int x = 10;x <= 14;x++){
			hand.add(new Card(x,4));
		}
		System.out.println(new Trick(hand));
		hand.clear();
		hand.add(new Card(1,4));
		System.out.println("An ace of spades:" + new Trick(hand));
	}  
}
