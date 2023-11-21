package org.example.uno.AI;

import org.example.uno.cards.Card;
import org.example.uno.cards.ReverseCard;
import org.example.uno.cards.SkipCard;
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
            if (c.getColour() == unoGame.getCurrentCard().getColour() || c.getValue() == unoGame.getCurrentCard().getValue()){
                // since reverse card and skip card have same value, one more check
                if ((c instanceof SkipCard && !(unoGame.getCurrentCard() instanceof SkipCard)) || !(c instanceof SkipCard && unoGame.getCurrentCard() instanceof SkipCard)){
                    return;
                } else if ((c instanceof ReverseCard && !(unoGame.getCurrentCard() instanceof ReverseCard)) || !(c instanceof ReverseCard && unoGame.getCurrentCard() instanceof ReverseCard)){
                    return;
                }
                unoGame.handleCurrentPlayerTurn(this, c);
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
