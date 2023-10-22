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

        // add 18 (1-9 twice) of each colour cards
        for (Card.Colour colour : Card.Colour.values()) {
            for (NumberCard.Number number : NumberCard.Number.values()){
                cards.add(new NumberCard(colour, number));
                cards.add(new NumberCard(colour, number));
            }
        }

        // add 8 Skip cards of each colour
        for (Card.Colour colour : Card.Colour.values()) {
            for(int i = 0; i<2;i++) {
                cards.add(new SkipCard(colour));
            }
        }

        // add 8 Reverse cards of each colour
        for (Card.Colour colour : Card.Colour.values()) {
            for(int i = 0; i<2;i++) {
                cards.add(new ReverseCard(colour));
            }
        }

        // add 4 Wild cards and 4 Wild Draw Two cards
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard());
            cards.add(new WildCard());
        }

        // add 8 Draw One Cards
        for (Card.Colour colour : Card.Colour.values()) {
            for(int i = 0; i<2;i++) {
                cards.add(new DrawOneCard(colour));
            }
        }

        // shuffle the deck
        shuffle();
    }

    /**
     * Gets the amount of cards remaining in the deck.
     *
     * @return The size of the deck.
     */
    public int getDeckSize(){
        return cards.size();
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
