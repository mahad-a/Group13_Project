import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UnoGame {

    private ArrayList<Player> players;
    private Deck deck;
    private boolean lightGame; // if true, we are in light game
    private static Scanner scanner = new Scanner(System.in);

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
            while (true){
                playerName = promptText("Enter a name for Player " + i);
                if (!unoGame.isPlayerNameExists(playerName)) {
                    unoGame.addPlayer(new Player(playerName));
                    break;
                } else {
                    System.out.println("Player name already exists. Please choose a different name.");
                }
            }
        }
        scanner.close();
    }
}
