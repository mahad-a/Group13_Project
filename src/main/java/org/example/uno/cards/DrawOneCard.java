package org.example.uno.cards;

import org.example.uno.game.UnoGameModel;

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
    public final static int LIGHT_VALUE = 10;
    public final static int DARK_VALUE = 20;


    /**
     * Constructs a draw one card with a specific colour.
     * @param colour The colour of the card.
     */
    public DrawOneCard(Card.Colour colour){
        super(colour);
        this.value = LIGHT_VALUE;
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
     * Sets the value of the card to the value passed in.
     *
     * @param value The value to set the card to.
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
    public boolean playCard(UnoGameModel game) {
        if (game.isDarkGame()) {
            setValue(DARK_VALUE);
            if(super.isCardPlaceable(game, this)){
                super.placeCard(game, this);
                // makes the next person pick up 5 card and skips their turn
                for (int i = 0; i < 5; i++){
                    game.takeFromDeck(game.getNextPlayer(), true, game.getNextPlayer().getName() + " has to draw five\ncards due to Draw Five Card");
                }
                return true;
            }
        } else {
            setValue(LIGHT_VALUE);
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
     *
     * @param game
     */
    public void unPlayCard(UnoGameModel game){

        // If game is dark, undo the 5 cards given
        int size =  game.getNextPlayer().getHand().size(); // store the size
        if (game.isDarkGame()){
            for(int i = 1; i<6; i++) {
                game.putBackInDeck(game.getNextPlayer().getHand().get(size - i), game.getNextPlayer());
            }
        }
        // If game is light, undo the last card given.
        game.putBackInDeck(game.getNextPlayer().getHand().get(size - 1),game.getNextPlayer());

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

