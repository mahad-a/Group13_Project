package org.example.uno.cards;

import org.example.uno.game.*;

/**
 * The NumberCard class represents the specific number of UNO card. NumberCards consist of a number (0-9) and a Colour.
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

    private final int VALUE;


    /**
     * Enumeration representing the numeric values of UNO number cards.
     */
    public enum Number {
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);

        private final int value;

        /**
         * Constructs an Enum with its corresponding numerical value as an int.
         *
         * @param value The numerical value int of the Number enum.
         */
        Number(int value) {
            this.value = value;
        }

        /**
         * Get the numeric value of the number card.
         *
         * @return The numeric value of the number card.
         */
        public int getValue() {
            return value;
        }
    }
    private final Number NUMBER;

    /**
     * Constructs a NumberCard with a specific colour and number.
     *
     * @param colour The colour of the cards.NumberCard
     * @param number The numeric value of the cards.NumberCard
     */
    public NumberCard(Colour colour, Number number){
        super(colour);
        this.NUMBER = number;
        this.VALUE = number.getValue();
    }

    /**
     * Gets the score value of the number card.
     *
     * @return The value of the number card.
     */
    public int getValue(){
        return this.VALUE;
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
    public boolean playCard(UnoGameModel game) {
        Card currCard = game.getCurrentCard();
        if(super.isCardPlaceable(game, this) || (currCard instanceof NumberCard && ((NumberCard) currCard).getNumber() == this.NUMBER)){
            super.placeCard(game, this);
            return true;
        }
        return false;
    }

    @Override
    public void unPlayCard(UnoGameModel game){
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

    @Override
    public String getName() {
        return "NUMBER " + this.NUMBER.toString();
    }

    @Override
    public String getDarkName() {
        return getName();
    }
}
