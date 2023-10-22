package cards;

import cards.Card;
import game.*;


import java.util.Scanner;

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
    public void playCard(UnoGame game){

        Scanner scanner = new Scanner(System.in);
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
        super.placeCard(game, this);

    }

    /**
     * Returns a string representation of the wild card. Specifies the type of the card (WILD-CARD).
     *
     * @return A string representation of the wild card.
     */
    @Override
    public String toString() {
        if (this.getColour() == null) {
            return "WILD-CARD";
        } else {
            return "WILD-CARD (" + this.getColour() + ")";
        }
    }
}
