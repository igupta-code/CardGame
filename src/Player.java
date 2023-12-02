import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int points;
    private String name;


    // constructors
    public Player(String name){
        points = 0;
        hand = new ArrayList<>();
        this.name = name;
    }

    public Player(String name, ArrayList<Card> hand){
        points = 0;
        //remember to make your deck before you make players
        this.hand = hand;
        this.name = name;
    }

    public int getPoints(){
        return points;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public void addPoints(int num){
        points += num;
    }

    public void addCard(Card newCard){
        hand.add(newCard);
    }

    public String toString(){
        return //name + " has " + points + " points \n" +
                name + "'s cards: " + hand;
    }
}
