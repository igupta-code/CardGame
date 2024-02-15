// Isha Gupta
// Feb 16, 2024
/*
The Deck class initializes all the cards and adds them to an ArrayList of Cards(the deck).
Additionally, it performs the functions of a deck: dealing a card, shuffling the deck
and seeing if the deck is empty.
 */

import java.awt.*;
import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor initializes each card and adds it to the ArrayList cards
    public Deck(String[] ranks, String[] suits,  int[] values, Image[] deckImages, GameViewer window){
        cardsLeft = 0;
        cards = new ArrayList<Card>();
        for(int i = 0; i < ranks.length; i++) {
            for(int j = 0; j < suits.length; j++){
                cards.add(new Card(ranks[i], suits[j], values[i], deckImages[cardsLeft], window));
                cardsLeft++;
            }
        }
    }

    public boolean isEmpty(){
        if(cardsLeft == 0)
            return true;
        return false;
    }

    public int getCardsLeft(){
        return cardsLeft;
    }

    public Card deal(){
        if(isEmpty())
            return null;

        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle(){
        cardsLeft = cards.size();
        Card holder;
        int r = 0;
        for(int i = cardsLeft-1; i >= 0; i--){
            r = (int)(Math.random()*(cards.size()));
            holder = cards.set(i, cards.get(r));
            cards.set(r, holder);
        }
    }
}
