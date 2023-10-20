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
    public void playCard(UnoGame game) {

    }

    @Override
    public String toString() {
        return super.getColour() + " " + this.number;
    }
}
