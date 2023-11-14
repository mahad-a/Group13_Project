package org.example.uno.cards;

import org.example.uno.game.*;
import org.example.uno.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrawOneCardTest {

    private DrawOneCard drawOneCard;
    private UnoGame game;
    private int numPlayers = 3;

    @BeforeEach
    public void setUp() {
        drawOneCard = new DrawOneCard(Card.Colour.BLUE);
        game = new UnoGame(true,numPlayers);
        game.setCurrentCard(new NumberCard(Card.Colour.BLUE, NumberCard.Number.FIVE));
    }

    @Test
    public void testGetValue() {
        assertEquals(10, drawOneCard.getValue());
    }

    @Test
    public void testCardPlayTwoPlayer() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        game.addPlayer(alpha);
        game.addPlayer(beta);
        game.setCurrentPlayer(game.getPlayers().get(0));
        game.setDeck(new Deck());
        // ensure that the next player's turn has been skipped, and they picked up one
        Player originalCurrentPlayer = game.getCurrentPlayer();
        assertTrue(drawOneCard.playCard(game)); // test whether the card can be played
        Player newCurrentPlayer = game.getCurrentPlayer();

        assertEquals(originalCurrentPlayer, newCurrentPlayer);

    }
    @Test
    public void testCardPlayThreePlayer() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        Player gamma = new Player("Gamma");
        game.addPlayer(alpha);
        game.addPlayer(beta);
        game.addPlayer(gamma);
        game.setDeck(new Deck());
        game.setCurrentPlayer(alpha);
        // ensure that the next player's turn has been skipped
        Player originalCurrentPlayer = game.getCurrentPlayer();
        assertTrue(drawOneCard.playCard(game));// test whether the card can be played

        Player newCurrentPlayer = game.getCurrentPlayer();

        assertNotEquals(alpha, game.getCurrentPlayer());

    }

    @Test
    public void testToString() {
        assertEquals("BLUE DRAW_ONE_CARD", drawOneCard.toString());
    }
}