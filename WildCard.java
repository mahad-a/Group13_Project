import java.util.Scanner;

public class WildCard extends Card {

    public WildCard(){

        super();
    }

    @Override
    public void playCard(UnoGame game){

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

    }

    @Override
    public String toString() {
        return "WILD-CARD ";
    }
}
