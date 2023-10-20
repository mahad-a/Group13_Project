public class NumberCard extends Card {
    public enum Number{
        ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE
    }

    private Number number;

    public NumberCard(Colour colour, Number number ){
        super(colour);
        this.number = number;
    }

    @Override
    public void playCard(Deck deck, Player player) {

    }

    @Override
    public String toString() {
        return this.number + " " + super.getColour();
    }
}
