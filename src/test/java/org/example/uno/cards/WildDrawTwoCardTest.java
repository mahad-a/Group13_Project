package org.example.uno.cards;

import org.example.uno.game.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WildDrawTwoCardTest {

    private WildDrawTwoCard wildDrawTwoCard;
    private UnoGameModel game;
    private int numPlayers = 2;
    private int numAI = 0;
    @Before
    public void setUp() {
        wildDrawTwoCard = new WildDrawTwoCard();
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
        wildDrawTwoCard.setColour(Card.Colour.YELLOW);
        assertEquals(wildDrawTwoCard.getColour(), Card.Colour.YELLOW);
    }

    @Test
    public void testGetValue() {
        assertEquals(50, wildDrawTwoCard.getValue());
    }

    @Test
    public void testToStringWithoutColor() {
        assertEquals("WILD_DRAW_TWO_CARD", wildDrawTwoCard.toString());
    }

    @Test
    public void testToStringWithColor() {
        wildDrawTwoCard.setColour(Card.Colour.BLUE);
        assertEquals("WILD_DRAW_TWO_CARD (BLUE)", wildDrawTwoCard.toString());
    }
}
