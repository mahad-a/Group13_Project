package org.example.uno.game;

import org.example.uno.GUI.UnoEvent;
import org.example.uno.GUI.UnoGameModelView;
import org.example.uno.cards.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The UnoGame class represents the logic of the UNO game. It manages the players, the cards, and the flow of the game.
 * The class is responsible for controlling the game, and the player interactions.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.1
 */
public class UnoGame {

    private ArrayList<Player> players;
    private List<UnoGameModelView> views;
    private int numPlayers;
    private Deck deck;
    private boolean lightGame; // if true, we are in light game
    private static final Scanner scanner = new Scanner(System.in);
    private Card currentCard;
    private Player currentPlayer;
    private boolean skipNextPlayer;
    private boolean roundOver;
    private static final String YES = "YES";
    private static final String NO = "NO";


    /**
     * Constructs an UNO game, initializing the list of players that are going to play, creates the players,
     * and sets their names based on user input, and setting the game to light mode or normal mode.
     *
     * @param lightGame  an indicator that indicates if the game is in "light" mode.
     */
    public UnoGame(boolean lightGame,int numberOfPlayers) {
        this.players = new ArrayList<>();
        this.lightGame = lightGame;
        this.numPlayers = numberOfPlayers;
        views = new ArrayList<UnoGameModelView>();
        startGame();
        //** add a way to create 4 players and give them names using input **\\
    }
    public void addUnoView(UnoGameModelView v){
        this.views.add(v);
    }

    /**
     * Sets the deck to be used for the game.
     *
     * @param deck The deck to be used.
     */
    public void setDeck(Deck deck){
        this.deck = deck;
    }

    /**
     * Retrieves the deck used in the game.
     *
     * @return The deck being used.
     */
    public Deck getDeck(){
        return this.deck;
    }

    /**
     * Adds a player to the game.
     *
     * @param player The player to be added.
     */
    public void addPlayer(Player player){
        players.add(player);
    }

