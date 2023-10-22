package org.example.uno.cards;

import org.example.uno.game.*;

/**
 * The cards.SkipCard class is a representation of a specific type of UNO card, The Skip card.
 * Skip cards have a colour and can be plated to skip the next player's turn.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.1
 */
public class SkipCard extends Card {
    private static final int VALUE = 20;

    /**
     * Constructs a skip card with a specific colour.
     *
     * @param colour The colour of the skip card.
     */
    public SkipCard(Colour colour){
        super(colour);
    }

    /**
     * Play the skip card in the UNO game. Skips the next player's turn when played.
     *
     * @param game The UNO game in which the card is being played.
     */
    @Override
    public boolean playCard(UnoGame game){
        Card currCard = game.getCurrentCard();
        if(super.isCardPlaceable(game, this)){
            super.placeCard(game, this);
            game.nextPlayer(); // skip a player
            return true;
        }
        return false;
    }

    /**
     * Gets the score value of the skip card.
     *
     * @return The value of the skip card (20).
     */
    public int getValue(){
        return this.VALUE;
    }

    /**
     * Returns a string representation of the skip card, including its colour and type (SKIP-CARD)
     *
     * @return A string representation of the skip card.
     */
    @Override
    public String toString() {
        return super.toString() + "SKIP_CARD" ;
    }
}

