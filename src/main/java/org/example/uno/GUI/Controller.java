package org.example.uno.GUI;

import org.example.uno.AI.AIPlayer;
import org.example.uno.cards.*;
import org.example.uno.game.UnoGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The Controller class is the architecture for the UNO Game.
 * It also handles user interactions from GUI, such as button clicks, and communicates them with the UnoGame.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 * @version 1.0
 */
public class Controller implements ActionListener {
    private UnoGame model;

    /**
     * Constructs an instance of controller with a reference to the UnoGame model.
     *
     * @param game The UnoGame model to be controlled.
     */
    public Controller(UnoGame game) {
        this.model = game;
    }

    /**
     * Displays text for the user to choose the colour of a wild card, when a wild card is played.
     *
     * @return The chosen color for the WildCard.
     */
    private Card.Colour getColourInput() {
        Object[] selectionValues;
        if (model.isDarkGame()) {
            selectionValues = new Object[]{"PURPLE", "TEAL", "PINK", "ORANGE"};
        } else {
            selectionValues = new Object[]{"RED", "YELLOW", "BLUE", "GREEN"};
        }

        //String initialSelection = "Red";
        Object selection = JOptionPane.showInputDialog(null, "Choose A Colour", "Wild Card Colour", JOptionPane.QUESTION_MESSAGE, null, selectionValues, null);
        Card.Colour chosenCardColor = null;
        try {
            chosenCardColor = Card.Colour.valueOf(convertColour((String) selection));
        } catch (NullPointerException e) {
            System.out.println("The window was closed. No color chosen.");
        }
        return chosenCardColor;
    }

    /**
     * Converts the colour of the card from dark mode to light mode.
     *
     * @param colour The colour of the card in dark mode.
     */
    public String convertColour(String colour) {

        // window closed case
        if (colour == null) {
            return null;
        }

        if (model.isDarkGame()) {
            switch (colour) {
                case "ORANGE" -> {
                    return "RED";
                }
                case "PINK" -> {
                    return "YELLOW";
                }
                case "PURPLE" -> {
                    return "GREEN";
                }
                case "TEAL" -> {
                    return "BLUE";
                }
            }
        }
        return colour;
    }


    /**
     * A prompt for the user to choose whether they would like to challenge a wild draw two card.
     *
     * @return The user's choice to challenge or not.
     */
    private String getChallengeInput() {
        Object[] selectionValues = new Object[]{"YES", "NO"};

        // the AI will randomly decide to challenge or not
        if (model.getNextPlayer() instanceof AIPlayer){
            int randomIndex = new Random().nextInt(selectionValues.length);
            Object randomValue = selectionValues[randomIndex];
            return (String) randomValue;
        }
        int selection = JOptionPane.showOptionDialog(null, model.getCurrentPlayer().getName() + " played a Wild Draw Two card. Do you wish to challenge?",
                "Challenge", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, selectionValues, selectionValues[0]);
        return (selection == JOptionPane.YES_OPTION) ? "YES" : "NO";
    }

    /**
     * Responsible for ActionEvents triggered by interactions from the user with the GUI components.
     *
     * @param e The ActionEvent triggered by the user.
     */
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof JButton b) {
            switch (b.getText()) {
                case "Draw A Card" -> {
                    model.takeFromDeck(model.getCurrentPlayer(), false, "Drew a card: ");
                }
                case "Next Player" -> {
                    model.nextPlayer();
                    // automatically play for AI
                    if (model.getCurrentPlayer() instanceof AIPlayer) {
                        if(! model.isSkipNextPlayer()) {
                            model.handleAIMove();
                        }
                        else{
                            model.setSkipNextPlayer(false);
                            model.skipAI();
                        }
                    }

                }
                default -> {
                    for (int i = 0; i < model.getCurrentPlayer().getHand().size(); i++) {
                        Card ca = model.getCurrentPlayer().getHand().get(i);
                        if (b.getClientProperty("card").equals(ca.toString())) {
                            if (ca instanceof WildCard) {
                                Card.Colour colourInput = getColourInput();
                                if (colourInput != null) {
                                    ca.setColour(colourInput);
                                }
                            }

                            if (ca instanceof WildDrawTwoCard) {
                                Card.Colour colourInput = getColourInput();
                                if (colourInput != null) {
                                    ca.setColour(colourInput);
                                    ((WildDrawTwoCard) ca).setChallenged(getChallengeInput());
                                }
                            }

                            if (ca.getColour() != null) {
                                model.handleCurrentPlayerTurn(model.getCurrentPlayer(), ca);
                            }

                        }
                    }
                }
            }
        }
    }
}