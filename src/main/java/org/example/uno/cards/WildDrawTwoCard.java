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
     * @return 'true' if the card was successfully played, 'false' otherwise.
     */
    @Override
    public boolean playCard(UnoGame game) {
        Colour chosenColour;
        String input;

        do {
            input = UnoGame.promptText("Enter a color of your choice").toUpperCase();

            try {
                chosenColour = Colour.valueOf(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid color. Please try again.");
            }

        } while (true);

        System.out.println(chosenColour + " has been chosen.");
        this.setColour(chosenColour);
        super.placeCard(game, this);
        Card c1 = game.takeFromDeck(game.getCurrentPlayer());
        Card c2 = game.takeFromDeck(game.getCurrentPlayer());
        System.out.println(game.getCurrentPlayer().getName() + " has to draw two due to Wild Draw Two: " + c1 + ", " + c2);
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
