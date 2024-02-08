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
            table;


    private Game game;
    public GameViewer(Game game){
        this.game = game;
        //imageO = new ImageIcon("Cards/O.png").getImage();
        instructions = new ImageIcon("Resources/instructions.png").getImage();
        table = new ImageIcon("Resources/table.png").getImage();


        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setVisible(true);
    }

    public void paint(Graphics g){
        if(!game.getHasStarted()){
            g.drawImage(instructions, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        }
       if(game.getHasStarted()) {
           g.drawImage(table, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
           for (int i = 0; i < game.getUserHand().size(); i++) {
               // Spacing determines the leftmost x coordinate of card in player's hand
               int spacing = BUFFER_X + i*((WINDOW_WIDTH - 2*BUFFER_X)/game.getUserHand().size());
               game.getUserHand().get(i).draw(g, spacing, WINDOW_HEIGHT - BUFFER_Y - Card.CARD_HEIGHT);

               g.setColor(Color.BLACK);
               g.setFont(new Font("Serif", Font.PLAIN, 30));
               g.drawString(Integer.toString(i), spacing + Card.CARD_WIDTH/2, WINDOW_HEIGHT-BUFFER_Y + LABEL_OFFSET);
           }
       }
    }
}
