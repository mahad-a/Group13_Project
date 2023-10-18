public class Card {

    public enum Colour {
        BLUE, RED, GREEN, YELLOW
    }

    public enum Type {
        NUMBER, DRAW_ONE, SKIP, REVERSE, WILD, WILD_DRAW_TWO
    }

    private Colour colour;
    private Type type;
    private int number;

    public Card(Colour colour, Type type, int number) {
        this.colour = colour;
        this.type = type;
        this.number = number;

    }

    public Card(Colour colour, Type type) {
        this.colour = colour;
        this.type = type;
    }

    public Colour getColour() {
        return colour;
    }

    public Type getType() {
        return type;
    }

    // Display the card, EX (RED SKIP)
    public String toString() {
        return colour + " " + type;
    }
}
