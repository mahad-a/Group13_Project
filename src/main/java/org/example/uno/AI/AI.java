package org.example.uno.AI;

import org.example.uno.game.Player;
import org.example.uno.game.UnoGame;

public abstract class AI extends Player {

    public AI(String name) {
        super(name);
    }

    public abstract void decideStrategy(UnoGame unoGame); // decide the strategy to use
}
