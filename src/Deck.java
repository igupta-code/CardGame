import java.util.ArrayList;
public class Deck {
    ArrayList<Card> cards;
    int cardsLeft;

    //constructor
    public Deck(String[] ranks, String[] suits,  int[] values){
        cardsLeft = 0;
        cards = new ArrayList<Card>();
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++){
                cards.add(new Card(ranks[i], suits[j], values[i]));
                cardsLeft++;
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

    public Card deal(){
        if (isEmpty())
            return null;

        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle(){
        Card holder = new Card("","", 0);
        int r = 0;
        for (int i = cards.size(); i >= 0; i--){
            r = (int)(Math.random()*(cards.size()));
            holder = cards.set(i, cards.get(r));
            cards.set(r, holder);
        }
    }



}
