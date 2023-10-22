package org.example.uno.game;


import org.example.uno.cards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The "game.Deck" class creates a deck of 112 cards, where 80 cards are normal number cards with 10
 * of each colour(0-9) and the rest would be for 8 of each special cards: DRAW_ONE, SKIP,
 * REVERSE, WILD, WILD_DRAW_TWO
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.2
 */
public class Deck {

    private List<Card> cards;

    /**
     * Constructs a deck of UNO cards with 112 cards. The deck is composed of 80 normal cards (10 cards of each
     * colour with numbers ranging from 0-9). It is also composed of 32 special cards (DRAW_TWO, SKIP, REVERSE,
     * WILD, WILD_DRAW_TWO). The constructor also shuffles the deck initially.
     *
     */
    public Deck() {
        cards = new ArrayList<>();

        // add 80 number cards (10 of each colour)
        for (Card.Colour colour : Card.Colour.values()) {
            cards.add(new NumberCard(colour, NumberCard.Number.ZERO)); // zero is only added once in each color
            for (NumberCard.Number number : NumberCard.Number.values()){
                if (number != NumberCard.Number.ZERO) {
                    cards.add(new NumberCard(colour, number)); // every other number is added twice
                    cards.add(new NumberCard(colour, number));
                }
            }
        }

        // adds 2 Skip cards of each colour
        for (Card.Colour colour : Card.Colour.values()) {
            cards.add(new SkipCard(colour));
            cards.add(new SkipCard(colour));
        }

        // adds 2 Reverse cards of each colour
        for (Card.Colour colour : Card.Colour.values()) {
            cards.add(new ReverseCard(colour));
            cards.add(new ReverseCard(colour));
        }

        // add 4 Wild cards and 4 Wild Draw Two cards
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard());
            cards.add(new WildCard());
        }

        // shuffle the deck
        shuffle();
    }

    /**
     * Shuffles the deck by randomizing the order of the cards in the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards); // randomizes the order of the deck
    }

    /**
     * Draws a card from the deck, and removes the card from the deck when drawn. If the deck is empty, a
     * message is printed and the method will return null.
     *
     * @return The card drawn from the deck, or null if the deck is empty.
     */
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // remove and return the first card from the deck
        } else {
            System.out.println("The deck is empty.");
            return null;
        }
    }
}
