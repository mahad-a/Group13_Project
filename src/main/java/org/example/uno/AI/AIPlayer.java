package org.example.uno.AI;

import org.example.uno.cards.Card;
import org.example.uno.game.Player;
import org.example.uno.game.UnoGame;

public abstract class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name);
    }

    public abstract Card strategyPlay(UnoGame unoGame); // decide the strategy to use

}
