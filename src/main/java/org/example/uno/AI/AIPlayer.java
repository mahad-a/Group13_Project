package org.example.uno.AI;

import org.example.uno.cards.Card;
import org.example.uno.game.Player;
import org.example.uno.game.UnoGame;

import java.util.Random;

public abstract class AIPlayer extends Player {

    /**
     * Constructs an AI player with a specific name.
     *
     * @param name The name of the AI player.
     */
    public AIPlayer(String name) {
        super(name);
    }

    /**
     * Gets the strategy to play for the AI player.
     *
     * @param unoGame The UNO game in which the AI player is playing.
     * @return The card to play.
     */
    public abstract Card strategyPlay(UnoGame unoGame); // decide the strategy to use


    /**
     * Gets a random colour from the colour enum.
     *
     * @return A random colour.
     */
    public Card.Colour getRandomColour(){
        Card.Colour[] colours = Card.Colour.values();
        int randomIndex = new Random().nextInt(colours.length);
        return colours[randomIndex];
    }

}
