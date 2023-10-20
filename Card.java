/**
 * The Card class represents a card in the UNO game. UNO cards have a colour and can be assigned different
 * types. The class is abstract and is designed to be extended to define specific types of UNO cards.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.1
 */
public abstract class Card {

    public enum Colour {
        BLUE, RED, GREEN, YELLOW
    }

    private Colour colour;
    private Number number;

    public Card(){

    }

    public Card(Colour colour) {
        this.colour = colour;
    }


    public Colour getColour() {
        return colour;
    }

    // Display the card, EX (RED SKIP)
    public String toString() {
        return (this.colour + "");
    }

    public abstract void playCard(Deck deck, Player player);
}
