import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UnoGame {

    private ArrayList<Player> players;
    private Deck deck;
    private boolean lightGame; // if true, we are in light game
    private static Scanner scanner = new Scanner(System.in);
    private Card currentCard;
    private Player playerTurn;

    private boolean gameOver;

    public UnoGame(boolean lightGame) {
        this.players = new ArrayList<>();
        this.deck = new Deck(); // Initialize the deck
        this.lightGame = lightGame;

        //** add a way to create 4 players and give them names using input
    }
    public void addPlayer(Player player){
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean isLightGame() {
        return lightGame;
    }

    public void setLightGame(boolean lightGame) {
        this.lightGame = lightGame;
    }


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

    public void startGame(){
        dealCards();
        this.gameOver = false;
        currentCard = deck.drawCard();
        playerTurn = players.get(0);
        System.out.println("Starting card is: " + currentCard.toString());


        System.out.println(playerTurn.getName() + "'s turn:");
        System.out.println(playerTurn.showHand());

        System.out.println("Top card: " + currentCard.toString());
        handlePlayerTurn(playerTurn);

    }

    public void gameOver(){
        for(Player player: players){
            if (player.getHand().isEmpty()){
                this.gameOver = true;
                player.updateMyScore(); // adds point to the winner
            }
        }
        this.gameOver = false;
    }

    public void handlePlayerTurn(Player player){
        int cardPlayed = Integer.parseInt(promptText("Enter card index to play or 0 to draw card"));

        if(cardPlayed == 0){
            drawOne(player);
        }

        if(player.getHand().get(cardPlayed-1) == null){
            cardPlayed = Integer.parseInt(promptText("Enter a VALID card index to play or 0 to draw card"));
        }

        currentCard = (Card) player.getHand().get(cardPlayed-1);
        player.getHand().remove(cardPlayed -1);



    }
    public Player getCurrentPlayer() {
        return playerTurn;
    }



//gg
}
