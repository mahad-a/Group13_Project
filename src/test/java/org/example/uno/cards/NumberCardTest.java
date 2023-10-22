package org.example.uno.cards;
import static org.junit.Assert.*;
import org.example.uno.game.UnoGame;
import org.junit.Before;
import org.junit.Test;

public class NumberCardTest {

    private NumberCard numberCard;
    private UnoGame game;

    @Before
    public void setUp() {

        numberCard = new NumberCard(Card.Colour.BLUE, NumberCard.Number.FIVE);


        game = new UnoGame(true);
        game.setCurrentCard(numberCard);
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