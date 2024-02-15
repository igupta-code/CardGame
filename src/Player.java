// Isha Gupta
// Feb 16, 2024
/*
The Player class keeps track of the hand and name of each player.
It includes a way to add cards into the players hand, and to
get a players hand, allowing us to print it and find its size.
 */

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private String name;

    // Each player has an arrayList with the cards in their hand and a name for the console display
    public Player(String name, ArrayList<Card> hand){
        this.hand = hand;
        this.name = name;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    public void addCard(Card newCard){
        hand.add(newCard);
    }

    public String toString(){
        return name + "'s cards: " + hand;
    }
}
