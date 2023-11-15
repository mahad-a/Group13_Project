package org.example.uno.cards;

import org.example.uno.game.UnoGame;

/**
 * The DrawOneCard class represents a special UNO card type, the draw one card.
 * When played, the next player must draw one card and their turn is skipped.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.1
 */
public class DrawOneCard extends Card{
    private final int value;

    public DrawOneCard(Card.Colour colour){
        super(colour);
        this.value = 10;
    }

    /**
     * Gets the score value of the draw one card.
     *
     * @return The value of the draw one card (10).
     */
    public int getValue(){
        return this.value;
    }


    /**
     * Plays the Draw one card only if it can be placed on the current card on the table. (Based off of colour).
     *
     * @param game The UNO game in which the card is being played.
     * @return {@code true} if the card was played, {@code false} otherwise.
     */
    @Override
    public boolean playCard(UnoGame game) {
        Card currCard = game.getCurrentCard();
        if(super.isCardPlaceable(game, this)){

            super.placeCard(game, this);
            // makes the next person pick up a card and skips their turn
            Card c1 = game.takeFromDeck(game.getNextPlayer(),true,game.getNextPlayer().getName() + " has to draw one\ncard due to Draw One Card" );
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of number value and colour of the draw one card.
     *
     * @return A string representation of the card.
     */
    @Override
    public String toString() {
        return super.toString() + "DRAW_ONE_CARD";
    }
}

