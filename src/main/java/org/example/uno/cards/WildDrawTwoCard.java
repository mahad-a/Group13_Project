package org.example.uno.cards;

import org.example.uno.game.*;

public class WildDrawTwoCard extends Card {
    private static final int VALUE = 50;


    public WildDrawTwoCard(){
        super();
    }

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
        System.out.println(game.getCurrentPlayer() + " has to draw two due to Wild Draw Two: " + c1 + ", " + c2);
        return true;
    }
    public int getValue(){
        return VALUE;
    }

    @Override
    public String toString() {
        if (this.getColour() == null) {
            return "WILD_DRAW_TWO_CARD";
        } else {
            return "WILD_DRAW_TWO_CARD (" + this.getColour() + ")";
        }
    }
}
