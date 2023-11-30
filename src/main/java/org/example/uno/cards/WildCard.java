package org.example.uno.cards;

import org.example.uno.game.UnoGameModel;

/**
 * The WildCard class represents a specific type of UNO card, the Wild card.
 * The wild card can be played in an UNO game to change the current colour of the game.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.1
 */
public class WildCard extends Card {

    private static final int VALUE = 40;

    /**
     * Constructs a wild card.
     */
    public WildCard(){
        super();
    }


    /**
     * Play the wild card in the UNO game. When played, it allows the player to choose
     * the next playing colour of the cards.
     *
     * @param game The UNO game in which the card is being played.
     */
    @Override
    public boolean playCard(UnoGameModel game){
        super.placeCard(game, this);
        return true;
    }

    public void unPlayCard(UnoGameModel game){
        this.setColour(null);
    }

    /**
     * Gets the score value of the wild card.
     *
     * @return The value of the wild card (40).
     */
    public int getValue(){
        return VALUE;
    }

    /**
     * Returns a string representation of the wild card. Specifies the type of the card (WILD-CARD).
     *
     * @return A string representation of the wild card.
     */
    @Override
    public String toString() {
        if (this.getColour() == null) {
            return "WILD_CARD";
        } else {
            return "WILD_CARD (" + this.getColour() + ")";
        }
    }

    @Override
    public String getName() {
        return "WILD CARD ";
    }

    @Override
    public String getDarkName() {
        return getName();
    }
}

