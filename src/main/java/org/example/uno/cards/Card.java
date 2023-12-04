package org.example.uno.cards;

import org.example.uno.game.*;

import java.io.Serializable;


/**
 * The cards.Card class represents a card in the UNO game. UNO cards have a colour and can be assigned different
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
public abstract class Card implements Serializable {

    /**
     * Enumeration representing the colours of the UNO cards.
     */
    public enum Colour {
        BLUE, RED, GREEN, YELLOW;

        @Override
        public String toString() {
            return name();
        }
    }

    private Colour colour;

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
        //this.darkMode = false;
    }

    /**
     * Sets the cards colour to a specific colour.
     *
     * @param colour The colour of the card.
     */
    public void setColour(Colour colour){
        this.colour = colour;
    }

    /**
     * Get the colour of the card.
     *
     * @return the colour of the card.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Checks if a card can be placed on the "table" based off of the current card.
     *
     * @param game The UNO game the card is being played in
     * @param placeCard The card being considered for placement.
     * @return {@code true} if the card is placeable, {@code false} otherwise.
     */
    public boolean isCardPlaceable(UnoGameModel game, Card placeCard){
        Card currentCard = game.getCurrentCard();

        // within rulebook, states wildDrawTwoCard can only be played if the player has no cards of the current color
        if (placeCard instanceof WildDrawTwoCard){
            for (Card card : game.getCurrentPlayer().getHand()){

                if((card.getColour() != null && card.getColour().equals(currentCard.getColour())) ||
                        (((card instanceof NumberCard) && (currentCard instanceof NumberCard)) &&
                        ((NumberCard) card).getNumber() == ((NumberCard) currentCard).getNumber())){
                    return false;
                }
            }
        }

        boolean isWildDrawTwo = (placeCard instanceof WildDrawTwoCard);
        boolean isWildCard = (placeCard instanceof WildCard);
        boolean deckColorMatch = currentCard.getColour() == placeCard.getColour();
        boolean wildCardMatch = currentCard.getColour() == placeCard.getColour() && (currentCard instanceof WildCard);
        boolean reverseCardsMatch = currentCard instanceof ReverseCard && placeCard instanceof ReverseCard;
        boolean drawOneCardMatch = currentCard instanceof DrawOneCard && placeCard instanceof DrawOneCard;
        boolean skipCardMatch = currentCard instanceof SkipCard && placeCard instanceof SkipCard;
        boolean isFlipCard =  currentCard instanceof FlipCard && placeCard instanceof FlipCard;

        return deckColorMatch || wildCardMatch || reverseCardsMatch || drawOneCardMatch || skipCardMatch || isWildDrawTwo || isWildCard || isFlipCard;
    }

    /**
     * Places the card on the "table" that the UNO game is being played on.
     *
     * @param game The UNO game the card is being played in
     * @param card The card to be placed.
     */
    public void placeCard(UnoGameModel game, Card card){
        game.getCurrentPlayer().discardCard(card);
        game.getDeck().addToDiscardPile(card);// add to placed pile
        game.setCurrentCard(card);
    }

    /**
     * Abstract method designed to get the score value of each card.
     *
     * @return The value of the card.
     */
    public abstract int getValue();

    /**
     * Get the colour of a card, represented as a string.
     *
     * @return A string representation of the current colour of the card.
     */
    public String toString() {
        return (this.colour + " ");
    }

    public abstract String getName();

    public abstract String getDarkName();

    /**
     * Abstract method for playing the card in a UNO game.
     *
     * @param game The UNO game in which the card is being played.
     */
    public abstract boolean playCard(UnoGameModel game);
    public abstract void unPlayCard(UnoGameModel game);

}
