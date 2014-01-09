public class Card implements Comparable{
	private int value;
	private int suit;
	public Card(int v,int s){
		value = v;
		suit = s;
	}
	public Card(){
		this(1,4);
	}
	public int compareTo(Object x){
		Card card = (Card) x;
		if (value == card.value){
			if (suit == card.suit){
				return 0;
			}
			else if (suit > card.suit){
				return 1;
			}
			else{
				return -1;
			}
		}
		else {
			if (value > card.value){
				return 1;
			}
			else
				return -1;
		}
	}
	public String toString(){
		String s = "";
		if (value == 1){
			s += "Ace";
		}
		else if (value == 11){
			s += "Jack";
		}
		else if (value == 12){
			s += "Queen";
		}
		else if (value == 13){
			s += "King";
		}
		else{
			s += value;
		}
		s += " of ";
		if (suit == 1){
			s += "Diamonds";
		}
		else if (suit == 2){
			s += "Clubs";
		}
		else if (suit == 3){
			s += "Hearts";
		}
		else if (suit == 4){
			s += "Spades";
		}
		return s;
	}
	public static void main(String[] args){
		Card a = new Card(13,4);
		Card b = new Card(5,2);
		System.out.println("Card a : " + a);
		System.out.println("Card b : " + b);
		System.out.println("Compare a to b : " + a.compareTo(b));
	}
}