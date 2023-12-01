import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    //instance variables
    private Scanner input;
    private Deck deck;
    private ArrayList<Card> userHand;
    private ArrayList<Card> compHand;
    private static Player user;
    private static Player comp;


    public Game(){
        input = new Scanner(System.in);
        //init and shuffle deck
        String[] suits = {"diamonds", "hearts", "spades", "club"};
        String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack", "Queen", "King" };
        int[] values = {1,2,3,4,5,6,7,8,9,10, 11, 12, 13};
        deck = new Deck(suits, ranks, values);
        deck.shuffle();

        //init the players
        System.out.println("Enter your name: ");
        String userName = input.nextLine();
        userHand = new ArrayList<>();
        compHand = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            userHand.add(i, deck.deal());
            compHand.add(i, deck.deal());
        }

        user = new Player(userName, userHand);
        comp = new Player("Computer", compHand);



    }

    public void printInstructions(){
        //Instructions!
        System.out.println("Welcome to a game of Crazy 8's");
        System.out.println("Similar to uno, place a card down that " +
                "has the same number or suit as the one on the pile. Eight's " +
                "are wild! You can play them at anytime to change the suit to the " +
                "suit of the eight card. If you can't play any card from your hand, draw " +
                "a card from the deck until you can play.");
        System.out.println("Whoever runs out of cards first or has the least number of " +
                "cards when the deck runs out wins!");



    }

    public void playGame(){
        //deal the first card
        while //the card is an 8 draw again

    }
    public static void main(String[] args) {
        Game g = new Game();
        System.out.println(user);
        System.out.println(comp);
        g.printInstructions();
        g.playGame();









    }
}
