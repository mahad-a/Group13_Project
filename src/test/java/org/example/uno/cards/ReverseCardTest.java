package org.example.uno.cards;
import static org.junit.Assert.*;
import org.example.uno.game.Player;
import org.example.uno.game.UnoGameModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class ReverseCardTest {

    private ReverseCard reverseCard;
    private UnoGameModel game;
    int numPlayers = 2 ;
    private int numAI = 0;

    @Before
    public void setUp() {
        Player player = new Player("TEST");
        Player player2 = new Player("TEST2");
        reverseCard = new ReverseCard(Card.Colour.BLUE);

        game = new UnoGameModel(true, numPlayers, numAI);
        game.setCurrentCard(reverseCard);
        game.addPlayer(player);
        game.addPlayer(player2);
        game.setCurrentPlayer(player);
    }

    @Test
    public void testGetValue() {

        assertEquals( 20,reverseCard.getValue());
    }

    @Test
    public void testPlayCard() {

        // Ensure that the order of players has been reversed
        ArrayList<Player> originalPlayers = new ArrayList<>(game.getPlayers());
        reverseCard.playCard(game);
        ArrayList<Player> reversedPlayers = new ArrayList<>(game.getPlayers());
        assertNotEquals(reversedPlayers, originalPlayers);
        Collections.reverse(originalPlayers);
        assertEquals(originalPlayers, reversedPlayers);
    }

    @Test
    public void testToString() {
        assertEquals("BLUE REVERSE_CARD", reverseCard.toString());
    }
}