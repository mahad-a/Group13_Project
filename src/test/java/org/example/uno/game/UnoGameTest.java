package org.example.uno.game;

import org.example.uno.cards.Card;
import org.example.uno.cards.NumberCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnoGameTest {
    UnoGame unoGame;

    @BeforeEach
    public void createGame() {
        unoGame = new UnoGame(true);
    }

    @Test
    public void testNextPlayer() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        Player gamma = new Player("Gamma");

        unoGame.addPlayer(alpha);
        unoGame.addPlayer(beta);
        unoGame.addPlayer(gamma);
        unoGame.setCurrentPlayer(unoGame.getPlayers().get(0));
        unoGame.nextPlayer();
        assertEquals(unoGame.getCurrentPlayer(), beta);
    }

    @Test
    public void testDealCards() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        Player gamma = new Player("Gamma");
        Player delta = new Player("Delta");

        unoGame.addPlayer(alpha);
        unoGame.addPlayer(beta);
        unoGame.addPlayer(gamma);
        unoGame.addPlayer(delta);
        unoGame.setDeck(new Deck());
        unoGame.dealCards();

        // check every player has 7 cards dealt to them
        for (Player player: unoGame.getPlayers()) {
            assertEquals(7, player.getHand().size());
        }
    }

    @Test
    public void testDrawOne() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");

        unoGame.addPlayer(alpha);
        unoGame.addPlayer(beta);

        unoGame.setDeck(new Deck());
        unoGame.dealCards();

        int initialAlphaHandSize = alpha.getHand().size();
        int initialBetaHandSize = beta.getHand().size();

        unoGame.takeFromDeck(alpha);

        assertEquals(initialAlphaHandSize + 1, alpha.getHand().size());
        assertEquals(initialBetaHandSize, beta.getHand().size());
    }

    @Test
    public void testIsPlayerNameExists(){
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");

        unoGame.addPlayer(alpha);
        unoGame.addPlayer(beta);

        assertTrue(unoGame.isPlayerNameExists("Beta"));
        assertFalse(unoGame.isPlayerNameExists("Pi"));
    }

    @Test
    public void testCalculateScore() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        unoGame.addPlayer(alpha);
        unoGame.addPlayer(beta);
        alpha.addCard(new NumberCard(Card.Colour.RED, NumberCard.Number.FIVE));
        alpha.addCard(new NumberCard(Card.Colour.BLUE, NumberCard.Number.THREE));
        assertEquals(8, unoGame.calculateScore(beta));
        beta.updateScore(unoGame.calculateScore(beta));
        assertEquals(0, alpha.getScore());
        assertEquals(8, beta.getScore());
    }


}
