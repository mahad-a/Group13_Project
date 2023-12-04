package org.example.uno.cards;

import org.example.uno.game.*;

/**
 * The WildDrawTwoCard class represents a special type of UNO card, The wild draw two card.
 * This card allows a player to choose a color and forces the next player to draw two cards.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.2
 */
public class WildDrawTwoCard extends Card {
    private int value;
    public static final int LIGHTVALUE = 50;
    public static final int DARKVALUE = 60;
    private boolean challenged;
    private boolean guilty;
    private String message;
    private int addedCards = 1;

    /**
     * Constructs a wild draw two card.
     */
    public WildDrawTwoCard(){
        super();
        this.value = LIGHTVALUE;
    }

    /**
     * Plays the wild draw two card in the UNO game. The player of the card gets to choose the colour and the next
     * player must draw two cards.
     *
     * @param game The UNO game in which the card is being played.
     * @return {@code true} if the card was successfully played, {@code false} otherwise.
     */
    @Override
    public boolean playCard(UnoGameModel game) {

        if (game.isDarkGame()) {
            setValue(DARKVALUE);
            String colour = convertDarkColourToLight();

            if (challenged){
                if(! super.isCardPlaceable(game,this)){
                    Card c;
                    do {
                        c = game.takeFromDeck(game.getCurrentPlayer(), false, "");
                        addedCards++;
                    }
                    while (c.getColour() != game.getCurrentCard().getColour());
                    this.message = colour + " was chosen.\n" + game.getCurrentPlayer().getName()
                            + " was found GUILTY!\nDraw cards until colour +2";
                    guilty = true;
                } else{
                    Card c;
                    do {
                        c = game.takeFromDeck(game.getNextPlayer(), true, "");
                        addedCards++;
                    }
                    while (c.getColour() != game.getCurrentCard().getColour());
                    this.message = colour + " was chosen.\n" + game.getNextPlayer().getName()
                            + " must draw cards until\ncolour +2 \n Wild Draw Colour.";
                    guilty = false;
                }
            } else {
                Card c;
                do {
                    c = game.takeFromDeck(game.getNextPlayer(), true, "");
                    addedCards++;
                }
                while (c.getColour() != game.getCurrentCard().getColour());
                this.message = colour + " was chosen.\n" + game.getNextPlayer().getName()
                        + " must draw until colour\ndue to Wild Draw colour.";
                guilty = false;
            }
        }
        else {
            setValue(LIGHTVALUE);
            if(challenged){
                if(! super.isCardPlaceable(game,this)){
                    game.takeFromDeck(game.getCurrentPlayer(),false,"" );
                    game.takeFromDeck(game.getCurrentPlayer(),false,"" );
                    this.message = this.getColour() + " was chosen.\n" + game.getCurrentPlayer().getName()
                            + " was found GUILTY!\nDraw two cards";
                    guilty = true;
                } else {
                    game.takeFromDeck(game.getNextPlayer(), true, "");
                    game.takeFromDeck(game.getNextPlayer(), true, "");
                    this.message = this.getColour() + " was chosen.\n" + game.getNextPlayer().getName()
                            + " must draw 2 cards\ndue to Wild Draw Two.";
                    guilty = false;
                }
            } else {
                game.takeFromDeck(game.getNextPlayer(), true, "");
                game.takeFromDeck(game.getNextPlayer(), true, "");
                this.message = this.getColour() + " was chosen.\n" + game.getNextPlayer().getName()
                        + " must draw 2 cards\ndue to Wild Draw Two.";
                guilty = false;
            }
        }
        super.placeCard(game, this);

        return true;
    }

    @Override
    public void unPlayCard(UnoGameModel game) {
        this.setColour(null);
        if (game.isDarkGame()) { // draw colour
            if (challenged && guilty){ // current player is challenged and guilty
                int size = game.getCurrentPlayer().getHand().size(); // store the size
                for (int i = 1; i < addedCards; i++){
                    game.putBackInDeck(game.getCurrentPlayer().getHand().get(size - i),game.getCurrentPlayer());
                }
            } else {
                int size = game.getNextPlayer().getHand().size(); // store the size
                for (int i = 1; i < addedCards; i++){
                    game.putBackInDeck(game.getNextPlayer().getHand().get(size - i),game.getCurrentPlayer());
                }
                game.setSkipNextPlayer(false);
            }
        } else { // draw two
            if (challenged && guilty){ // current player is challenged and guilty
                System.out.println("CHALLENGED AND GUILTY");
                int size = game.getCurrentPlayer().getHand().size(); // store the size
                game.putBackInDeck(game.getCurrentPlayer().getHand().get(size - 1),game.getCurrentPlayer());
                game.putBackInDeck(game.getCurrentPlayer().getHand().get(size - 2),game.getCurrentPlayer());
                System.out.println("CARDS RETURNED");
            } else{ // not guilty or not challenged
                System.out.print("NOT GUILTY AND NOT CHALLENGED");
                int size = game.getNextPlayer().getHand().size(); // store the size
                game.putBackInDeck(game.getNextPlayer().getHand().get(size - 1),game.getNextPlayer());
                game.putBackInDeck(game.getNextPlayer().getHand().get(size - 2),game.getNextPlayer());
                game.setSkipNextPlayer(false);
                System.out.println("CARDS RETURNED");
            }
        }
    }

    /**
     * Gets the score value of the wild draw two card.
     *
     * @return The value of the card (50).
     */
    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    /**
     * Sets the player's decision to challenge or not challenge the WildDrawTwoCard.
     *
     * @param in The decision to challenge or not challenge.
     */
    public void setChallenged(String in){
        this.challenged = in.equals("YES");
    }

    /**
     * Retrieves a string representation of the message.
     *
     * @return A String message.
     */
    public String getMessage(){
        return this.message;
    }
    private String convertDarkColourToLight() {

        switch (String.valueOf(this.getColour())) {
            case "RED" -> {
                return "ORANGE";
            }
            case "YELLOW" -> {
                return "PINK";
            }
            case "GREEN" -> {
                return "PURPLE";
            }
            case "BLUE" -> {
                return "TEAL";
            }
        }
        return null;
    }

    /**
     * A string representation of the card and the chosen colour.
     *
     * @return A string representation of the card.
     */
    @Override
    public String toString() {
        if (this.getColour() == null) {
            return "WILD_DRAW_TWO_CARD";
        } else {
            return "WILD_DRAW_TWO_CARD (" + this.getColour() + ")";
        }
    }

    @Override
    public String getName() {
        return "WILD DRAW TWO ";
    }

    @Override
    public String getDarkName() {
        return "WILD DRAW COLOUR ";
    }
}

