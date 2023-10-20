import java.util.Scanner;

/**
 * The WildCard class represents a specific type of UNO card, the Wild card.
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

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();
        scanner.close();

        System.out.println("Enter A Colour of your choice: ");
        String upInput = input.toUpperCase();

        if (upInput == "BLUE"){
            this.setColour(Colour.BLUE);

        }
        else if(upInput== "RED"){
            this.setColour(Colour.RED);

        }
        else if(upInput == "YELLOW"){
            this.setColour(Colour.YELLOW);

        }
        else if(upInput == "GREEN"){
            this.setColour(Colour.GREEN);
        }
        System.out.println(game.getCurrentPlayer()+" has changed colour to: " +upInput+" !");


    }

    /**
     * Returns a string representation of the wild card. Specifies the type of the card (WILD-CARD).
     *
     * @return A string representation of the wild card.
     */
    @Override
    public String toString() {
        return "WILD-CARD ";
    }
}
