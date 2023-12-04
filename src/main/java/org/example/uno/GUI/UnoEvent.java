package org.example.uno.GUI;

import org.example.uno.cards.Card;
import org.example.uno.game.UnoGameModel;

import java.io.Serializable;
import java.util.EventObject;

/**
 * The UnoEvent class represents an event that happens in the UNO game. It extends the EventObject class.
 * It is used as a messenger for game events allowing the game rules to communicate with the user interface.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.0
 */
public class UnoEvent extends EventObject implements Serializable {
    private boolean moveMade;
    private boolean skipNextPlayer;
    private Card cardDrawn;
    private String message;

    /**
     * Constructs a new UnoEvent with parameters.
     *
     * @param model The UnoGameModel associated with the event.
     * @param t Represents if a move was made during the event or not.
     * @param skipNext Represents if the next player should be skipped or not.
     * @param m A message related to the event that is taking place.
     */
    public UnoEvent(UnoGameModel model, boolean t, boolean skipNext, String m){
        super(model);
        this.cardDrawn = model.getCardDrawn();
        this.moveMade = t;
        this.skipNextPlayer = skipNext;
        this.message = m;
    }

    /**
     * Checks if a move was made during the event.
     *
     * @return true if a move was made, false otherwise.
     */
    public boolean isMoveMade(){
        return this.moveMade;
    }

    /**
     * Retrieves the card that was drawn during the event.
     *
     * @return The card drawn during the event, or null if no card was drawn.
     */
    public Card getCardDrawn(){
        return this.cardDrawn;
    }
    /**
     * Checks if the next player should be skipped.
     *
     * @return true if the next player should be skipped, false otherwise.
     */
    public boolean isSkipNextPlayer(){return this.skipNextPlayer;}

    /**
     * Provides the additional message associated with the event.
     *
     * @return The additional message related to the event.
     */
    public String getMessage(){
        return this.message;
    }
}
