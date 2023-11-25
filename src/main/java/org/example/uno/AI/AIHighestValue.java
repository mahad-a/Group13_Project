package org.example.uno.AI;

import org.example.uno.cards.Card;
import org.example.uno.game.UnoGame;

public class AIHighestValue extends AIPlayer {

    public AIHighestValue(String name){
        super(name);
    }
    @Override
    public Card strategyPlay(UnoGame unoGame) {
        int highestValue = 0; // store the highest value
        Card highestCard = null; // highest possible card
        // strategy: search through hand for card with the highest value to play
        for (Card c: this.getHand()){
            if (c.isCardPlaceable(unoGame, c)){ // if card playable
                if (c.getValue() > highestValue){ // compare to current highest
                    highestValue += c.getValue(); // if higher, update the highest value
                    highestCard = c; // store as the highest card
                }
            }
        }
        // if playable card found, returns the card
        // else, returns null
        return highestCard;
    }
}
