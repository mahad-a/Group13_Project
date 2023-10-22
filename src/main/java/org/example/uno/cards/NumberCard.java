package org.example.uno.cards;

import org.example.uno.game.*;


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

    private final int value;

    /**
     * Enumeration representing the numeric values of UNO number cards.
     */
    public enum Number {
        ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);

        private final int value;

        Number(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    private final Number NUMBER;

    /**
     * Constructs a cards.NumberCard with a specific colour and number.
     *
     *
     * @param colour The colour of the cards.NumberCard
     * @param number The numeric value of the cards.NumberCard
     */
    public NumberCard(Colour colour, Number number ){
        super(colour);
        this.NUMBER = number;
        this.value = number.getValue();
    }
    public int getValue(){
        return this.value;
    }

    /**
     * Get the numeric value of the number card.
     *
     * @return The numeric value of the number card.
     */
    public Number getNumber(){
        return this.NUMBER;
    }

    /**
     * Play the number card in the UNO game, only if it can be placed
     * based on the current number or colour of the card.
     *
     * @param game The UNO game in which the card is being played.
     */
    @Override
    public boolean playCard(UnoGame game) {
        Card currCard = game.getCurrentCard();
        if(super.isCardPlaceable(game, this) || (currCard instanceof NumberCard && ((NumberCard) currCard).getNumber() == this.NUMBER)){
            super.placeCard(game, this);
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of number value and colour of the number card.
     *
     * @return A string representation of the card.
     */
    @Override
    public String toString() {
        return super.toString() + this.NUMBER;
    }
}