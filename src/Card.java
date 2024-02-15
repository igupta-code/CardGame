// Isha Gupta
// Feb 16, 2024
/*
The Card class creates and initializes the outline of each card.
Each card has a suit, rank, image, and point value.
A card also knows how to draw itself.
*/

import java.awt.*;

public class Card {
    private int point;
    private String suit;
    private String rank;
    private Image pic;
    private GameViewer window;
    public static final int CARD_WIDTH = 100;
    public static final int CARD_HEIGHT = (int)(100*1.7106038292);


    public Card(String rank, String suit, int point, Image pic, GameViewer window){
        this.suit = suit;
        this.rank = rank;
        this.point = point;
        this.pic = pic;
        // The card needs to know what window to draw itself on
        this.window = window;
    }

    // All getters and setters
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

    // Card can draw itself on the GameViewer window if it's given what position to draw itself
    public void draw(Graphics g, int xTop, int yTop){
        g.drawImage(pic, xTop,yTop, CARD_WIDTH,CARD_HEIGHT, window);
    }
}

