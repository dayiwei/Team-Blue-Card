
/* Rules 

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
public class Combo{

    private String type;
    private int value;

    public Combo(ArrayList<Card> input) {
	sort(input);
	if (pair(input)) {
	    type ="pair";
	    value = input.get(0).power();
	}
	else if (triple(input)){
	    type = "triple";
	    value = input.get(0).power();
	}
	else if (house(input)) {
	    type = "house";
	    value = input.get(2).power();
	}
	else if (bomb(input)){
	    if(input.get(0).compareTo(input.get(1))==0)
		value = input.get(0).power();
	    else
		value = input.get(1).power();
	    type = "bomb";
	}
	else if (straight(input) && flush(input)){
	    type = "straight flush";
	    value = input.get(4).power();
	}	
	else if (straight(input)){
	    type = "straight";
	    value = input.get(4).power();
	}
	else if (flush(input)){
	    type = "flush";
	    value = input.get(4).power();
	}

    }

    private static boolean validate(ArrayList<Card> input){
	return (input.size() > 0 && (

				     pair(input) ||
				     triple(input) ||
				     straight(input) ||
				     flush(input) ||
				     house(input) ||
				     bomb(input)
				     )
		);
    }
    private static void sort(ArrayList<Card> input){
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

    private static boolean sameSuit(ArrayList<Card> input){
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
	    if(!(input.get(x -1).equalV(input.get(x)))){
		ret = false;
	    }
	}
	return ret;
    }

    private static boolean straight(ArrayList<Card> input){
	if(input.size() != 5)
	    return false;
	sort(input);
	boolean ret = true;
	for(int x = 1; x < input.size() ; x++){
	    int currentvalue = input.get(0).getV() + x;
	    if (currentvalue == 14){
		currentvalue = 1;
	    }
	    if(! (currentvalue == input.get(x).getV())){
		ret = false;
	    }
	}
	return ret;
    }

    private static boolean house(ArrayList<Card> input) {
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
	return (count == 1 || count == 4);
    }
    private static boolean straightflush(ArrayList<Card> input){
	return (sameSuit(input) && straight(input));
    }
    public static void main(String[] args){
	ArrayList<Card> hand = new ArrayList<Card>();
	for(int x = 1;x != 4;x++){
	    // Adds three aces to the hand.
	    hand.add(new Card(1,x));
	}
	System.out.println("Current hand: " + hand);
	System.out.println("Same value of hand: " + flush(hand));
	hand = new ArrayList<Card>();
	for(int x = 14;x >= 10;x--){
	    hand.add(new Card(x,4));	
	}
	System.out.println("Current hand: " + hand);
	sort(hand);
	System.out.println("Sorted hand: " + hand);
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
	System.out.println("Bomb of hand: " + bomb(hand));
		
	hand = new ArrayList<Card>();
	hand.add(new Card(3,4));
	hand.add(new Card(3,1));
	hand.add(new Card(3,2));
	hand.add(new Card(4,1));
	hand.add(new Card(4,2));
	System.out.println("Current hand: "+ hand);
	System.out.println("Is house? " + house(hand));
	System.out.println("Current hand: "+ hand);
    }
}
