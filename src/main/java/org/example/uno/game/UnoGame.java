package org.example.uno.game;

import org.example.uno.cards.*;

import java.util.ArrayList;
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
    private int numPlayers;
    private Deck deck;
    private boolean lightGame; // if true, we are in light game
    private static final Scanner scanner = new Scanner(System.in);
    private Card currentCard;
    private Player currentPlayer;

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
        //startGame();
        //** add a way to create 4 players and give them names using input **\\
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

    /**
     * Advances the turn to the next player, taking into consideration the effects of a special card.
     */
    public void nextPlayer(){
        int currPlayerIndex = this.players.indexOf(getCurrentPlayer());
        int nextPlayer = (currPlayerIndex + 1) % players.size();
        // handle reverse case when only 2 players
        if (!(currentCard instanceof ReverseCard && players.size() == 2)){
            setCurrentPlayer(players.get(nextPlayer));
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
        this.currentCard = card;
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
    public Card takeFromDeck(Player player){
        Card cardDrawn = deck.drawCard();
        if (!(currentCard instanceof WildDrawTwoCard)) {
            System.out.print("Drew a card: " + cardDrawn.toString() + "\n");
        }
        player.addCard(cardDrawn);
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
     * Displays a prompt in the console and waits for a player to enter text.
     *
     * @param text The prompt text to be displayed
     * @return The text entered by the user.
     */
    public static String promptText(String text){
        System.out.print(text + ": ");
        return scanner.nextLine();
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
     * Prints a header with a centered title and a separator used to separate the header from other text.
     *
     * @param title The title of the header.
     * @param separator The separator of the header.
     */
    private void printHeader(String title, String separator){
        int consoleWidth = 50;
        int spaces = (consoleWidth - title.length()) / 2;
        String centeredTitle = String.format("%" + spaces + "s%s", "", title);
        System.out.println(separator.repeat(consoleWidth));
        System.out.println(centeredTitle);
        System.out.println(separator.repeat(consoleWidth));
    }

    /**
     * Handles a player's turn by allowing them to place a card, or draw a card if no placeable card is available.
     *
     * @param player The current player taking their turn.
     */
    public void handleCurrentPlayerTurn(Player player){
        int playerChoice;
        do {
            try {
                playerChoice = Integer.parseInt(promptText("Enter card index to play or 0 to draw card"));
                if (playerChoice >= 0 && playerChoice <= player.getHand().size()) {
                    break;
                } else {
                    System.out.println("Invalid entry. Please enter a card index in your hand.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid index. Please try again.");
            }
        } while (true);
        // draw the top card from the deck, and optionally play it if possible
        if(playerChoice == 0){
            takeFromDeck(player);
            nextPlayer(); // current logic: if player draws a card it counts as a turn
        } else {
            Card cardPlayed = player.getHand().get(playerChoice-1);
            if(!cardPlayed.playCard(this)){
                System.out.println("Cannot play this card.");
                handleCurrentPlayerTurn(player);
            }
            checkGameWinner(player);
        }
    }

    /**
     * Display the current player's hand and the top card on the table.
     */
    public void displayHand(){
        String gameSide = isLightGame() ? "Light" : "Dark";
        System.out.println("Current side: " + gameSide);
        System.out.println(currentPlayer.showHand());
        System.out.println("Top card: " + currentCard.toString());
    }

    /**
     * Starts the UNO game by dealing the cards, initializing the state of the game, and beginning the first turn.
     */
    public void startGame(){
        printHeader("Welcome to the UNO Flip Card Game", "=");

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
        dealCards();
        currentCard = deck.drawCard();
        while (!(currentCard instanceof NumberCard)){ // starting card cannot be action or wild card
            currentCard = deck.drawCard();
        }
        setCurrentPlayer(players.get(0));
        System.out.println("\nStarting card is: " + currentCard.toString());

        do{
            printHeader(getCurrentPlayer().getName() + "'s Turn", "-");
            displayHand();
            handleCurrentPlayerTurn(currentPlayer);
        }while (!this.roundOver);
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

            printHeader("Winner!", "~");
            System.out.println(player.getName() + " won this round!");
            // print scoreboard
            System.out.println("SCOREBOARD: ");
            for(Player p: players){
                System.out.println(p.getName() +"s Score: " + p.getScore());
            }
            String playerChoice = promptText("Do you wish to play again? (YES or NO) ");
            if(playerChoice.equalsIgnoreCase(YES)){
                // reset and start game
                for (Player p : players){
                    p.discardHand();
                }
                startGame();
                this.roundOver = false;
            } else if (playerChoice.equalsIgnoreCase(NO)) {
                gameOver();
            }
        }

    }

    /**
     * Checks if the UNO game is over depending on how many cards each player has in their hand.
     */
    public void gameOver(){
        printHeader("GAME OVER", "=");
        for(Player player: players){
            System.out.println(player.getName() +"s Score: " + player.getScore());
        }
        System.out.println("Thank you for playing!");
    }
    
//    public static void main(String[] args) {
//        UnoGame unoGame = new UnoGame(true);
//        unoGame.startGame();
//        scanner.close();
//    }

}
