package org.example.uno.game;

import org.example.uno.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {
    Player player;
    @BeforeEach
    public void createPlayer() {
        player = new Player("TestDummy");
    }

    @Test
    public void testGetName() {
        assertEquals(player.getName(), "TestDummy");
    }

    @Test
    public void testEmptyShowHand() {
        assertEquals(player.showHand(), ""); // tests empty hand
    }

    @Test
    public void testShowHand() {
        // ArrayList<Card> testHand = new ArrayList<>();
        player.addCard(new NumberCard(NumberCard.Colour.BLUE, NumberCard.Number.THREE));
        player.addCard(new NumberCard(NumberCard.Colour.RED, NumberCard.Number.SEVEN));

        String expected = "1.  BLUE THREE\n2.  RED SEVEN\n";
        assertEquals(expected, player.showHand());
    }

    @Test
    public void testAddCard() {
        NumberCard card = new NumberCard(NumberCard.Colour.GREEN, NumberCard.Number.FOUR);
        player.addCard(card);

        assertTrue(player.getHand().contains(card));
    }

    @Test
    public void testDiscardCard() {
        NumberCard card = new NumberCard(NumberCard.Colour.GREEN, NumberCard.Number.FOUR);
        player.addCard(card);

        assertTrue(player.getHand().contains(card)); // card currently in players hand

        player.discardCard(card);
        assertFalse(player.getHand().contains(card));
    }

    @Test
    public void testUpdateScore(){
        player.updateScore(32);
        assertEquals(player.getScore(), 32);
    }

    @Test
    public void testPlayerEquals(){
        Player p1 = new Player("John");
        Player p3 = new Player("john");
        Player p2 = new Player("TestDummy");
        assertTrue(p1.equals(p1));
        assertTrue(player.equals(p2));
        assertFalse(p1.equals(p3));
    }
}

