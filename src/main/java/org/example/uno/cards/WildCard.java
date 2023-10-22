package org.example.uno.cards;

import java.util.Scanner;
import org.example.uno.game.*;

/**
 * The cards.WildCard class represents a specific type of UNO card, the Wild card.
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
     *
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
    public boolean playCard(UnoGame game){
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
        return true;
    }
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
}

