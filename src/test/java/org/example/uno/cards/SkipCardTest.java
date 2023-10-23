package org.example.uno.cards;

import org.example.uno.game.*;
import org.example.uno.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkipCardTest {

    private SkipCard skipCard;
    private UnoGame game;

    @BeforeEach
    public void setUp() {
        skipCard = new SkipCard(Card.Colour.BLUE);
        game = new UnoGame(true);
        game.setCurrentCard(new NumberCard(Card.Colour.BLUE, NumberCard.Number.FIVE));
    }

    @Test
    public void testGetValue() {
        assertEquals(20, skipCard.getValue());
    }

    @Test
    public void testCardPlayTwoPlayer() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        game.addPlayer(alpha);
        game.addPlayer(beta);
        game.setCurrentPlayer(game.getPlayers().get(0));
        // ensure that the next player's turn has been skipped
        Player originalCurrentPlayer = game.getCurrentPlayer();
        assertTrue(skipCard.playCard(game)); // test whether the card can be played
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
        game.setCurrentPlayer(game.getPlayers().get(0));
        // ensure that the next player's turn has been skipped
        Player originalCurrentPlayer = game.getCurrentPlayer();
        assertTrue(skipCard.playCard(game)); // test whether the card can be played
        Player newCurrentPlayer = game.getCurrentPlayer();

        assertNotEquals(originalCurrentPlayer, newCurrentPlayer);
        assertEquals(newCurrentPlayer, gamma);
    }

    @Test
    public void testToString() {
        assertEquals("BLUE SKIP_CARD", skipCard.toString());
    }
}