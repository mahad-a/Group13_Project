package org.example.uno.game;

import org.example.uno.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnoGameTest {
    UnoGame unoGame;
    int numPlayers = 2;
    boolean skipNext;


    @BeforeEach
    public void createGame() {
        unoGame = new UnoGame(true, numPlayers);
    }

    @Test
    public void testNextPlayer() {
        Player nextPlayer = unoGame.getPlayers().get(1); // second person in the arraylist
        unoGame.nextPlayer();

        assertEquals(nextPlayer, unoGame.getCurrentPlayer());
    }

    @Test
    public void testDealCards() {
        unoGame.setDeck(new Deck());
        unoGame.clearHand(); // make every player start with an empty hand
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

        unoGame.takeFromDeck(alpha,skipNext,"TEST");

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
        for (Player p: unoGame.getPlayers()) {
            p.discardHand();
        }
        Player player1 = unoGame.getCurrentPlayer();
        player1.addCard(new NumberCard(Card.Colour.RED, NumberCard.Number.FIVE));
        assertEquals(5, unoGame.calculateScore(player1));
        player1.addCard(new SkipCard(Card.Colour.BLUE));
        assertEquals(25, unoGame.calculateScore(player1));
        player1.addCard(new DrawOneCard(Card.Colour.GREEN));
        assertEquals(35, unoGame.calculateScore(player1));
        player1.addCard(new WildCard());
        assertEquals(75, unoGame.calculateScore(player1));
        player1.addCard(new ReverseCard(Card.Colour.YELLOW));
        assertEquals(95, unoGame.calculateScore(player1));
        player1.addCard(new WildDrawTwoCard());
        assertEquals(145, unoGame.calculateScore(player1));
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
        assertEquals(unoGame.getPlayers().size(), 4);
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
        unoGame.takeFromDeck(p1,true,"TEST 1");
        unoGame.takeFromDeck(p1,false,"TEST 2");
        unoGame.takeFromDeck(p1,false, "TEST 3");
        unoGame.takeFromDeck(p2, false, "TEST 4");
        unoGame.takeFromDeck(p2,false, "TEST 5");
        unoGame.takeFromDeck(p3,false, "TEST 6");
        assertEquals(p1.getHand().size(), 3);
        assertEquals(p2.getHand().size(), 2);
        assertEquals(p3.getHand().size(), 1);
    }

}
