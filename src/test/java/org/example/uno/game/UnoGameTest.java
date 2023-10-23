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

    @Test
    public void testIsLightGame(){
        assertTrue(unoGame.isLightGame());
    }

    @Test
    public void testDeck(){
        Deck deck = new Deck();
        unoGame.setDeck(deck);
        assertEquals(unoGame.getDeck(), deck);
    }

    @Test
    public void testAddPlayer(){
        unoGame.addPlayer(new Player("John"));
        unoGame.addPlayer(new Player("Cathy"));
        unoGame.addPlayer(new Player("Manny"));
        assertEquals(unoGame.getPlayers().size(), 3);
    }

    @Test
    public void testGetCurrentPlayerAndNextPlayer(){
        Player p1 = new Player("Hajar");
        Player p2 = new Player("Mahad");
        Player p3 = new Player("Hasib");
        unoGame.addPlayer(p1);
        unoGame.addPlayer(p2);
        unoGame.addPlayer(p3);
        unoGame.setCurrentPlayer(p1);
        assertEquals(unoGame.getCurrentPlayer(),p1);
        unoGame.nextPlayer();
        assertEquals(unoGame.getCurrentPlayer(),p2);
        unoGame.nextPlayer();
        assertEquals(unoGame.getCurrentPlayer(),p3);
    }

    @Test
    public void testTakeFromDeck(){
        Deck deck = new Deck();
        Player p1 = new Player("Hajar");
        Player p2 = new Player("Mahad");
        Player p3 = new Player("Hasib");
        unoGame.addPlayer(p1);
        unoGame.addPlayer(p2);
        unoGame.addPlayer(p3);
        unoGame.setDeck(deck);
        unoGame.takeFromDeck(p1);
        unoGame.takeFromDeck(p1);
        unoGame.takeFromDeck(p1);
        unoGame.takeFromDeck(p2);
        unoGame.takeFromDeck(p2);
        unoGame.takeFromDeck(p3);
        assertEquals(p1.getHand().size(), 3);
        assertEquals(p2.getHand().size(), 2);
        assertEquals(p3.getHand().size(), 1);
    }

}
