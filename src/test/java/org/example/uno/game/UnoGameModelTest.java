package org.example.uno.game;

import org.example.uno.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class UnoGameModelTest {
    UnoGameModel unoGameModel;
    int numPlayers = 2;
    boolean skipNext;

    private int numAI = 0;
    @BeforeEach
    public void createGame() {
        unoGameModel = new UnoGameModel(true, numPlayers, numAI);
    }

    @Test
    public void testNextPlayer() {
        Player nextPlayer = unoGameModel.getPlayers().get(1); // second person in the arraylist
        unoGameModel.nextPlayer();

        assertEquals(nextPlayer, unoGameModel.getCurrentPlayer());
    }

    @Test
    public void testDealCards() {
        unoGameModel.setDeck(new Deck());
        unoGameModel.clearHand(); // make every player start with an empty hand
        unoGameModel.dealCards();

        // check every player has 7 cards dealt to them
        for (Player player: unoGameModel.getPlayers()) {
            assertEquals(7, player.getHand().size());
        }
    }

    @Test
    public void testDrawOne() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");

        unoGameModel.addPlayer(alpha);
        unoGameModel.addPlayer(beta);

        unoGameModel.setDeck(new Deck());
        unoGameModel.dealCards();

        int initialAlphaHandSize = alpha.getHand().size();
        int initialBetaHandSize = beta.getHand().size();

        unoGameModel.takeFromDeck(alpha,skipNext,"TEST");

        assertEquals(initialAlphaHandSize + 1, alpha.getHand().size());
        assertEquals(initialBetaHandSize, beta.getHand().size());
    }

    @Test
    public void testIsPlayerNameExists(){
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");

        unoGameModel.addPlayer(alpha);
        unoGameModel.addPlayer(beta);

        assertTrue(unoGameModel.isPlayerNameExists("Beta"));
        assertFalse(unoGameModel.isPlayerNameExists("Pi"));
    }
    
    @Test
    public void testCalculateScore() {
        for (Player p: unoGameModel.getPlayers()) {
            p.discardHand();
        }
        Player player1 = unoGameModel.getCurrentPlayer();
        player1.addCard(new NumberCard(Card.Colour.RED, NumberCard.Number.FIVE));
        assertEquals(5, unoGameModel.calculateScore(player1));
        player1.addCard(new SkipCard(Card.Colour.BLUE));
        assertEquals(25, unoGameModel.calculateScore(player1));
        player1.addCard(new DrawOneCard(Card.Colour.GREEN));
        assertEquals(35, unoGameModel.calculateScore(player1));
        player1.addCard(new WildCard());
        assertEquals(75, unoGameModel.calculateScore(player1));
        player1.addCard(new ReverseCard(Card.Colour.YELLOW));
        assertEquals(95, unoGameModel.calculateScore(player1));
        player1.addCard(new WildDrawTwoCard());
        assertEquals(145, unoGameModel.calculateScore(player1));
    }

    @Test
    public void testIsLightGame(){
        assertTrue(unoGameModel.isDarkGame());
    }

    @Test
    public void testDeck(){
        Deck deck = new Deck();
        unoGameModel.setDeck(deck);
        assertEquals(unoGameModel.getDeck(), deck);
    }

    @Test
    public void testAddPlayer(){
        unoGameModel.addPlayer(new Player("John"));
        unoGameModel.addPlayer(new Player("Cathy"));
        assertEquals(unoGameModel.getPlayers().size(), 4);
    }

    @Test
    public void testGetCurrentPlayerAndNextPlayer(){
        Player p1 = new Player("Hajar");
        Player p2 = new Player("Mahad");
        Player p3 = new Player("Hasib");
        unoGameModel.addPlayer(p1);
        unoGameModel.addPlayer(p2);
        unoGameModel.addPlayer(p3);
        unoGameModel.setCurrentPlayer(p1);
        assertEquals(unoGameModel.getCurrentPlayer(),p1);
        unoGameModel.nextPlayer();
        assertEquals(unoGameModel.getCurrentPlayer(),p2);
        unoGameModel.nextPlayer();
        assertEquals(unoGameModel.getCurrentPlayer(),p3);
    }

    @Test
    public void testTakeFromDeck(){
        Deck deck = new Deck();
        Player p1 = new Player("Hajar");
        Player p2 = new Player("Mahad");
        Player p3 = new Player("Hasib");
        unoGameModel.addPlayer(p1);
        unoGameModel.addPlayer(p2);
        unoGameModel.addPlayer(p3);
        unoGameModel.setDeck(deck);
        unoGameModel.takeFromDeck(p1,true,"TEST 1");
        unoGameModel.takeFromDeck(p1,false,"TEST 2");
        unoGameModel.takeFromDeck(p1,false, "TEST 3");
        unoGameModel.takeFromDeck(p2, false, "TEST 4");
        unoGameModel.takeFromDeck(p2,false, "TEST 5");
        unoGameModel.takeFromDeck(p3,false, "TEST 6");
        assertEquals(p1.getHand().size(), 3);
        assertEquals(p2.getHand().size(), 2);
        assertEquals(p3.getHand().size(), 1);
    }


    @Test
    public void testExportGameAndImportGame(){
        //test Serialization (tests exporting of original game)
        UnoGameModel ogGame = new UnoGameModel(true, 2, 1);
        ogGame.startGame();
        ogGame.exportGame("ogGame");

        //test importGame1 and ogGame (tests if import works)
        UnoGameModel deserializedGame1 = new UnoGameModel(false, 1, 1);
        deserializedGame1.importGame("ogGame");

        assertEquals(ogGame.isDarkGame(), deserializedGame1.isDarkGame());
        assertEquals(ogGame.getCurrentPlayer() , deserializedGame1.getCurrentPlayer());
        assertEquals(ogGame.getNextPlayer(), deserializedGame1.getNextPlayer());
        assertEquals(ogGame.getCurrentCard().toString(), deserializedGame1.getCurrentCard().toString());
        assertEquals(ogGame.getPlayers(), deserializedGame1.getPlayers());
        assertEquals(ogGame.getCardDrawn(), deserializedGame1.getCardDrawn());
        assertEquals(ogGame.getPrevTopCard(), deserializedGame1.getPrevTopCard());

        //test importGame2 and ogGame (tests if import works again on the same og file)
        UnoGameModel deserializedGame2 = new UnoGameModel(true, 3, 2);
        deserializedGame2.importGame("ogGame");

        assertEquals(ogGame.isDarkGame(), deserializedGame2.isDarkGame());
        assertEquals(ogGame.getCurrentPlayer() , deserializedGame2.getCurrentPlayer());
        assertEquals(ogGame.getNextPlayer(), deserializedGame2.getNextPlayer());
        assertEquals(ogGame.getCurrentCard().toString(), deserializedGame2.getCurrentCard().toString());
        assertEquals(ogGame.getPlayers(), deserializedGame2.getPlayers());
        assertEquals(ogGame.getCardDrawn(), deserializedGame2.getCardDrawn());
        assertEquals(ogGame.getPrevTopCard(), deserializedGame2.getPrevTopCard());

        File file = new File("ogGame.ser");
        file.delete();
    }
}
