package org.example.uno.cards;

import org.example.uno.game.Player;
import org.example.uno.game.UnoGameModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlipCardTest {
    private FlipCard flipCard;
    private UnoGameModel game;
    private NumberCard card;
    int numPlayers = 2 ;
    private int numAI = 0;
    @Before
    public void setUp() {

        Player player = new Player("TEST");

        flipCard = new FlipCard(Card.Colour.BLUE);
        card = new NumberCard(Card.Colour.BLUE, NumberCard.Number.ONE);

        game = new UnoGameModel(false, numPlayers, numAI);

        game.setCurrentCard(card);
        game.addPlayer(player);
        game.setCurrentPlayer(player);
    }

    @Test
    public void testGetValue() {

        assertEquals( 20,flipCard.getValue());
    }

    @Test
    public void testPlayCard() {
        assertTrue(flipCard.playCard(game));
        assertTrue(game.isDarkGame());
    }

    @Test
    public void testToString() {
        assertEquals("BLUE FLIP_CARD", flipCard.toString());
    }
}