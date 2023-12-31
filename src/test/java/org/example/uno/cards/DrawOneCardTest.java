package org.example.uno.cards;

import org.example.uno.game.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrawOneCardTest {

    private DrawOneCard drawOneCard;
    private UnoGameModel game;
    private int numPlayers = 3;
    private int numAI = 0;

    @BeforeEach
    public void setUp() {
        drawOneCard = new DrawOneCard(Card.Colour.BLUE);
        game = new UnoGameModel(true,numPlayers, numAI);
        game.setCurrentCard(new NumberCard(Card.Colour.BLUE, NumberCard.Number.FIVE));
    }

    @Test
    public void testGetValue() {
        assertEquals(10, drawOneCard.getValue());
    }

    @Test
    public void testPlayCard() {
        Player nextPlayer = game.getNextPlayer();
        int size = nextPlayer.getHand().size();
        drawOneCard.playCard(game);
        System.out.println(size);
        System.out.println(game.getNextPlayer().getHand().size());
        assertTrue(size < game.getNextPlayer().getHand().size());
    }

    @Test
    public void testToString() {
        assertEquals("BLUE DRAW_ONE_CARD", drawOneCard.toString());
    }
}