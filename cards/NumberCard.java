package cards;
import game.*;

/**
 * The cards.NumberCard class represents the specific number of UNO card. NumberCards consist of a number (0-9) and a Colour.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.1
 */
public class NumberCard extends Card {

    /**
     * Enumeration representing the numeric values of UNO number cards.
     */
    public enum Number{
        ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE
    }
    private Number number;

    /**
     * Constructs a cards.NumberCard with a specific colour and number.
     *
     *
     * @param colour The colour of the cards.NumberCard
     * @param number The numeric value of the cards.NumberCard
     */
    public NumberCard(Colour colour, Number number ){
        super(colour);
        this.number = number;
    }

    /**
     * Get the numeric value of the number card.
     *
     * @return The numeric value of the number card.
     */
    public Number getNumber(){
        return this.number;
    }

    /**
     * Play the number card in the UNO game, only if it can be placed
     * based on the current number or colour of the card.
     *
     * @param game The UNO game in which the card is being played.
     */
    @Override
    public void playCard(UnoGame game) {
        Card currCard = game.getCurrentCard();
        if(super.isCardPlaceable(currCard, this)){
            super.placeCard(game, this);
        } else if(currCard instanceof NumberCard){
            if (((NumberCard) currCard).getNumber() == this.number) {
                super.placeCard(game, this);
            }
        } else {
            System.out.println("Cannot place this card.");
        }
    }

    /**
     * Returns a string representation of number value and colour of the number card.
     *
     * @return A string representation of the card.
     */
    @Override
    public String toString() {
        return super.toString() + this.number;
    }
}
