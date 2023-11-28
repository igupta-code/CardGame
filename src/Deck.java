import java.util.ArrayList;
public class Deck {
    ArrayList<Card> cards;
    int cardsLeft;

    //constructor
    public Deck(String[] ranks, String[] suits,  int[] values){
        cardsLeft = 0;
        for (int i = 0; i < ranks.length; i++) {
            for(int j = 0; j < suits.length; j++){
                for (int m = 0; m < values.length; m++) {
                    Card card = new Card(ranks[i], suits[j], values[m]);
                    cards.add(card);
                    cardsLeft++;
                }
            }
        }
    }

    public boolean isEmpty(){
        if (cardsLeft == 0)
            return true;
    }
    public int getCardsLeft(){
        return cardsLeft;
    }

}
