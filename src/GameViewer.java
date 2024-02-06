import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    public static final int WINDOW_WIDTH = 800,
            WINDOW_HEIGHT = 800,
            BUFFER_X = (int)(WINDOW_WIDTH*0.2),
            BUFFER_Y = (int)(WINDOW_HEIGHT*0.2),
            SIDE_LENGTH = (int)(WINDOW_WIDTH*0.19),
            LABEL_OFFSET = (int)(WINDOW_WIDTH*0.05);
    public final String TITLE = "Crazy 8's";


    private Game game;
    public GameViewer(Game game){
        this.game = game;
        //imageO = new ImageIcon("Cards/O.png").getImage();


        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setVisible(true);
    }

    public void paint(Graphics g){
       for(int i = 0; i < game.getUserHand().size(); i++){
           //huh
           game.getUserHand().get(i).draw(g, 100*i,100);
       }
        //g.drawImage(game.getDeck().deal().getPic(), 0, 0, 100, (int)(150*1.7106038292), this);
    }
}
