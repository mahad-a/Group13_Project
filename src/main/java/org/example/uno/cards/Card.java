package org.example.uno.cards;

import org.example.uno.game.*;


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
public abstract class Card {


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
    public boolean isCardPlaceable(UnoGame game, Card placeCard){
        boolean noPlayableCardInHand = true;
        Card currentCard = game.getCurrentCard();
        // within rulebook, states wildDrawTwoCard can only be played if the player has no cards of the current color
        for (Card card : game.getCurrentPlayer().getHand()){
            if (card instanceof NumberCard && card.getColour().equals(currentCard.getColour())){
                noPlayableCardInHand = false;
                break;
            }
        }
        boolean deckColorMatch = currentCard.getColour() == placeCard.getColour();
        boolean wildCardMatch = currentCard.getColour() == placeCard.getColour() && (currentCard instanceof WildCard);
        boolean wildDrawTwoCardMatch = (currentCard instanceof WildDrawTwoCard) && noPlayableCardInHand;
        boolean reverseCardsMatch = currentCard instanceof ReverseCard && placeCard instanceof ReverseCard;

        return deckColorMatch || wildCardMatch || reverseCardsMatch || wildDrawTwoCardMatch;
    }

    public void placeCard(UnoGame game, Card card){
        game.setCurrentCard(card);
        game.getCurrentPlayer().discardCard(card);
        game.nextPlayer();
    }

    public abstract int getValue();

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
    public abstract boolean playCard(UnoGame game);
}
