import java.util.ArrayList;
import java.util.Arrays;
public class GraphicCards{
	public static String getPicture(Card x){
		String ret = new String();
		if(x.getV() == 1){
			if(x.getS() == 1){
				ret += " _____ \n";
				ret += "|A ^  |\n";
				ret += "| / \\ |\n";
				ret += "| \\ / |\n";
				ret += "|  .  |\n";
				ret += "|____V|\n";

			}
			else if(x.getS() == 2){
				ret += " _____ \n";
				ret += "|A _  |\n";
				ret += "| ( ) |\n";
				ret += "|(_'_)|\n";
				ret += "|  |  |\n";
				ret += "|____V|\n";
			}
			else if(x.getS() == 3){
				ret += " _____ \n";
				ret += "|A_ _ |\n";
				ret += "|( v )|\n";
				ret += "| \\ / |\n";
				ret += "|  .  |\n";
				ret += "|____V|\n";
			}
			else if(x.getS() == 4){
				ret += " _____ \n";
				ret += "|A .  |\n";
				ret += "| /.\\ |\n";
				ret += "|(_._)|\n";
				ret += "|  |  |\n";
				ret += "|____V|\n";
			}
		}
		else if(x.getV() == 13){
			if(x.getS() == 1){
				ret += " _____ \n";
				ret += "|K  WW|\n";
				ret += "| /\\{)|\n";
				ret += "| \\/%%|\n";
				ret += "|  %%%|\n";
				ret += "|_%%%>|\n";
			}
			else if(x.getS() == 2){
				ret += " _____ \n";
				ret += "|K  WW|\n";
				ret += "| o {)|\n";
				ret += "|o o%%|\n";
				ret += "| |%%%|\n";
				ret += "|_%%%>|\n";
			}

			else if(x.getS() == 3){
				ret += " _____ \n";
				ret += "|K  WW|\n";
				ret += "|   {)|\n";
				ret += "|(v)%%|\n";
				ret += "| v%%%|\n";
				ret += "|_%%%>|\n";
			}
			else if(x.getS() == 4){
				ret += " _____ \n";
				ret += "|K  WW|\n";
				ret += "| ^ {)|\n";
				ret += "|(.)%%|\n";
				ret += "| |%%%|\n";
				ret += "|_%%%>|\n";
			}
		}
		else if(x.getV() == 12){
			if(x.getS() == 1){
				ret += " _____ \n";
				ret += "|Q  ww|\n";
				ret += "| /\\{(|\n";
				ret += "| \\/%%|\n";
				ret += "|  %%%|\n";
				ret += "|_%%%O|\n";
			}
			else if(x.getS() == 2){
				ret += " _____ \n";
				ret += "|Q  ww|\n";
				ret += "| o {(|\n";
				ret += "|o o%%|\n";
				ret += "| |%%%|\n";
				ret += "|_%%%O|\n";
			}
			else if(x.getS() == 3){
				ret += " _____ \n";
				ret += "|Q  ww|\n";
				ret += "|   {(|\n";
				ret += "|(v)%%|\n";
				ret += "| v%%%|\n";
				ret += "|_%%%O|\n";
			}

			else if(x.getS() == 4){
				ret += " _____ \n";
				ret += "|Q  ww|\n";
				ret += "| ^ {(|\n";
				ret += "|(.)%%|\n";
				ret += "| |%%%|\n";
				ret += "|_%%%O|\n";
			}
		}
		else if(x.getV() == 11){
			if(x.getS() == 1){
				ret += " _____ \n";
				ret += "|J  ww|\n";
				ret += "| /\\{)|\n";
				ret += "| \\/% |\n";
				ret += "|   % |\n";
				ret += "|__%%[|\n";
			}
			else if(x.getS() == 2){
				ret += " _____ \n";
				ret += "|J  ww|\n";
				ret += "| o {)|\n";
				ret += "|o o% |\n";
				ret += "| | % |\n";
				ret += "|__%%[|\n";
			}
			else if(x.getS() == 3){
				ret += " _____ \n";
				ret += "|J  ww|\n";
				ret += "|   {)|\n";
				ret += "|(v)% |\n";
				ret += "| v % |\n";
				ret += "|__%%[|\n";
			}

			else if(x.getS() == 4){
				ret += " _____ \n";
				ret += "|J  ww|\n";
				ret += "| ^ {)|\n";
				ret += "|(.)% |\n";
				ret += "| | % |\n";
				ret += "|__%%[|\n";
			}
		}
		else if(x.getS() == 1){ 
			if(x.getV() == 2){
				ret += " _____ \n";
				ret += "|2    |\n";
				ret += "|  o  |\n";
				ret += "|     |\n";
				ret += "|  o  |\n";
				ret += "|____Z|\n";
			}
			else if(x.getV() == 3){
				ret += " _____ \n";
				ret += "|3    |\n";
				ret += "| o o |\n";
				ret += "|     |\n";
				ret += "|  o  |\n";
				ret += "|____E|\n";
			}
			else if(x.getV() == 4){
				ret += " _____ \n";
				ret += "|4    |\n";
				ret += "| o o |\n";
				ret += "|     |\n";
				ret += "| o o |\n";
				ret += "|____h|\n";
			}
			else if(x.getV() == 5){
				ret += " _____ \n";
				ret += "|5    |\n";
				ret += "| o o |\n";
				ret += "|  o  |\n";
				ret += "| o o |\n";
				ret += "|____S|\n";
			}
			else if(x.getV() == 6){
				ret += " _____ \n";
				ret += "|6    |\n";
				ret += "| o o |\n";
				ret += "| o o |\n";
				ret += "| o o |\n";
				ret += "|____9|\n";
			}
			else if(x.getV() == 7){
				ret += " _____ \n";
				ret += "|7    |\n";
				ret += "| o o |\n";
				ret += "|o o o|\n";
				ret += "| o o |\n";
				ret += "|____L|\n";
			}
			else if(x.getV() == 8){
				ret += " _____ \n";
				ret += "|8    |\n";
				ret += "|o o o|\n";
				ret += "| o o |\n";
				ret += "|o o o|\n";
				ret += "|____8|\n";
			}       
			else if(x.getV() == 9){
				ret += " _____ \n";
				ret += "|9    |\n";
				ret += "|o o o|\n";
				ret += "|o o o|\n";
				ret += "|o o o|\n";
				ret += "|____6|\n";
			}
			else if(x.getV() == 10){
				ret += " _____ \n";
				ret += "|10 o |\n";
				ret += "|o o o|\n";
				ret += "|o o o|\n";
				ret += "|o o o|\n";
				ret += "|___0I|\n";
			}
		}
		else{
			if(x.getS() == 2){
				return getPicture(new Card(x.getV(),1)).replace('o','&');
			}
			else if(x.getS() == 3){
				return getPicture(new Card(x.getV(),1)).replace('o','v');
			}
			else if(x.getS() == 4){
				return getPicture(new Card(x.getV(),1)).replace('o','^');
			}
		}
		return ret;
	}
	public static String getCard(Card x){
		String s = getPicture(x) + x.stringV() + " of \n" + x.stringS() + "s\n";
		String ret = new String();
		for(String l : s.split("\n")){
			while(l.length() < 9){
				l += " ";
			}
			ret += l + "\n";
		}
		return ret;
	}
	public static String addStrings(ArrayList<String> x){
		ArrayList<String> lines = new ArrayList<String>();
		for(String s : x){
			ArrayList<String> rows = new ArrayList<String>(Arrays.asList(s.split("\n")));
			for(int index = 0;index < rows.size();index++){
				if(index >= lines.size()){
					lines.add(rows.get(index));
				}
				else{
					lines.set(index,lines.get(index) + rows.get(index));
				}
			}
		}
		String ret = new String();
		for(String line : lines){
			ret += line + "\n";
		}
		return ret;
	}
	private static String numbering(int x){
		String ret = x + ".\n";
		while(ret.length() == 3){
			ret = " " + ret;
		}
		for(int count = 0;count<7;count++){
			ret += "   \n";
		}
		return ret;
	}
	public static String toString(Trick input){
		ArrayList<Card> a = input.getCards();
		ArrayList<String> b = new ArrayList<String>();
		for(Card x : a){
			b.add(getCard(x));
		}
		return addStrings(b);
	}
	public static String toString(Player input){
		String ret = new String();
		for(int x = 0;x < input.size();x+= 6){
			int upper = Math.min(x + 6,input.size());
			ret += twoString(new ArrayList<Card>(input.subList(x,upper)),x + 1);
			ret += "\n";
		}
		return ret;
	}
	private static String twoString(ArrayList<Card> input,int count){
		ArrayList<String> strings = new ArrayList<String>();
		for(Card c : input){
			strings.add(numbering(count));
			strings.add(getCard(c));
			count++;
		}
		return addStrings(strings);
	}
	public static void main(String[] args){

		ArrayList<Card> a = new ArrayList<Card>();
		for(int x = 1;x<=5;x++){
			a.add(new Card(x,4));
		}
		System.out.println(getCard(new Card(1,4)));
		System.out.println(toString(new Trick(a)));
	}
}
	