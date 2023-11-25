package org.example.uno.AI;

import org.example.uno.cards.Card;
import org.example.uno.game.Player;
import org.example.uno.game.UnoGame;

import java.util.Random;

public abstract class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name);
    }

    public abstract Card strategyPlay(UnoGame unoGame); // decide the strategy to use


    // JAVADOCS makes the AI choose a random colour
    public Card.Colour getRandomColour(){
        Card.Colour[] colours = Card.Colour.values();
        int randomIndex = new Random().nextInt(colours.length);
        return colours[randomIndex];
    }

}
