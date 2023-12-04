package org.example.uno.AI;

import org.example.uno.cards.Card;
import org.example.uno.game.UnoGameModel;

public class AIFirstCard extends AIPlayer {
    // AI plays the first possible card it can find
    public AIFirstCard(String name) {
        super(name);
    }

    @Override
    public Card strategyPlay(UnoGameModel unoGameModel) {
        // strategy: search through hand for first possible card to play
        for (Card c: this.getHand()){
            if (c.isCardPlaceable(unoGameModel, c)){
                // deal with wild card instances
                if (c.getColour() == null){
                    c.setColour(getRandomColour());
                }
                return c;
            }
        }
        // if no suitable card is found, return null (picking up from deck is handled in handleNextPlayer)
        return null;
    }
}
