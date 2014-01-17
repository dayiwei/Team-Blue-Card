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
	public int power(){
	/* Return the card's compartive power
	   from 1-52. The Three of Diamonds
	   would return 1 and the Two of Spades
	   would return 52
	*/
	   int position = value;
	   if (position <= 2){
	   		position += 13;
	   }
	   position = (position - 2) * 4 - 4 + suit;
	   return position;
	}
	public int getV(){
		return value;
	}
	public int getS(){
		return suit;
	}
	public int compareTo(Object x){
		int ipower = ((Card) x).power();
		if (power() > ipower){
			return 1;
		}
		else if (power() < ipower){
			return -1;
		}
		else{
			return 0;
		}
	}
	public boolean equalV(Card x){
		return value == x.value;
	}
	public boolean equalS(Card x){
		return suit == x.suit;
	}
	public boolean equals(Object x){
		return suit == ((Card)x).suit && value == ((Card)x).value;
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
		Card a = new Card(3,1);
		Card b = new Card(2,4);
		System.out.println("Card a : " + a);
		System.out.println("Card a position : " + a.power());
		System.out.println("Card b : " + b);
		System.out.println("Card b position : " + b.power());
		System.out.println("Compare a to b : " + a.compareTo(b));
	}
}