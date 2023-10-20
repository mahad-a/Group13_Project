public class SkipCard extends Card{

    public SkipCard(Colour colour){
        super(colour);
    }

    @Override
    public void playCard(Deck deck, Player player){
        Player nextPlayer;
        Player players;
        //player = players.getPl
        // firas stop for one second so i can run main
    }

    @Override
    public String toString() {
        return "SKIP-CARD " + super.getColour();
    }
}
