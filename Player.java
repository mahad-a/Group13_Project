import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    // actually a getter
    public ArrayList getHand(){
        return this.getHand();
    }

    public String showHand() {

        String retString = "";

        for(int i = 1; i < hand.size(); i++){
            retString += (i + ".  " + hand.get(i-1).toString() + "\n");
        }

        return retString;
    }

//    public void addPlayer(String playerName)  {
//        hand.add(playerName, new ArrayList<>());
//    }

    // draws a card and adds to hand
    public void addCard(Card card) {
            hand.add(card);
    }

    public void discardCard(String playerName, Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            this.discardPile.add(card);
        } else {
            System.out.println("Cannot remove " + card + " as this is not in the players hand");
        }
    }

}