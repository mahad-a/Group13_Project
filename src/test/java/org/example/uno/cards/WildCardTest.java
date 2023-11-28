package org.example.uno.cards;

import org.example.uno.game.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WildCardTest {

    private WildCard wildCard;
    private UnoGameModel game;
    private int numPlayers = 2;
    private int numAI = 0;

    @Before
    public void setUp() {
        wildCard = new WildCard();
        game = new UnoGameModel(true, numPlayers, numAI);
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        game.addPlayer(alpha);
        game.addPlayer(beta);
        game.setCurrentPlayer(alpha);
        game.setCurrentCard(new NumberCard(Card.Colour.RED, NumberCard.Number.FIVE));
    }

    @Test
    public void testSetColour() {
        wildCard.setColour(Card.Colour.YELLOW);
        assertEquals(wildCard.getColour(), Card.Colour.YELLOW);
    }

    @Test
    public void testGetValue() {
        assertEquals(40, wildCard.getValue());
    }

    @Test
    public void testToStringWithoutColor() {
        assertEquals("WILD_CARD", wildCard.toString());
    }

    @Test
    public void testToStringWithColor() {
        wildCard.setColour(Card.Colour.BLUE);
        System.out.println(wildCard.toString());
        assertEquals("WILD_CARD (BLUE)", wildCard.toString());
    }
}
