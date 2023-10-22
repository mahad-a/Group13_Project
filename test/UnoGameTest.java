import cards.NumberCard;
import game.Player;
import game.UnoGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnoGameTest {
    UnoGame unoGame;

    @BeforeEach
    public void createGame() {
        UnoGame unoGame = new UnoGame(true);
    }

    @Test
    public void testNextPlayer() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        Player gamma = new Player("Gamma");

        unoGame.addPlayer(alpha);
        unoGame.addPlayer(beta);
        unoGame.addPlayer(gamma);
        // right now players ArrayList should be ["Alpha", "Beta", "Gamma"]

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

        unoGame.dealCards();

        // check every player has 6 cards dealt to them
        for (Player player: unoGame.getPlayerList()) {
            assertTrue(player.getHand().size() < 7);
        }
    }

    @Test
        public void testDrawOne() {
            Player alpha = new Player("Alpha");
            Player beta = new Player("Beta");

            unoGame.addPlayer(alpha);
            unoGame.addPlayer(beta);

            unoGame.dealCards();

            unoGame.drawOne(alpha);
            assertTrue(alpha.getHand().size() > beta.getHand().size());
            // beta should have 1 card less than alpha
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
    public void testStartGame(){
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        Player gamma = new Player("Gamma");
        Player delta = new Player("Delta");

        unoGame.addPlayer(alpha);
        unoGame.addPlayer(beta);
        unoGame.addPlayer(gamma);
        unoGame.addPlayer(delta);

        unoGame.startGame();

        assertEquals(unoGame.getCurrentPlayer(), alpha);
        assertTrue(alpha.getHand().size() < 7);
        assertFalse(unoGame.gameOver());
    }



    @Test
    public void testGameOver() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        Player gamma = new Player("Gamma");
        Player delta = new Player("Delta");

        unoGame.addPlayer(alpha);
        unoGame.addPlayer(beta);
        unoGame.addPlayer(gamma);

        unoGame.dealCards();

        unoGame.addPlayer(delta);
        NumberCard throwCard = new NumberCard(NumberCard.Colour.RED, NumberCard.Number.SEVEN);
        delta.addCard(throwCard);

        delta.discardCard(throwCard);

        assertTrue(unoGame.gameOver());
    }

    /* This test is not done yet - Mahad
    @Test
    public void testHandlePlayerDraw() {
        Player alpha = new Player("Alpha");
        Player beta = new Player("Beta");
        Player gamma = new Player("Gamma");

        unoGame.addPlayer(alpha);
        unoGame.addPlayer(beta);
        unoGame.addPlayer(gamma);

        unoGame.dealCards();

        int beforeChoice = alpha.getHand().size();

        unoGame.handlePlayerTurn(alpha);
        // alpha draws a card
        assertTrue(beforeChoice < alpha.getHand().size());
    }

     */
}
