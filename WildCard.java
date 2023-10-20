import java.util.Scanner;

public class WildCard extends Card {

    public WildCard(){

        super();
    }

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

    @Override
    public String toString() {
        return "WILD-CARD ";
    }
}
