// Isha Gupta
// Feb 16, 2024
/*
The Game class controls the whole game of Crazy 8s. The constructor initializes the player, the deck, pile, and
a GameViewer class allowing for the sharing of data. Key methods print the instructions(on the terminal),
the playGame method facilitates the game, and of course, the main method puts it together.
 */

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    // Instance variables
    private Scanner input = new Scanner(System.in);;
    private Deck deck;
    // Private ArrayList<Card> pile;
    private Card pile;
    private ArrayList<Card> userHand;
    private ArrayList<Card> compHand;
    private static Player user;
    private boolean hasWon,
                    hasStarted;
    private int winner;
    private GameViewer window;
    private Image[] deckImages;

    public Game(){
        // Sets all the information in order required to initialize deck, then shuffles
        String[] suits = { "spades", "hearts", "diamonds", "club"};
        String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack", "Queen", "King" };
        int[] values = {1,2,3,4,5,6,7,8,9,10, 11, 12, 13};
        deckImages = new Image[53];
        for(int i = 0; i < deckImages.length; i++){
            deckImages[i] = new ImageIcon("Resources/Cards/" + (i+1) + ".png").getImage();
        }
        window = new GameViewer(this);
        deck = new Deck(ranks, suits, values, deckImages, window);
        deck.shuffle();

        // Deal hands to each player and initializes the user as a Player
        System.out.println("Enter your name: ");
        String userName = input.nextLine();
        userHand = new ArrayList<>();
        compHand = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            userHand.add(i, deck.deal());
            compHand.add(i, deck.deal());
        }
        user = new Player(userName, userHand);

        // Initialize the pile
        pile = new Card("", "", 0, null, null);

        // Variables that change the state of the game/convey the winner to GameViewer
        hasStarted = false;
        hasWon = false;
        winner = -1;
    }

    // Getters to share data with GameViewer
    public ArrayList<Card> getCompHand() {
        return compHand;
    }

    public ArrayList<Card> getUserHand() {
        return userHand;
    }

    public Card getPile(){
        return pile;
    }

    public boolean getHasStarted(){
        return hasStarted;
    }

    public boolean getHasWon() {
        return hasWon;
    }

    public int getWinner(){
        return winner;
    }

    // Prints instruction(on terminal) and sets hasStarted to true, indicating a change in state
    public void printInstructions(){
        // Instructions!
        System.out.println("Welcome to a game of Crazy 8's");
        System.out.println("Similar to uno, place a card down that " +
                "has the same number or suit as the one on the pile. Eight's are\n" +
                "wild! You can play them at anytime to change the suit to the suit of\n" +
                " the eight card. If you can't play any card from your hand, draw a card.");
        System.out.println("Whoever runs out of cards first or has the least number of " +
                "cards when the deck runs out wins! \n");
        System.out.println();
        hasStarted = true;
    }

    // This method alternates between the player's/computer's turn until someone wins or ties.
    public void playGame(){
        // Deal the first card
        pile = deck.deal();
        System.out.println("pile: " + pile);
        window.repaint();
        // While the game isn't done, alternate between player/user turns
        while (!hasWon){
            if (playerTurn()){
                checkWin();
                if (hasWon) {
                    window.repaint();
                    System.out.println(checkWin());
                    return;
                }
                System.out.println("pile: " + pile);
                System.out.println();
                window.repaint();

                System.out.println("Computer's turn");
                computerTurn();
                System.out.println("Computer has " + compHand.size() + " cards.");
                System.out.println();

                System.out.println("pile: " + pile);
                System.out.println(user);
                window.repaint();
            }
            checkWin();
        }
        System.out.println(checkWin());
        window.repaint();
    }

    // Sets boolean hasWon and returns the print statement depending on who's won
    // The winner variable is shared with gameViewer so the window know what message to print as well.
    public String checkWin(){
        if(userHand.isEmpty()){
            hasWon = true;
            winner = 0;
            return "Congrats you won!";
        }
        if(compHand.isEmpty()){
            hasWon = true;
            winner = 1;
            return "Oh no, the computer won!";
        }
        if(deck.isEmpty()){
            hasWon = true;
            if(userHand.size() < compHand.size()){
                winner = 0;
                return "Congrats you won!";
            }
            if(userHand.size() > compHand.size()){
                winner = 1;
                return "Oh no, the computer won!";
            }
            else{
                winner = 2;
                return "It's a tie!";
            }
        }
        return null;
    }

    // Plays for the computer
    public void computerTurn(){
        int play = 0;
        while (!checkCard(compHand.get(play))){
            play++;
            if (play >= compHand.size()){
                compHand.add(deck.deal());
                return;
            }
        }
        pile = compHand.remove(play);
    }

    // Returns true once player's turn is over
    public boolean playerTurn(){
        // Asks for player input
        System.out.println("Your turn! Enter the index of your card. If you can't play, enter -1.");
        int play = -2;
        while(play == -2) {
            try {
                play = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Please enter an int!");
            }
        }

        if(play < 0){
            userHand.add(deck.deal());
            return true;
        }

        // Checks if card is valid
        if(play < userHand.size() && checkCard(userHand.get(play))){
            pile = userHand.remove(play);
            return true;
        }
        else{
            System.out.println("invalid card");
            return false;
        }
    }

    // Returns true if suits/value match or if it's an eight
    public boolean checkCard(Card card){
        if(pile.getSuit().equals(card.getSuit())){
            return true;
        }
        int pilePt = pile.getPoint();
        int cardPt = card.getPoint();
        return pilePt == cardPt || pilePt == 8 || cardPt == 8;
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.printInstructions();
        System.out.println(user);
        g.playGame();
    }
}
