package org.example.uno.cards;
import static org.junit.Assert.*;

import org.example.uno.game.Player;
import org.example.uno.game.UnoGame;
import org.junit.Before;
import org.junit.Test;

public class NumberCardTest {

    private NumberCard numberCard;
    private UnoGame game;
    int numPlayers = 2;
    private int numAI = 0;
    @Before
    public void setUp() {
        Player alpha = new Player("Alpha");
        numberCard = new NumberCard(Card.Colour.BLUE, NumberCard.Number.FIVE);
        game = new UnoGame(true, numPlayers, numAI);
        game.setCurrentCard(numberCard);
        game.addPlayer(alpha);
        game.setCurrentPlayer(game.getPlayers().get(0));
    }

    @Test
    public void testGetValue() {
        assertEquals(5, numberCard.getValue());
    }

    @Test
    public void testGetNumber() {
        assertEquals(NumberCard.Number.FIVE, numberCard.getNumber());
    }

    @Test
    public void testPlayCard() {

        assertTrue(numberCard.playCard(game));
        assertEquals(numberCard, game.getCurrentCard());
    }

    @Test
    public void testToString() {
        assertEquals("BLUE FIVE", numberCard.toString());
    }
}