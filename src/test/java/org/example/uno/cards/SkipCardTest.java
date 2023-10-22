package org.example.uno.cards;

import static org.junit.Assert.*;

import org.example.uno.game.Player;
import org.example.uno.game.UnoGame;
import org.junit.Before;
import org.junit.Test;

public class SkipCardTest {

    private SkipCard skipCard;
    private UnoGame game;

    @Before
    public void setUp() {
        // Create an instance of SkipCard with a specific color for testing
        skipCard = new SkipCard(Card.Colour.BLUE);

        // Create an UnoGame instance (you may need to adapt this based on your project)
        game = new UnoGame(true);
        game.setCurrentCard(skipCard); // Set the current card to the skip card
    }

    @Test
    public void testGetValue() {
        assertEquals(20, skipCard.getValue());
    }

    @Test
    public void testPlayCard() {
        // Test whether the card can be played
        assertTrue(skipCard.playCard(game));

        // Ensure that the next player's turn has been skipped
        Player originalCurrentPlayer = game.getCurrentPlayer();
        game.nextPlayer(); // Simulate moving to the next player
        Player newCurrentPlayer = game.getCurrentPlayer();

        assertNotEquals(originalCurrentPlayer, newCurrentPlayer);
    }

    @Test
    public void testToString() {
        assertEquals("BLUE SKIP_CARD", skipCard.toString());
    }
}