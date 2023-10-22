package game;

import cards.Card;
import cards.NumberCard;
import cards.ReverseCard;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The game.UnoGame class represents the logic of the UNO game. It manages the players, the cards, and the flow of the game.
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
    private Deck deck;
    private boolean lightGame; // if true, we are in light game
    private static final Scanner scanner = new Scanner(System.in);
    private Card currentCard;
    private Player playerTurn;


    /**
     * Constructs an UNO game.
     *
     * @param lightGame  an indicator that indicates if the game is in "light" mode.
     */
    public UnoGame(boolean lightGame) {
        this.players = new ArrayList<>();
        this.deck = new Deck(); // Initialize the deck
        this.lightGame = lightGame;

        //** add a way to create 4 players and give them names using input **\\
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
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Retrieves the current player whose turn it is to play.
     *
     * @return The current player taking their turn.
     */
    public Player getCurrentPlayer() {
        return playerTurn;
    }


    public void setCurrentPlayer(Player player) {
        this.playerTurn = player;
    }

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

    public ArrayList<Player> getPlayerList() {
        return this.players;
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
     * @param lightGame true if game in light mode, false if game in normal mode.
     */
    public void setLightGame(boolean lightGame) {
        this.lightGame = lightGame;
    }


    /**
     *
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

    public void drawOne(Player player){
        Card cardDrawn = deck.drawCard();

        System.out.print("Drew a card: " + cardDrawn.toString() + "\n\n");
        player.addCard(cardDrawn);
        // current handling: **DOUVLE CHECK IF ITS SUPPOED TO
        // if you pick up a card it will count as a turn
        nextPlayer();
    }

    // make javadocs: checks the player arraylist if the player exists
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



    public static String promptText(String text){
        System.out.print(text + ": ");
        return scanner.nextLine();
    }

    /**
     * Starts the UNO game by dealing the cards, initializing the state of the game, and beginning the first turn.
     */
    public void startGame(){
        dealCards();
        currentCard = deck.drawCard();
        // ***double check but i think the first card has to be a number card
        while (!(currentCard instanceof NumberCard)){
            currentCard = deck.drawCard();
        }
        playerTurn = players.get(0);
        setCurrentPlayer(players.get(0));
        System.out.println("\nStarting card is: " + currentCard.toString());

        do{
            handlePlayerTurn(playerTurn);

        }while (!gameOver());
    }

    /**
     * Checks if the UNO game is over depending on how many cards each player has in their hand.
     */
    public boolean gameOver(){
        for(Player player: players){
            if (player.getHand().isEmpty()){
                player.updateMyScore(); // adds point to the winner
                return true;
            }
        }
        return false;
    }

    /**
     * Handles a player's turn by allowing them to place a card, or draw a card if no placeable card is available.
     *
     * @param player The current player taking their turn.
     */
    public void handlePlayerTurn(Player player){
        displayHand();
        int playerChoice = Integer.parseInt(promptText("Enter card index to play or 0 to draw card"));
        // player chose to draw deck
        if(playerChoice == 0){
            drawOne(player);

        } else if(player.getHand().get(playerChoice-1) == null){ // player chose a card out of bounds
            playerChoice = Integer.parseInt(promptText("Enter a VALID card index to play or 0 to draw card"));
        } else {
            Card cardPlayed =  (Card) player.getHand().get(playerChoice-1);
            cardPlayed.playCard(this);
        }

        System.out.print("===================================================\n");
    }

    /**
     * Display the current player's hand and the top card on the table.
     */
    public void displayHand(){
        System.out.println(playerTurn.getName() + "'s turn:");
        System.out.println(playerTurn.showHand());

        System.out.println("Top card: " + currentCard.toString());
    }

    public static void main(String[] args) {

        UnoGame unoGame = new UnoGame(true);

        int numPlayers = Integer.parseInt(promptText("Enter a number of players (2-4)"));
        numPlayers = (numPlayers >= 2 && numPlayers <= 4) ? numPlayers : Integer.parseInt(promptText("Enter a number of players (2-4)")); // invalid input of players

        // prompt player names and add to game
        for (int i = 1; i<=numPlayers; i++){
            String playerName;
            while (true) {
                playerName = promptText("Enter a name for Player " + i);
                if (!unoGame.isPlayerNameExists(playerName)) {
                    unoGame.addPlayer(new Player(playerName));
                    break;
                } else {
                    System.out.println("Please choose a different name.");
                }
            }
        }
        unoGame.startGame();
        scanner.close();
    }

}
