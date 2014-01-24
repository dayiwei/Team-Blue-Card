 
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

      private ArrayList<Card> cards;
   	private int type;
   	private Card value;

   	public Combo(ArrayList<Card> input) {
         cards = input;
   		if(!(validate(input)==true)){
   			type = 0;
   			value = null;
   		}
   		else {
   			sort(input);
            if (input.size() == 1){
               type = 1;
               value = input.get(0);
            }
   			if (pair(input)) {
   				type = 2;
   				value = input.get(0);
   			}
   			else if (triple(input)){
   				type = 3;
   				value = input.get(0);
   			}
   			else if (house(input)) {
   				type = 6;
               Card x = input.get(1);
               int count = 0;
               for(Card y : input){
                  if (y.equalV(x)){
                     count++;
                  }
               }
               if (count == 3){
                  value = x;
               }
               else {
                  value = input.get(4);
               }
   			}
   			else if (bomb(input)){
   				if(input.get(0).getV()==(input.get(1).getV()))
				    value = input.get(0);
   				else
				    value = input.get(1);
   				type = 7;
   			}
   			else if (straightflush(input)){
   				type = 8;
   				value = input.get(4);
   			}	
   			else if (straight(input)){
   				type = 4;
   				value = input.get(4);
   			}
   			else if (flush(input)){
   				type = 5;
   				value = input.get(4);
   			}
   		}

   	}
   	
   	public int getCT() {
   		return type;
   	}
   	public Card getCV() {
   		return value;
   	}

   	public int compareTo(Combo x) {
	    // if(! x.getCT().equals(type))
	    // 	return -2;
   		if (value.compareTo(x.getCV())>0)
   			return 1;
   		else if (value.compareTo(x.getCV())<0)
   			return -1;
   		else 
   			return 0;

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
      public boolean beats(Combo x){
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
         if (type == 1){
            return value.toString();
         }
         else if (type == 2){
            if (value.getV() == 6){
               return "Pair of " + value.stringV() + "es";
            }
            return "Pair of " + value.stringV() + "s";
         }
         else if (type == 3){
            if (value.getV() == 6){
               return "Triple " + value.stringV() + "es";
            }
            return "Triple " + value.stringV() + "s";
         }
         else if (type == 4){
            return "Straight to " + value; 
         }
         else if (type == 5){
            return value.stringS() + " Flush " + "(" + value + " high)";
         }
         else if (type == 6){
            if (value.getV() == 6){
               return "House of " + value.stringV() + "es";
            }
            return "House of  " + value.stringV() + "s";
         }
         else if (type == 7){
            return value.stringV() + " Bomb";
         }
         else if (type == 8){
            return "Straight Flush to " + value;
         }
         return null;
      }
   	public static void main(String[] args){
   		ArrayList<Card> hand = new ArrayList<Card>();
   		for(int x = 1;x != 4;x++){
	    // Adds three aces to the hand.
   			hand.add(new Card(1,x));
   		}
   		System.out.println("Current hand: " + hand);
         System.out.println("Combo of hand: " + new Combo(hand));
   		System.out.println("Same value of hand: " + flush(hand));
   		hand = new ArrayList<Card>();
   		for(int x = 14;x >= 10;x--){
   			hand.add(new Card(x,4));	
   		}
   		System.out.println("Current hand: " + hand);
   		sort(hand);
         System.out.println("Combo of hand: " + new Combo(hand));
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
         System.out.println("Combo of hand: " + new Combo(hand));
   		System.out.println("Bomb of hand: " + bomb(hand));
   		
   		ArrayList<Card> hand2 = new ArrayList<Card>();
   		hand2.add(new Card(3,4));
   		hand2.add(new Card(3,1));
   		hand2.add(new Card(3,2));
   		hand2.add(new Card(4,1));
   		hand2.add(new Card(4,2));
   		System.out.println("Current hand: "+ hand2);
         System.out.println("Combo of hand: " + new Combo(hand2));
   		System.out.println("Is house? " + house(hand2));
   		System.out.println("Current hand: "+ hand2);
         System.out.println(new Combo(hand) + " beats " + new Combo(hand2) + ": " + (new Combo(hand)).beats(new Combo(hand2)));
         System.out.println(new Combo(hand2) + " beats " + new Combo(hand) + ": " + (new Combo(hand2)).beats(new Combo(hand)));
   	}
   }
