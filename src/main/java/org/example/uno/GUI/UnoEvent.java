package org.example.uno.GUI;

import org.example.uno.cards.Card;
import org.example.uno.game.UnoGame;

import java.util.EventObject;

public class UnoEvent extends EventObject {
    private boolean moveMade;
    private boolean skipNextPlayer;
    private String message;
    public UnoEvent(UnoGame model, boolean t,boolean skipNext,String m){
        super(model);
        this.moveMade = t;
        this.skipNextPlayer = skipNext;
        this.message = m;
    }

    public boolean isMoveMade(){
        return this.moveMade;
    }
    public boolean isSkipNextPlayer(){return this.skipNextPlayer;}

    public String getMessage(){
        return this.message;
    }
}
