import java.util.ArrayList;
import java.util.HashMap;
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
    private Deck deck;
    private boolean lightGame; // if true, we are in light game
    private static Scanner scanner = new Scanner(System.in);
    private Card currentCard;
    private Player playerTurn;

    private boolean gameOver;

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

    /**
     * Retrieves the current player who's turn it is to play.
     *
     * @return The current player taking their turn.
     */
    public Player getCurrentPlayer() {
        return playerTurn;
    }

    public void setCurrentPlayer(Player player) {
        this.playerTurn = player;
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

        if (cardDrawn != null) {
            player.addCard(cardDrawn);
        } else {
            // Handle the case where there are no more cards in the deck.
            System.out.println("No more cards in the deck.");
        }
    }

    // make javadocs: checks the player arraylist if the player exists
    public boolean isPlayerNameExists(String playerName){
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return true;
            }
        }
        return false;
    }



    private static String promptText(String text){
        System.out.print(text + ": ");
        return scanner.nextLine();
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
                    System.out.println("Player name already exists. Please choose a different name.");
                }
            }
        }
        unoGame.startGame();
        scanner.close();
    }

    /**
     * Starts the UNO game by dealing the cards, initializing the state of the game, and beginning the first turn.
     */
    public void startGame(){
        dealCards();
        this.gameOver = false;
        currentCard = deck.drawCard();
        playerTurn = players.get(0);
        System.out.println("Starting card is: " + currentCard.toString());


        displayHand();
        handlePlayerTurn(playerTurn);
    }

    /**
     * Checks if the UNO game is over depending on how many cards each player has in their hand.
     */
    public void gameOver(){
        for(Player player: players){
            if (player.getHand().isEmpty()){
                this.gameOver = true;
                player.updateMyScore(); // adds point to the winner
            }
        }
        this.gameOver = false;
    }

    /**
     * Handles a player's turn by allowing them to place a card, or draw a card if no placeable card is available.
     *
     * @param player The current player taking their turn.
     */
    public void handlePlayerTurn(Player player){
        int cardPlayed = Integer.parseInt(promptText("Enter card index to play or 0 to draw card"));

        if(cardPlayed == 0){
            drawOne(player);
        }

        if(player.getHand().get(cardPlayed-1) == null){
            cardPlayed = Integer.parseInt(promptText("Enter a VALID card index to play or 0 to draw card"));
        }

        Card cardplayed =  (Card) player.getHand().get(cardPlayed-1);
        cardplayed.playCard(this);


        displayHand();

    }

    /**
     * Display the current player's hand and the top card on the table.
     */
    public void displayHand(){
        System.out.println(playerTurn.getName() + "'s turn:");
        System.out.println(playerTurn.showHand());

        System.out.println("Top card: " + currentCard.toString());
    }

}
