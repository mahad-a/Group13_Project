package org.example.uno.game;

import org.example.uno.AI.*;
import org.example.uno.GUI.UnoEvent;
import org.example.uno.GUI.UnoGameModelView;
import org.example.uno.GUI.UnoView;
import org.example.uno.cards.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The UnoGameModel class represents the logic of the UNO game. It manages the players, the cards, and the flow of the game.
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
public class UnoGameModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Player> players;
    private List<UnoGameModelView> views;
    private int numPlayers, numAI;
    private Deck deck;
    private boolean darkGame; // if true, we are in dark game
    private static final Scanner scanner = new Scanner(System.in);
    private Card prevTopCard;
    private Card currentCard;

    private Player currentPlayer;
    private boolean skipNextPlayer;
    private Card cardDrawnn;

    private boolean roundOver;
    private boolean cardisDrawn;


    /**
     * Constructs an UNO game, initializing the list of players that are going to play, creates the players,
     * and sets their names based on user input, and setting the game to light mode or normal mode.
     *
     * @param darkGame  an indicator that indicates if the game is in "light" mode.
     */
    public UnoGameModel(boolean darkGame, int numberOfPlayers, int numberOfAI) {
        this.players = new ArrayList<>();
        this.darkGame = darkGame;
        this.numPlayers = numberOfPlayers;
        this.numAI = numberOfAI;
        views = new ArrayList<>();
        startGame();
        //** add a way to create 4 players and give them names using input **\\
    }

    /**
     * Adds a view observer to the game.
     *
     * @param v The UnoGameModelView to be added.
     */
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
     * Retrieves the previous top card
     * @return The previous top card
     */
    public Card getPrevTopCard(){

        return prevTopCard;
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
     * Restarts the game.
     *
     */
    public void setRoundOver(boolean roundOver) {
        this.roundOver = roundOver;
        this.darkGame = false;
        if( !(players.get(0).getName().equals("Player 1"))){
            ArrayList<Player> playersr = this.getPlayers();
            Collections.reverse(playersr);
            this.setPlayers(playersr);
            this.setCurrentPlayer(players.get(0));
        }
        for(UnoGameModelView v: views){
            v.restartGame();
        }
    }

    /**
     * Sets the current player who is meant to play their turn.
     *
     * @param player The player to set as the current player.
     */
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }


    /**
     * Checks if the program should skip the next player.
     *
     * @return true if the player should be skipped, false otherwise.
     */
    public boolean isSkipNextPlayer(){
        return this.skipNextPlayer;
    }

    /**
     * Sets the player that should be skipped next.
     *
     * @param x the player to be skipped.
     */
    public void setSkipNextPlayer(boolean x){this.skipNextPlayer = x;}

    /**
     * Sets the card that was drawn by the player.
     *
     * @param c The card to be set as the card that was drawn.
     */
    public void setCardDrawn(Card c){
        this.cardDrawnn = c;
    }

    /**
     * Gets the card that was drawn by the player.
     *
     * @return The card that was drawn by the player.
     */
    public Card getCardDrawn(){

        return this.cardDrawnn;
    }

    /**
     * Advances the turn to the next player, taking into consideration the effects of a special card.
     */
    public void nextPlayer(){
        int currPlayerIndex = this.players.indexOf(getCurrentPlayer());
        int nextPlayer = (currPlayerIndex + 1) % players.size();
        // handle reverse case when only 2 players
        setCurrentPlayer(players.get(nextPlayer));
        this.cardDrawnn = null;
        setCardDrawnBool(false);
        updateView(false,isSkipNextPlayer(),"");
    }

    /**
     * Updates the view when Skip Everyone card is invoked
     */
    public void skipEveryone() {
            updateView(false, false, getCurrentPlayer().getName() + " Skipped Everyone, \nand can play again!");
    }

    /**
     * Gets the player whose turn is next.
     *
     * @return the player who is meant to play next.
     */
    public Player getNextPlayer(){
        int currPlayerIndex = this.players.indexOf(getCurrentPlayer());
        int nextPlayer = (currPlayerIndex + 1) % players.size();
        return players.get(nextPlayer);
    }

    /**
     * Removes card from hand and returns to deck
     * @param card The card to be returned
     * @param p The player to remove card from
     */
    public void putBackInDeck(Card card,Player p){
        deck.addToDeck(card); // return card to deck
        p.getHand().remove(card); // remove card from hand
    }

    /**
     * Updates the view of the game by sending an event to all views that Undo button was invoked
     */
    public void undoView(){
        for(UnoGameModelView v: views){
            v.undoMove();
        }
    }

    /**
     * Updates the view of the game by sending an event to all views that Redo button was invoked
     */
    public void redoView(){
        for(UnoGameModelView v: views){
            v.redoMove();
        }
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
        this.prevTopCard = this.currentCard;
        this.currentCard = card;
        if(card instanceof SkipCard){
            updateView(true,false,this.getNextPlayer().getName() + " has to skip their turn\ndue to Skip Card");
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
    public boolean isDarkGame() {
        return darkGame;
    }

    /**
     * Sets the game's mode to light or normal.
     *
     * @param darkGame {@code true} if game in light mode, {@code false} if game in normal mode.
     */
    public void setDarkGame(boolean darkGame) {
        this.darkGame = darkGame;
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
     * Sets the boolean if card has been drawn or not
     * @param cardDrawn {@code true} if the card is drawn, {@code false} otherwise.
     */
    public void setCardDrawnBool(boolean cardDrawn){
        this.cardisDrawn = cardDrawn;
    }

    /**
     * Returns the boolean if card has been drawn or not
     * @return true if card has been drawn, false if not
     */
    public boolean checkIsCardDrawn(){
        return cardisDrawn;
    }

    /**
     * Updates the view of the game by sending an event to all views.
     * @param moveMade if a move was made
     * @param skipNext if the next player should be skipped
     * @param m A message related to the event that is taking place
     */
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
        // deck ran out of cards
        if (cardDrawn == null) {
            System.out.println("No more cards in the deck. Shuffling pile and adding to deck.");
            deck.shuffleDiscardPile();
            for (Card card : deck.getDiscardPile()) {
                if (card instanceof WildCard || card instanceof WildDrawTwoCard){
                    card.setColour(null);
                }
                deck.addToDeck(card);
            }
            cardDrawn = deck.drawCard();
        }


        player.addCard(cardDrawn);
        if (message.equals("Drew a card: ")){
            this.cardDrawnn = cardDrawn;
            if (isDarkGame()){
                updateView(true,false,  message + "\n" + revertColour(cardDrawn.getColour().toString()) + cardDrawn.getDarkName());
            }
            else{
                updateView(true,false,message + "\n" + revertColour(cardDrawn.getColour().toString()) + cardDrawn.getName());

            }
        }
        else {
            updateView(true,skipNext, message);
            this.skipNextPlayer = skipNext;
        }
        return cardDrawn;
    }

    /**
     * Reverts the colour of cards from light to dark
     * @param colour The colour to revert
     * @return The reverted colour
     */
    public String revertColour(String colour) {
        // window closed case
        if (colour == null) {
            return null;
        }

        if (isDarkGame()) {
            switch (colour) {
                case "RED" -> {
                    return "ORANGE ";
                }
                case "YELLOW" -> {
                    return "PINK ";
                }
                case "GREEN" -> {
                    return "PURPLE ";
                }
                case "BLUE" -> {
                    return "TEAL ";
                }
            }
        }
        return colour + " ";
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
     * Handles an AI's move by calling upon their unique strategy method
     */
    public void handleAIMove(){
        handleCurrentPlayerTurn(this.getCurrentPlayer(), ((AIPlayer) getCurrentPlayer()).strategyPlay(this));
    }

    /**
     * Updates view when to skip AI's turn
     */
    public void skipAI(){
        this.updateView(true,isSkipNextPlayer(),"");
    }

    /**
     * Handles a player's turn by allowing them to place a card, or draw a card if no placeable card is available.
     *
     * @param player The current player taking their turn.
     */
    public void handleCurrentPlayerTurn(Player player, Card card){
        // invalid card view update
        if (player instanceof AIPlayer) {  // AI view update
            // handle case that the AI has no cards to play
            if (card == null) {
                takeFromDeck(player, false,"Drew a card: ");
            } else {
                card.playCard(this);
                if (isDarkGame()){
                    updateView(true, false, player.getName() + " played: \n" + revertColour(card.getColour().toString()) + card.getDarkName());
                }
                else {
                    updateView(true, false, player.getName() + " played: \n" + revertColour(card.getColour().toString()) + card.getName());
                }
            }
        } else if (!card.playCard(this)) {
            updateView(false, false, "Invalid Card");
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
            // add in AI
            for (int j = 0; j < numAI; j++) {
                addPlayer(new AIHighestValue("AI " + j));
            }
        }
        // initialize deck
        this.deck = new Deck();
        this.roundOver = false;
        this.skipNextPlayer = false;
        dealCards();
        this.prevTopCard = null;
        this.cardDrawnn = null;
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

    /**
     * Clears the player's hand of Uno cards.
     */
    public void clearHand(){
        for (Player p : players){
            p.discardHand();
        }
    }

    /**
     * Imports a game from a file.
     * @param fileName The name of the file to import from
     * @return The UnoGameModel that was imported.
     */
    public void importGame(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + ".ser"))) {
            UnoGameModel importedGame = (UnoGameModel) ois.readObject();
            this.players = importedGame.players;
            this.numPlayers = importedGame.numPlayers;
            this.numAI = importedGame.numAI;
            this.deck = importedGame.deck;
            this.darkGame = importedGame.darkGame;
            this.prevTopCard = importedGame.prevTopCard;
            this.currentCard = importedGame.currentCard;
            this.currentPlayer = importedGame.currentPlayer;
            this.skipNextPlayer = importedGame.skipNextPlayer;
            this.cardDrawnn = importedGame.cardDrawnn;
            this.roundOver = importedGame.roundOver;
            this.cardisDrawn = importedGame.cardisDrawn;
            for(UnoGameModelView v: views){
                v.loadGame();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exports the current game to a file.
     *
     * @param fileName The name of the file to be exported to.
     */
    public void exportGame(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method that initiates the start of the UnoGameModel
     *
     * @param args The arguments for the command line.
     */
    public static void main(String[] args) {
        UnoGameModel unoGameModel = new UnoGameModel(true,2, 1);
        unoGameModel.startGame();
        scanner.close();
    }


}
