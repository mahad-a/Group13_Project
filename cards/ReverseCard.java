package cards;

import cards.Card;
import game.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The cards.ReverseCard class represents a specific type of UNO card, the reverse card.
 * Reverse cards have a colour and can be placed in an UNO game to reverse the order of playing.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.1
 */
public class ReverseCard extends Card {

    /**
     * Constructs a reverse card with a specific colour.
     *
     * @param colour The colour of the card.
     */
    public ReverseCard(Colour colour){
        super(colour);
    }

    /**
     * Play the reverse card in the UNO game. When played, it reverses the order of playing.
     *
     * @param game The UNO game in which the card is being played.
     */
    @Override
    public void playCard(UnoGame game){
        // reverse card

        // if this doesnt change the original player list make a setter for player list
        //and replace the list with the reversed list
        Collections.reverse(game.getPlayers());
        int currPlayerIndex = game.getPlayers().indexOf(game.getCurrentPlayer());
        if(game.getPlayers().size() > 2){
            game.setCurrentPlayer(game.getPlayers().get(currPlayerIndex + 1));
        }

    }

    /**
     * Returns a string representation of the playing card including its colour and its type (REVERSE-CARD)
     *
     * @return A string representation of the card.
     */
    @Override
    public String toString() {
        return  super.toString() + "REVERSE-CARD" ;
    }


}
