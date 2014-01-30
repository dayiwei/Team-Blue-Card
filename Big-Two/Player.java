import java.util.ArrayList;

public class Player extends ArrayList<Card>{
    private boolean won;
    private String name;
    public Player(String playername) {
        super();
        name = playername;
        won = false;
    }
    public ArrayList<Card> getHand(){
        return (ArrayList<Card>) this;
    }
    public String getName(){return name;}
    public Trick play(ArrayList<Card> x){
        for(Card y : x){
            if(contains(y)){
                remove(indexOf(y));
            }
        }
        return new Trick(x);
    }
    public boolean won(){
        return won;
    }
    public void checkwin(){
        if (size() == 0){
            won = true;
        }
        else if(size() == 13 && Trick.sameSuit(this)){
            won = true;
        }
    }
    public String toString(){
        String ret = "Your Cards:\n";
        ret += GraphicCards.toString(this);
        return ret;
    }
    public static void main(String[] args){
        Player a = new Player("Shein");
        for(int x = 13; x >= 1; x--){
            a.add(new Card(x,4));
        }
        System.out.println(a);
        ArrayList<Card> b = new ArrayList<Card>();
        b.add(new Card(1,4));
        System.out.println(b);
        System.out.println(a.play(b));
    }
}
