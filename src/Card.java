public class Card {
    private int point;
    private String suit;
    private String rank;

    //constructor
    public Card(String rank, String suit, int point){

        this.suit = suit;
        this.rank = rank;
        this.point = point;
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

}
