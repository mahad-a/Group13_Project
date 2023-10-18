import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnoGame {

    private ArrayList<Player> players;
    private Deck deck;
    private boolean lightGame; // if true, we are in light game baby!!!

    public UnoGame(boolean lightGame) {
        this.players = new ArrayList<>();
        this.deck = new Deck(); // Initialize the deck
        this.lightGame = lightGame;

        //Add a way to create 4 players and give them names using input
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




}
