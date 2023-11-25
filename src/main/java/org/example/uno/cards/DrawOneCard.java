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
    private int value;
    public final static int lightValue = 10;
    public final static int darkValue = 20;


    /**
     * JAVADOC
     */
    public DrawOneCard(Card.Colour colour){
        super(colour);
        this.value = lightValue;
    }

    /**
     * Gets the score value of the draw one card.
     *
     * @return The value of the draw one card (10).
     */
    public int getValue(){
        return value;
    }

    /**
     * JAVADOC
     *
     * @return
     */
    public void setValue(int value){
        this.value = value;
    }

    /**
     * Plays the Draw one card only if it can be placed on the current card on the table. (Based off of colour).
     *
     * @param game The UNO game in which the card is being played.
     * @return {@code true} if the card was played, {@code false} otherwise.
     */
    @Override
    public boolean playCard(UnoGame game) {
        if (game.isDarkGame()) {
            setValue(darkValue);
            if(super.isCardPlaceable(game, this)){
                super.placeCard(game, this);
                // makes the next person pick up 5 card and skips their turn
                for (int i = 0; i < 5; i++){
                    game.takeFromDeck(game.getNextPlayer(), true, game.getNextPlayer().getName() + " has to draw five\ncards due to Draw Five Card");
                }
                return true;
            }
        } else {
            setValue(lightValue);
            if (super.isCardPlaceable(game, this)) {
                super.placeCard(game, this);
                // makes the next person pick up a card and skips their turn
                game.takeFromDeck(game.getNextPlayer(), true, game.getNextPlayer().getName() + " has to draw one card\ncard due to Draw One Card");
                return true;
            }
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

    @Override
    public String getName() {
        return "DRAW ONE ";
    }

    @Override
    public String getDarkName() {
        return "DRAW FIVE ";
    }
}

