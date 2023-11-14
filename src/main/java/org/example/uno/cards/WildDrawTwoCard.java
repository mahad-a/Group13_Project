package org.example.uno.cards;

import org.example.uno.game.*;

/**
 * The WildDrawTwoCard class represents a special type of UNO card, The wild draw two card.
 * This card allows a player to choose a color and forces the next player to draw two cards.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.2
 */
public class WildDrawTwoCard extends Card {
    private static final int VALUE = 50;
    private boolean challenged;
    private String message;

    /**
     * Constructs a wild draw two card.
     */
    public WildDrawTwoCard(){
        super();
    }

    /**
     * Plays the wild draw two card in the UNO game. The player of the card gets to choose the colour and the next
     * player must draw two cards.
     *
     * @param game The UNO game in which the card is being played.
     * @return {@code true} if the card was successfully played, {@code false} otherwise.
     */
    @Override
    public boolean playCard(UnoGame game) {

        if(challenged){

                if(! super.isCardPlaceable(game,this)){
                    Card c1 = game.takeFromDeck(game.getCurrentPlayer(),false,"" );
                    Card c2 = game.takeFromDeck(game.getCurrentPlayer(),false,"" );
                    this.message = this.getColour() + " was chosen.\n" + game.getCurrentPlayer().getName()
                            + " was challenged and found GUILTY!\nDraw two cards";
                }

        }
        else {
            Card c1 = game.takeFromDeck(game.getNextPlayer(), true, "");
            Card c2 = game.takeFromDeck(game.getNextPlayer(), true, "");
            this.message = this.getColour() + " was chosen.\n" + game.getNextPlayer().getName()
                    + " must draw 2 cards due to Wild Draw Two.";
        }
        super.placeCard(game, this);

        return true;
    }

    /**
     * Gets the score value of the wild draw two card.
     *
     * @return The value of the card (50).
     */
    public int getValue(){
        return VALUE;
    }

    public void setChallenged(String in){
        if(in.equals("YES")){
            this.challenged = true;
        }
        else{
            this.challenged = false;
        }
    }

    public String getMessage(){
        return this.message;
    }

    /**
     * A string representation of the card and the chosen colour.
     *
     * @return A string representation of the card.
     */
    @Override
    public String toString() {
        if (this.getColour() == null) {
            return "WILD_DRAW_TWO_CARD";
        } else {
            return "WILD_DRAW_TWO_CARD (" + this.getColour() + ")";
        }
    }
}
