package org.example.uno.cards;

import org.example.uno.game.UnoGame;

public class FlipCard extends Card{

    static final int VALUE = 20;

    public FlipCard(Colour colour){
        super(colour);
    }
    @Override
    public int getValue() {
        return VALUE;
    }

    @Override
    public boolean playCard(UnoGame game) {
        Card currCard = game.getCurrentCard();
        if(super.isCardPlaceable(game, this) || (currCard instanceof FlipCard && currCard.getColour() == this.getColour())){
            super.placeCard(game, this);
            game.setDarkGame(!game.isDarkGame());
            return true;
        }
        return false;
    }

    public String toString(){
        return super.toString() + "FLIP_CARD";
    }

}
