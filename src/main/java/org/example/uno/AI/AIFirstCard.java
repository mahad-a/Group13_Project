package org.example.uno.AI;

import org.example.uno.cards.Card;
import org.example.uno.game.UnoGame;

public class AIFirstCard extends AI implements AIMain {
    // AI plays the first possible card it can find
    public AIFirstCard(String name) {
        super(name);
    }

    @Override
    public void decideStrategy(UnoGame unoGame) {
        // search through hand for first possible card to play
        for (Card c: this.getHand()){
            if (c.isCardPlaceable(unoGame, c)){
                c.playCard(unoGame);
                return;
            }
        }
        // if no suitable card is found, draw card
        unoGame.takeFromDeck(this, false, "");
    }

    @Override
    public void legalMove() {

    }
}
