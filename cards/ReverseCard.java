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
        Player currPlayer = game.getCurrentPlayer();
        ArrayList<Player> players = game.getPlayerList();
        int index = players.indexOf(currPlayer);
        Player prevPlayer;
        if ((index - 1) < 0) {
            prevPlayer = players.get(players.size() - 1);
        } else {
            prevPlayer = players.get(index - 1);
        }
        Collections.reverse(players);
        game.setCurrentPlayer(prevPlayer);

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
