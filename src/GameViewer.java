// Isha Gupta
// Feb 16, 2024
/*
The GameViewer class displays the game on the window. It creates a new window, initializes and prints background
images, prints instructions, decides the position of the cards depending on the size of the hand, and prints a wining
message.
 */

import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    // Variables for formatting and spacing
    public static final int WINDOW_WIDTH = 1000,
            WINDOW_HEIGHT = 800,
            BUFFER_X = (int)(WINDOW_WIDTH*0.2),
            BUFFER_Y = (int)(WINDOW_HEIGHT*0.15),
            SIDE_LENGTH = (int)(WINDOW_WIDTH*0.19),
            LABEL_OFFSET = (int)(WINDOW_WIDTH*0.05);
    public final String TITLE = "Crazy 8's";
    private Image instructions,
            table,
            cardBack,
            lost,
            tie,
            won;
    private Game game;

    public GameViewer(Game game){
        // Creating an instance of Game allows data sharing/access to variables and methods in game
        this.game = game;

        // Initializes background images and the image for the back of the card
        instructions = new ImageIcon("Resources/instructions.png").getImage();
        table = new ImageIcon("Resources/table.png").getImage();
        cardBack = new ImageIcon("Resources/Cards/back.png").getImage();
        lost = new ImageIcon("Resources/lost.png").getImage();
        tie = new ImageIcon("Resources/tie.png").getImage();
        won = new ImageIcon("Resources/won.png").getImage();

        // Creates a new window
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setVisible(true);
    }

    public void paint(Graphics g){
        // Prints the opening screen with instructions
        if(!game.getHasStarted()){
            g.drawImage(instructions, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        }

        // This gets called while the game is being played
       if(game.getHasStarted() && !game.getHasWon()){
           // Sets up: draws table backdrop, the deck image, and the pile
           g.drawImage(table, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
           int yPos = WINDOW_HEIGHT/2 - Card.CARD_HEIGHT/2;
           g.drawImage(cardBack, WINDOW_WIDTH/2 - 2*Card.CARD_WIDTH,yPos, Card.CARD_WIDTH,
                   Card.CARD_HEIGHT, this);
           game.getPile().draw(g, WINDOW_WIDTH/2 + Card.CARD_WIDTH, yPos);

           // Prints out the user's hand and the computer's hand with spacing based off of hand's size
           for (int i = 0; i < game.getUserHand().size(); i++){
               // Prints user's cards
               int spacing = BUFFER_X + i*((WINDOW_WIDTH - 2*BUFFER_X)/game.getUserHand().size());
               game.getUserHand().get(i).draw(g, spacing, WINDOW_HEIGHT - BUFFER_Y - Card.CARD_HEIGHT);

               // Dynamically prints the numbers below the cards
               g.setColor(Color.BLACK);
               g.setFont(new Font("Serif", Font.PLAIN, 30));
               g.drawString(Integer.toString(i), spacing + Card.CARD_WIDTH/2,
                       WINDOW_HEIGHT-BUFFER_Y + LABEL_OFFSET);
           }
           for (int i = 0; i < game.getCompHand().size(); i++){
               int spacing = BUFFER_X + i*((WINDOW_WIDTH - 2*BUFFER_X)/game.getCompHand().size());
               g.drawImage(cardBack, spacing, BUFFER_Y, Card.CARD_WIDTH, Card.CARD_HEIGHT, this);
           }
       }

       // Prints the final message depending on who won the game
       if(game.getHasWon()){
           if(game.getWinner() == 0){
               g.drawImage(won, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
           }
           else if(game.getWinner() == 1){
               g.drawImage(lost, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
           }
           else{
               g.drawImage(tie, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
           }
       }
    }
}
