import java.sql.SQLOutput;

public class Game {
    public static void main(String[] args) {
        //Instructions!
        System.out.println("Welcome to a game of Crazy 8's");
        System.out.println("Similar to uno, place a card down that " +
                "has the same number or suit as the one on the pile. Eight's " +
                "are wild! You can play them at anytime to change the suit to the " +
                "suit of the eight card.");

        //Make a deck and shuffle it
        String[] suits = {"diamonds, hearts, spades, club"};
        String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack", "Queen", "King" };
        int[] values = {1,2,3,4,5,6,7,8,9,10, 11, 12, 13};
        Deck deck = new Deck(suits, ranks, values);
        deck.shuffle();
    }
}
