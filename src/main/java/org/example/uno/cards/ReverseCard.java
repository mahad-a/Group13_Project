package org.example.uno.cards;

import java.util.ArrayList;
import java.util.Collections;
import org.example.uno.game.*;


/**
 * The ReverseCard class represents a specific type of UNO card, the reverse card.
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

    private static final int VALUE = 20;

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
    public boolean playCard(UnoGameModel game){
        // reverse card

        // if this doesn't change the original player list make a setter for player list
        //and replace the list with the reversed list
        if(super.isCardPlaceable(game, this)) {
            if(game.getPlayers().size() == 2){
                super.placeCard(game, this);
                game.setSkipNextPlayer(true);
                return true;
            }
            ArrayList<Player> players = game.getPlayers();
            Collections.reverse(players);
            game.setPlayers(players);
            super.placeCard(game, this);
            return true;
        }
        return false;
    }

    @Override
    public void unPlayCard(UnoGameModel game){
        if(game.getPlayers().size() == 2){
            game.setSkipNextPlayer(false); // revoke the skip next player
        }
//        ArrayList<Player> playersList = game.getPlayers();
//        Collections.reverse(playersList);
//        game.setPlayers(playersList);
        Collections.reverse(game.getPlayers()); // try this
    }

    /**
     * Gets the score value of the reverse card.
     *
     * @return The value of the reverse card (20).
     */
    public int getValue(){

        return VALUE;
    }

    /**
     * Returns a string representation of the playing card including its colour and its type (REVERSE-CARD)
     *
     * @return A string representation of the card.
     */
    @Override
    public String toString() {
        return  super.toString() + "REVERSE_CARD" ;
    }

    @Override
    public String getName() {
        return "REVERSE CARD ";
    }

    @Override
    public String getDarkName() {
        return getName();
    }


}
