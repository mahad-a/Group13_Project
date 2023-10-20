public class NumberCard extends Card {
    public enum Number{
        ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE
    }

    private Number number;

    public NumberCard(Colour colour, Number number ){
        super(colour);
        this.number = number;
    }

    public Number getNumber(){
        return this.number;
    }

    @Override
    public void playCard(UnoGame game) {
        Card currCard = game.getCurrentCard();
        if(currCard.getColour() == this.getColour()){
            game.setCurrentCard(this);
        }
        else if(currCard instanceof NumberCard){
            if (((NumberCard) currCard).getNumber() == this.number) {
                game.setCurrentCard(this);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.number;
    }
}
