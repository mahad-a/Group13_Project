package org.example.uno.cards;

import org.example.uno.game.UnoGameModel;

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
    public boolean playCard(UnoGameModel game) {
        Card currCard = game.getCurrentCard();
        if(super.isCardPlaceable(game, this) || (currCard instanceof FlipCard && currCard.getColour() == this.getColour())){
            super.placeCard(game, this);
            game.setDarkGame(!game.isDarkGame());
            return true;
        }
        return false;
    }

    @Override
    public void unPlayCard(UnoGameModel game){
        game.setDarkGame(!game.isDarkGame());
    }

    public String toString(){
        return super.toString() + "FLIP_CARD";
    }

    @Override
    public String getName() {
        return "FLIP CARD ";
    }

    @Override
    public String getDarkName() {
        return getName();
    }

}
