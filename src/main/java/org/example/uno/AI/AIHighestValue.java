package org.example.uno.AI;

import org.example.uno.cards.Card;
import org.example.uno.game.UnoGame;

public class AIHighestValue extends AIPlayer {

    public AIHighestValue(String name){
        super(name);
    }
    @Override
    public Card strategyPlay(UnoGame unoGame) {
//        // strategy: search through hand for first possible card to play
//        for (Card c: this.getHand()){
//            if (c.isCardPlaceable(unoGame, c)){
//                return c;
//            }
//        }
//        // if no suitable card is found, draw card
//        unoGame.takeFromDeck(this, false, "");
        return null;
    }
}
