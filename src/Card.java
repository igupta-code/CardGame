import java.awt.*;

public class Card {
    private int point;
    private String suit;
    private String rank;
    private Image pic;
    private GameViewer window;
    public static final int CARD_WIDTH = 100;
    public static final int CARD_HEIGHT = (int)(100*1.7106038292);

    //constructor
    public Card(String rank, String suit, int point, Image pic, GameViewer window){

        this.suit = suit;
        this.rank = rank;
        this.point = point;
        this.pic = pic;
        this.window = window;
    }

    //getters and setters
    public String getSuit(){
        return suit;
    }
    public void setSuit(String suit){
        this.suit = suit;
    }

    public String getRank(){
        return rank;
    }
    public void setRank(String rank){
        this.rank = rank;
    }

    public int getPoint(){
        return point;
    }
    public void setPoint(int point){
        this.point = point;
    }

    public String toString(){
        return rank + " of " + suit;
    }

    public Image getPic() {
        return pic;
    }
    public void draw(Graphics g, int xTop, int yTop){
        g.setColor(Color.black);

        g.drawImage(pic, xTop,yTop, CARD_WIDTH,CARD_HEIGHT, window);
//        g.drawRect(topX, topY, TicTacToeViewer.SIDE_LENGTH, TicTacToeViewer.SIDE_LENGTH);
//
//        if(this.isWinningSquare){
//            g.setColor(Color.GREEN);
//            g.fillRect(topX, topY, TicTacToeViewer.SIDE_LENGTH, TicTacToeViewer.SIDE_LENGTH);
//        }
//
//
//        // Prints X or Y image in corret place if needed
//        int xPos = topX + TicTacToeViewer.SIDE_LENGTH/2 - imageSize/2;
//        int yPos = topY + TicTacToeViewer.SIDE_LENGTH/2 - imageSize/2;
//        // fills in X/O
//        if(this.marker.equals("X")){
//            g.drawImage(imageX, xPos, yPos, imageSize,imageSize, t);
//        }
//        else if(this.marker.equals("O")){
//            g.drawImage(imageO, xPos, yPos, imageSize,imageSize, t);
        }


}