    /**
     * Retrieves a list of the current players.
     *
     * @return An ArrayList of players in the game.
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Sets an ArrayList of players who are currently playing the game.
     *
     * @param players The list of players currently playing.
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Retrieves the current player whose turn it is to play.
     *
     * @return The current player taking their turn.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player who is meant to play their turn.
     *
     * @param player The player to set as the current player.
     */
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }
    public boolean isSkipNextPlayer(){
        return this.skipNextPlayer;
    }
    public void setSkipNextPlayer(boolean x){this.skipNextPlayer = x;}

    /**
     * Advances the turn to the next player, taking into consideration the effects of a special card.
     */
    public void nextPlayer(){
        int currPlayerIndex = this.players.indexOf(getCurrentPlayer());
        int nextPlayer = (currPlayerIndex + 1) % players.size();
        // handle reverse case when only 2 players

        setCurrentPlayer(players.get(nextPlayer));

        updateView(false,isSkipNextPlayer(),"");
    }

    public Player getNextPlayer(){
        int currPlayerIndex = this.players.indexOf(getCurrentPlayer());
        int nextPlayer = (currPlayerIndex + 1) % players.size();
        return players.get(nextPlayer);
    }

    /**
     * Retrieves the current card placed on the table.
     *
     * @return The current card in play.
     */

    public Card getCurrentCard(){
        return this.currentCard;
    }

    /**
     * Sets the current card on the table.
     *
     * @param card The card to be set as the current card.
     */
    public void setCurrentCard(Card card){
        this.currentCard = card;
        if(card instanceof SkipCard){
            updateView(true,false,this.getNextPlayer().getName() + " has to skip their turn due to Skip Card");
            return;
        }
        else if(card instanceof WildDrawTwoCard){
            updateView(true,false,((WildDrawTwoCard) card).getMessage());
            return;
        }

        updateView(true,false," ");
    }

    /**
     * Checks if the game is in light mode.
     *
     * @return true if the game is in light mode, false otherwise.
     */
    public boolean isLightGame() {
        return lightGame;
    }

    /**
     * Sets the game's mode to light or normal.
     *
     * @param lightGame {@code true} if game in light mode, {@code false} if game in normal mode.
     */
    public void setLightGame(boolean lightGame) {
        this.lightGame = lightGame;
    }

    /**
     * Checks if the current round is over.
     *
     * @return {@code true} if the round is over, {@code false} otherwise.
     */
    public boolean isRoundOver(){
        return this.roundOver;
    }

    /**
     * Sets the status of the current round.
     *
     * @param roundOver {@code true} if the round is over, {@code false} otherwise.
     */
    public void setRoundOver(boolean roundOver){
        this.roundOver = roundOver;
    }
    private void updateView(boolean moveMade,boolean skipNext,String m, Card cardDrawn){
        for(UnoGameModelView v: this.views){
            v.updateView(new UnoEvent(this,moveMade,skipNext,m, cardDrawn));
        }
    }
    private void updateView(boolean moveMade,boolean skipNext,String m){
        for(UnoGameModelView v: this.views){
            v.updateView(new UnoEvent(this,moveMade,skipNext,m));
        }
    }


    /**
     * Deals a set of cards to each player of the game.
     */
    public void dealCards(){
        for(Player player: players){
            for(int i = 0; i<7; i++){
                Card card = deck.drawCard();

                if (card != null) {
                    player.addCard(card);
                } else {
                    // Handle the case where there are no more cards in the deck.
                    System.out.println("No more cards in the deck.");
                    break;
                }
            }
        }
    }

    /**
     * Draws a card from the deck and gives it to the player.
     *
     * @param player The player taking a card from the deck.
     * @return The card that was taken from the deck and placed in the player's hand.
     */
    public Card takeFromDeck(Player player,boolean skipNext, String message){
        Card cardDrawn = deck.drawCard();

        player.addCard(cardDrawn);
        if (message.equals("Drew a Card:\n ")){
            updateView(true,false,message + cardDrawn.toString(), cardDrawn);
        }
        else {
            updateView(true,skipNext, message);
            this.skipNextPlayer = skipNext;
        }
        return cardDrawn;
    }

    /**
     * Checks if the name of the player is already taken from the list of players.
     *
     * @param playerName The name to be checked.
     * @return {@code true} if the name is available, {@code false} otherwise.
     */
    public boolean isPlayerNameExists(String playerName){
        if (playerName.isEmpty()){
            System.out.print("Cannot have an empty name. ");
            return true;
        }

        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                System.out.print("Player name already exists. ");
                return true;
            }
        }
        return false;
    }


    /**
     * Calculates the player's score based off of the score of each card in the player's hand.
     *
     * @param player The player whose score is to be calculated.
     * @return The player's score.
     */
    public int calculateScore(Player player){
        int score = player.getScore();
        for(Player p: players){
            for(Card card: p.getHand()){
                score += card.getValue();
            }
        }
        return score;
    }


    /**
     * Handles a player's turn by allowing them to place a card, or draw a card if no placeable card is available.
     *
     * @param player The current player taking their turn.
     */
    public void handleCurrentPlayerTurn(Player player, Card card){

            if(!card.playCard(this)){
                updateView(false,false,"Invalid Card");
            }
            checkGameWinner(player);
    }


    /**
     * Starts the UNO game by dealing the cards, initializing the state of the game, and beginning the first turn.
     */
    public void startGame(){

        // prompt user to input valid number of players
        if (!roundOver) {
            // initialize player names
            for (int i = 1; i <= numPlayers; i++) {
                addPlayer(new Player("Player " + i));
            }
        }
        // initialize deck
        this.deck = new Deck();
        this.roundOver = false;
        this.skipNextPlayer = false;
        dealCards();
        currentCard = deck.drawCard();
        while (!(currentCard instanceof NumberCard)){ // starting card cannot be action or wild card
            currentCard = deck.drawCard();
        }
        setCurrentPlayer(players.get(0));

    }

    /**
     * Checks if a player has won the round. And ends the round.
     *
     * @param player The player to be checked for winning.
     */
    public void checkGameWinner(Player player){

        if (player.getHand().isEmpty()){
            this.roundOver = true;
            player.updateScore(calculateScore(player)); // adds point to the winner
            updateView(false,false,"");

        }

    }
    public void clearHand(){
        for (Player p : players){
            p.discardHand();
        }
    }

    /**
     * Checks if the UNO game is over depending on how many cards each player has in their hand.
     */
    public void gameOver(){

    }
    
    public static void main(String[] args) {
        UnoGame unoGame = new UnoGame(true,2);
        unoGame.startGame();
        scanner.close();
    }


}
