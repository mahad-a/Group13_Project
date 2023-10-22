package game;

import cards.Card;

import java.util.ArrayList;

/**
 * The player class represents a player in the UNO game. Each player has a name, a hand of uno cards,
 * and a discard pile. Players are allowed to draw cards, add cards to their hand of cards, and discard cards
 * in their hand into the discard pile.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.4
 */
public class Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> discardPile;
    private int myScore;

    /**
     * Constructs a player with the player provided name. The player's hand is initially empty.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
        this.myScore = 0;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand of UNO cards.
     *
     * @return The player's hand of UNO cards, as an ArrayList of cards.Card objects.
     */
    public ArrayList<Card> getHand(){
        return this.hand;
    }

    /**
     * Displays a formatted string representation of the player's hand of UNO cards.
     *
     * @return A string representation of the player's hand of UNO cards.
     */
    public String showHand() {
        String retString = "";

        for (int i = 0; i < hand.size(); i++) {
            retString += ((i+1) + ".  " + hand.get(i).toString() + "\n");
        }

        return retString;
    }

    /**
     * Adds a card to the player's hand
     *
     * @param card The card to be added to the player's hand
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Discards a card from the player's hand into the discard pile.
     *
     *
     * @param card The card to be discarded
     */
    public void discardCard(Card card) {
        if (hand.contains(card)) {
            hand.remove(card);
            /*this.discardPile.add(card);*/
        } else {
            System.out.println("Cannot remove " + card + " as this is not in the players hand");
        }
    }

    /**
     * Gets the player's score.
     *
     * @return the player's score.
     */
    public int getMyScore() {
        return this.myScore;
    }

    /**
     * Updates the player's score by adding 1 to the current score.
     *
     * @return The updated player's score.
     */
    public void updateMyScore(int score) {
        this.myScore = score;
    }

}