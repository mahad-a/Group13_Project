import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The "Deck" class creates a deck of 112 cards, where 80 cards are normal number cards with 10
 * of each colour(0-9) and the rest would be for 8 of each special cards: DRAW_ONE, SKIP,
 * REVERSE, WILD, WILD_DRAW_TWO
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 *
 * @
 */
public class Deck {

    private List<Card> cards;

    /**
     * Deck constructs the entire deck and handles all assignation of 112 cards
     * to specific types, number and colours
     *
     */
    public Deck() {
        cards = new ArrayList<>();

        // add 80 number cards (10 of each colour)
        for (Card.Colour colour : Card.Colour.values()) {
            cards.add(new Card(colour, Card.Number.ZERO));
            for (Card.Number number : Card.Number.values()){
                if (number != Card.Number.ZERO) {
                    cards.add(new Card(colour, number));
                    cards.add(new Card(colour, number));
                }

            }
        }

        // adds 8 Draw One cards of each colour
        for (Card.Colour colour : Card.Colour.values()) {
            for (int i = 0; i < 8; i++) {
                cards.add(new Card(colour, Card.Type.DRAW_ONE));
            }
        }

        // adds 8 Skip cards of each colour
        for (Card.Colour colour : Card.Colour.values()) {
            for (int i = 0; i < 8; i++) {
                cards.add(new Card(colour, Card.Type.SKIP));
            }
        }

        // adds 8 Reverse cards of each colour
        for (Card.Colour colour : Card.Colour.values()) {
            for (int i = 0; i < 8; i++) {
                cards.add(new Card(colour, Card.Type.REVERSE));
            }
        }

        // add 4 Wild cards and 4 Wild Draw Two cards
        for (int i = 0; i < 4; i++) {
            cards.add(new Card(null, Card.Type.WILD));
            cards.add(new Card(null, Card.Type.WILD_DRAW_TWO));
        }

        // shuffle the deck
        shuffle();
    }

    /***
     * shuffles the deck and gives each player 7 random cards
     *
     */
    public void shuffle() {
        Collections.shuffle(cards); // randomizes the order of the deck
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // remove and return the first card from the deck
        } else {
            System.out.println("The deck is empty.");
            return null;
        }
    }


}