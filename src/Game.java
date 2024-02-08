import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    //instance variables
    private Scanner input = new Scanner(System.in);;
    private Deck deck;
    //private ArrayList<Card> pile;
    private Card pile;
    private ArrayList<Card> userHand;
    private ArrayList<Card> compHand;
    private static Player user;
    private static Player comp;
    private boolean hasWon,
                    hasStarted;
    private GameViewer window;
    private Image[] deckImages;

    // Constructor
    public Game(){
        //init and shuffle deck

        String[] suits = { "spades", "hearts", "diamonds", "club"};
        String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack", "Queen", "King" };
        int[] values = {1,2,3,4,5,6,7,8,9,10, 11, 12, 13};
        //imageX = new ImageIcon("Resources/X.png").getImage();
        deckImages = new Image[53];
        for(int i = 0; i < deckImages.length; i++){
            deckImages[i] = new ImageIcon("Resources/Cards/" + (i+1) + ".png").getImage();
            //set the card image equal to this card
            // game.getDeck().deal()
        }
        window = new GameViewer(this);
        deck = new Deck(ranks, suits, values, deckImages, window);
        window = new GameViewer(this);
        deck.shuffle();

        //Init the players and deal hands
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

        // Initialize the pile
        //pile = new ArrayList<>();
        pile = new Card("", "", 0, null, null);

        hasWon = false;
        hasStarted = false;
    }

    // Getters
    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Card> getCompHand() {
        return compHand;
    }

    public ArrayList<Card> getUserHand() {
        return userHand;
    }

    public boolean getHasWon() {
        return hasWon;
    }

    public boolean getHasStarted(){
        return hasStarted;
    }


    public void printInstructions(){
        //Instructions!
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


    public void playGame(){
        //deal the first card
        pile = deck.deal();
        System.out.println("pile: " + pile);
        window.repaint();
        // while the game isn't done, alternate between player/user turns
        while (!hasWon){
            if (playerTurn()){
                checkWin();
                if (hasWon) {
                    System.out.println(checkWin());
                    return;
                }
                window.repaint();
                System.out.println("pile: " + pile);
                System.out.println();
                window.repaint();

                System.out.println("Computer's turn");
                computerTurn();
                System.out.println("Computer has " + compHand.size() + " cards.");
                System.out.println();
                window.repaint();

                System.out.println("pile: " + pile);
                System.out.println(user);
                window.repaint();
            }
            checkWin();
        }
        System.out.println(checkWin());
        window.repaint();

        // do i need all these repaints??
    }

    // sets boolean hasWon and returns the print statement depending on who's won
    public String checkWin(){
        if(userHand.isEmpty()){
            hasWon = true;
            return "Congrats you won!";
        }
        if (compHand.isEmpty()){
            hasWon = true;
            return "Oh no, the computer won!";
        }
        if (deck.isEmpty()){
            hasWon = true;
            if (userHand.size() < compHand.size()){
                return "Congrats you won!";
            }
            if (userHand.size() > compHand.size()){
                return "Oh no, the computer won!";
            }
            else
                return "It's a tie!";
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
        //ask to go
        System.out.println("Your turn! Enter the index of your card. If you can't play, enter -1.");
        int play = input.nextInt();
        if (play < 0){
            userHand.add(deck.deal());
            return true;
        }
        //check if its valid
        if (play < userHand.size() && checkCard(userHand.get(play))){
            pile = userHand.remove(play);
            return true;

        }
        else{
            System.out.println("invalid card");
            return false;
        }

    }

    //returns true if suits/value match or if it's an eight
    public boolean checkCard(Card card){
        if (pile.getSuit().equals(card.getSuit())){
            return true;
        }
        int pilePt = pile.getPoint();
        int cardPt = card.getPoint();
        return pilePt == cardPt || pilePt == 8 || cardPt == 8;
    }


    // Main!!
    public static void main(String[] args) {
        Game g = new Game();
        g.printInstructions();
        System.out.println(user);
        g.playGame();

    }
}
