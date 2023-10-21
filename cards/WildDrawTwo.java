package cards;

import cards.Card;
import game.*;

import java.util.Scanner;

public class WildDrawTwo extends Card {


    public WildDrawTwo(){
        super();
    }

    @Override
    public void playCard(UnoGame game) {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.println("Enter a color of your choice: ");
            input = scanner.nextLine().toUpperCase();

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

            System.out.println(game.getCurrentPlayer() + " has changed color to: " + input + "!");
            break; // Exit the loop if a valid color is chosen.

        } while (true);

        game.setCurrentCard(this);
        game.getCurrentPlayer().discardCard(this);

        int currplayindx = game.getPlayers().indexOf(game.getCurrentPlayer());
        game.drawOne(game.getPlayers().get(currplayindx + 1));
        game.drawOne(game.getPlayers().get(currplayindx + 1));
        game.setCurrentPlayer(game.getPlayers().get(currplayindx + 2));


    }

    @Override
    public String toString() {
        return "WILD-CARD DRAW TWO";
    }
}
    