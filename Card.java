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

    /**
     * Enumeration representing the colours of the UNO cards.
     */
    public enum Colour {
        BLUE, RED, GREEN, YELLOW
    }

    private Colour colour;
    private Number number;

    /**
     * Default constructor for a card. Creates a card with no specific number or colour.
     */
    public Card(){

    }

    /**
     * Overloaded Constructor for a card. Creates a card with a specific colour.
     *
     * @param colour The colour of the card.
     */
    public Card(Colour colour) {
        this.colour = colour;
    }

    /**
     * Sets the cards colour to a specific colour.
     *
     * @param colour The colour of the card.
     */
    public void setColour(Colour colour){
        this.colour = colour;
    }

    /**I
     * Get the colour of the card.
     *
     * @return the colour of the card.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Get the colour of a card, represented as a string.
     *
     * @return A string representation of the current colour of the card.
     */
    public String toString() {
        return (this.colour + " ");
    }

    /**
     * Abstract method for playing the card in a UNO game.
     *
     * @param game The UNO game in which the card is being played.
     */
    public abstract void playCard(UnoGame game);
}
