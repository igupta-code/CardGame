import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
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
        this.game = game;
        //imageO = new ImageIcon("Cards/O.png").getImage();
        instructions = new ImageIcon("Resources/instructions.png").getImage();
        table = new ImageIcon("Resources/table.png").getImage();
        cardBack = new ImageIcon("Resources/Cards/back.png").getImage();
        lost = new ImageIcon("Resources/lost.png").getImage();
        tie = new ImageIcon("Resources/tie.png").getImage();
        won = new ImageIcon("Resources/won.png").getImage();

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setVisible(true);
    }

    public void paint(Graphics g){
        if(!game.getHasStarted()){
            g.drawImage(instructions, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        }

       if(game.getHasStarted() && !game.getHasWon()){
           g.drawImage(table, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
           int yPos = WINDOW_HEIGHT/2 - Card.CARD_HEIGHT/2;
           g.drawImage(cardBack, WINDOW_WIDTH/2 - 2*Card.CARD_WIDTH,yPos, Card.CARD_WIDTH, Card.CARD_HEIGHT, this);
           game.getPile().draw(g, WINDOW_WIDTH/2 + Card.CARD_WIDTH, yPos);


           for (int i = 0; i < game.getUserHand().size(); i++){
               // Spacing determines the leftmost x coordinate of card in player's hand
               int spacing = BUFFER_X + i*((WINDOW_WIDTH - 2*BUFFER_X)/game.getUserHand().size());
               game.getUserHand().get(i).draw(g, spacing, WINDOW_HEIGHT - BUFFER_Y - Card.CARD_HEIGHT);

               g.setColor(Color.BLACK);
               g.setFont(new Font("Serif", Font.PLAIN, 30));
               g.drawString(Integer.toString(i), spacing + Card.CARD_WIDTH/2, WINDOW_HEIGHT-BUFFER_Y + LABEL_OFFSET);
           }
           for (int i = 0; i < game.getCompHand().size(); i++){
               int spacing = BUFFER_X + i*((WINDOW_WIDTH - 2*BUFFER_X)/game.getCompHand().size());
               g.drawImage(cardBack, spacing, BUFFER_Y, Card.CARD_WIDTH, Card.CARD_HEIGHT, this);
           }
       }

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
