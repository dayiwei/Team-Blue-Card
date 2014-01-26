public class Card implements Comparable{
	private int value;
	private int suit;
	public Card(int v,int s){
		value = (v - 1) % 13 + 1;
		suit = (s - 1) % 13 + 1;
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
	public String stringS(){
		if(suit == 1){
			return "Diamond";
		}
		else if(suit == 2){
			return "Club";
		}
		else if(suit == 3){
			return "Heart";
		}
		else if(suit == 4){
			return "Spade";
		}
		else {
			return null;
		}
	}
	public String stringV(){
		if(value == 1){
			return "Ace";
		}
		else if(value == 2){
			return "Deuce";
		}
		else if(value == 3){
			return "Three";
		}
		else if(value == 4){
			return "Four";
		}
		else if(value == 5){
			return "Five";
		}
		else if(value == 6){
			return "Six";
		}
		else if(value == 7){
			return "Seven";
		}
		else if(value == 8){
			return "Eight";
		}
		else if(value == 9){
			return "Nine";
		}
		else if(value == 10){
			return "Ten";
		}
		else if(value == 11){
			return "Jack";
		}
		else if(value == 12){
			return "Queen";
		}
		else if(value == 13){
			return "King";
		}
		else{
			return null;
		}
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
		return stringV() + " of " + stringS() + "s";
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