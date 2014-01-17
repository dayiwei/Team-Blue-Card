
/* Rules 

   Single cards: Any card from the deck, ordered by rank with suit being the tie-breaker. (For instance, A♠ beats A♥, which beats K♠.)

   Pairs: Any two cards of matching rank, ordered as with singular cards by the card of the higher suit. (A pair consisting of the K♠ and K♣ beats a pair consisting of K♥ and K♦.)

   Triples: Three equal ranked cards, three twos are highest, then aces, kings, etc. down to three threes, which is the lowest triple. In some variations, a triple can only be played as part of a 5-card hand.

   5-card hand: There are five (var. 2) different valid 5-card poker hands, ranking, from low to high, as follows (the same ranking as in poker):

   Straight (also known as a snake in Cantonese): Any 5 cards in a sequence (but not all of the same suit). Rank is determined by the value of the last card in the sequence, with the suit used only as a tie-breaker. Therefore 3-4-5-6-7 > 2-3-4-5-6, since 6 is considered the largest card in the 2-3-4-5-6 straight. The largest straight is 10-J-Q-K-A (J-Q-K-A-2 is invalid in some variations), while the smallest straight is A-2-3-4-5.

   Flush (also known as a flower): Any 5 cards of the same suit (but not in a sequence). Rank is determined by highest value card and then by highest suit. In some popular variations, flushes are not permitted as a playable hand, and thus it is the lowest possible combination.

   Full House: a composite of a three-of-a-kind combination and a pair. Rank is determined by the value of the triple, regardless of the value of the pair. Also known as a Fullen.

   Four of a kind + One card (nicknamed King Kong, tiki, or Bomb): Any set of 4 cards of the same rank, plus any 5th card. (A 4 of a kind cannot be played unless it is played as a 5-card hand) Rank is determined by the value of the 4 card set, regardless of the value of the 5th card. It is also known as a poker. (Some play the Four of a kind hand as the beat all, therefore nicknamed the bomb, King Kong, or also tiki.)

   Straight Flush: A composite of the straight and flush: five cards in sequence in the same suit. Ranked the same as straights, suit being a tie-breaker. (Sometimes also played as a bomb or tiki, larger than a Four of a Kind)
*/

import java.util.ArrayList;

public class Combo {
    private ArrayList<Card> combo;
    private String type;
    private Card max;
    public static String checkHand(ArrayList<Card> input){
	if (input.size() == 1){
	    return "Single";
	}
	else if (input.size() == 2){
	    if(sameValue(input))
		return "Double";
	}
	else if (input.size() == 3){
	    if(sameValue(input))
		return "Triple";
	}
	else if (input.size() == 4){
	    return "Invalid";
	}
	else if (input.size() == 5){
	    if (bomb(input)
		return "Bomb";
	    else if (house(input))
		return "Full House";
	    else if (straight(input) && sameSuit(input))
		return "Straight Flush";
	    else if (straight(input))
		return "Straight";
	    else if (sameSuit(input))
		return "Flush";
	}
	return "Invalid";
    } 
    private static void sort(ArrayList<Card> input){
	for(int i=1; i<input.size();i++)
	    for (int i2=0;i2<i;i2++)
		if (input.get(i2).getV()>input.get(i).getv()){
		    input.add(i2, input.get(i));
		    input.remove(
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
    private static boolean straight(ArrayList<Card> input){
    	sort(input);
    	boolean ret = true;
    	for(int x = 1; x < input.size() ; x++){
    		currentvalue = input.get(0).get() + x;
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
}
