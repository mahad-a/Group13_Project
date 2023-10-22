package org.example.uno.cards;

import org.example.uno.game.*;

public class WildDrawTwoCard extends Card {
    private static final int VALUE = 50;


    public WildDrawTwoCard(){
        super();
    }

    @Override
    public void playCard(UnoGame game) {
        String input;

        do {
            input = UnoGame.promptText("Enter a color of your choice").toUpperCase();

            switch (input) {
                case "BLUE":
                    this.setColour(Colour.BLUE);
                    break;
                case "RED":
                    this.setColour(Colour.RED);
                    break;
                case "YELLOW":
                    this.setColour(Colour.YELLOW);
                    break;
                case "GREEN":
                    this.setColour(Colour.GREEN);
                    break;
                default:
                    System.out.println("Please enter a card with either matching colors or number:");
                    // Continue the loop to prompt the user again.
                    continue;
            }

            System.out.println(game.getCurrentPlayer().getName() + " has changed color to: " + input + "!");
            this.setColour(Colour.valueOf(input));
            break; // Exit the loop if a valid color is chosen.

        } while (true);

        // put down card and make next player pick up two cards
        super.placeCard(game, this);
        game.drawOne(game.getCurrentPlayer());
        game.drawOne(game.getCurrentPlayer());

    }
    public int getValue(){
        return this.VALUE;
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
