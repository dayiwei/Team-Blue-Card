import java.util.ArrayList;

public class Player extends ArrayList<Card>{

    private String name;
    private boolean won;
    public Player(String playername) {
        super();
        name = playername;
        won = false;
    }
    public ArrayList<Card> getHand(){
        return (ArrayList<Card>) this;
    }
    public String getName(){return name;}
    public Combo play(ArrayList<Card> x){
        for(Card y : x){
            if(contains(y)){
                remove(indexOf(y));
            }
        }
        return new Combo(x);
    }
    public String toString(){
        String ret = "";
        ret += name + "'s hand:\n";
        for(int count = 0; count < size(); count++){
            ret += Integer.toString(count + 1) + ". " + get(count) + "\n";
        }
        return ret.substring(0,ret.length() - 1);
    }
    public static void main(String[] args){
        Player a = new Player("Shein");
        for(int x = 1; x <= 13; x++){
            a.add(new Card(x,4));
        }
        System.out.print(a);
    }
}
