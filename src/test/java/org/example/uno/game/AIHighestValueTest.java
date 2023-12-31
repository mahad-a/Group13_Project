package org.example.uno.game;

import org.example.uno.AI.AIFirstCard;
import org.example.uno.AI.AIHighestValue;
import org.example.uno.cards.Card;
import org.example.uno.cards.NumberCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AIHighestValueTest {
    UnoGameModel game;
    Deck deck;
    AIHighestValue aiPlayer;
    Player player;


    @Before
    public void setGame(){
        game = new UnoGameModel(false, 1,1);
        player = new Player("FIRAS ELEZZI");
        aiPlayer = new AIHighestValue("TEST ");
        game.addPlayer(player);
        game.addPlayer(aiPlayer);

        aiPlayer.addCard(new NumberCard(NumberCard.Colour.BLUE, NumberCard.Number.THREE));
        aiPlayer.addCard(new NumberCard(NumberCard.Colour.RED, NumberCard.Number.TWO));
        aiPlayer.addCard(new NumberCard(NumberCard.Colour.GREEN, NumberCard.Number.ONE));
        aiPlayer.addCard(new NumberCard(NumberCard.Colour.YELLOW, NumberCard.Number.FOUR));
        aiPlayer.addCard(new NumberCard(NumberCard.Colour.BLUE, NumberCard.Number.FIVE));

        deck = new Deck();
        game.setCurrentCard(new NumberCard(Card.Colour.BLUE, NumberCard.Number.TWO));
        game.setCurrentPlayer(aiPlayer);


    }

    @Test
    public void testStrategyPlay(){
        NumberCard testCard = new NumberCard(Card.Colour.YELLOW, NumberCard.Number.THREE);
        NumberCard actualCard = (NumberCard) aiPlayer.strategyPlay(game);

        assertEquals(testCard.toString(), actualCard.toString());


    }
}
