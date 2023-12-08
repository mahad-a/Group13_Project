package org.example.uno.GUI;

import org.example.uno.AI.AIPlayer;
import org.example.uno.cards.*;
import org.example.uno.game.UnoGameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Random;

/**
 * The UnoController class is the architecture for the UNO Game.
 * It also handles user interactions from GUI, such as button clicks, and communicates them with the UnoGameModel.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 * @version 1.0
 */
public class UnoController implements ActionListener, Serializable {
    private UnoGameModel model;

    /**
     * Constructs an instance of controller with a reference to the UnoGameModel model.
     *
     * @param game The UnoGameModel model to be controlled.
     */
    public UnoController(UnoGameModel game) {
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
        if(o instanceof JMenuItem){
            JMenuItem i = (JMenuItem) o;
            switch (i.getText()){
                case "Restart":
                    System.out.println("Restarting Game...");
                    int selection = JOptionPane.showOptionDialog(null, "Do you wish to restart the game?",
                            "Challenge", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(selection == JOptionPane.YES_OPTION){
                        model.setRoundOver(true);
                    }
                    break;
                case "Save":
                    System.out.println("Saving game...");
                    model.exportGame("SerializedGame");
                    break;
                case "Load":
                    System.out.println("Loading game...");
                    model.importGame("SerializedGame"); // import new game
                    break;
            }
        }
        if (o instanceof JButton b) {
            switch (b.getText()) {
                case "Undo" -> {
                    if(model.checkIsCardDrawn()){
                        Card kaka = model.getCurrentPlayer().getHand().get(model.getCurrentPlayer().getHand().size()-1);
                        model.putBackInDeck(kaka,model.getCurrentPlayer());
                        model.getCurrentPlayer().getHand().remove(kaka);
                        model.setCardDrawn(kaka);
                    }
                    else {
                        Card top = model.getPrevTopCard();
                        model.getCurrentPlayer().getHand().add(model.getCurrentCard());
                        model.getCurrentCard().unPlayCard(model);
                        model.setCurrentCard(top);
                    }

                    model.undoView();
                }

                case "Redo" -> {
                    if (model.checkIsCardDrawn()) {
                        model.getCurrentPlayer().getHand().add(model.getCardDrawn());
                    } else {
                        Card played = model.getPrevTopCard();
                        if (played instanceof WildCard || played instanceof WildDrawTwoCard) {
                            Card.Colour colourInput = getColourInput();
                            if (colourInput != null) {
                                played.setColour(colourInput);
                            }
                        }
                        played.playCard(model);
                    }
                    model.redoView();
                }
                    //redo
                case "Draw A Card" -> {
                    model.setCardDrawnBool(true);
                    model.takeFromDeck(model.getCurrentPlayer(), false, "Drew a card: ");
                }
                case "Next Player" -> {
                    model.nextPlayer();
                    model.setCardDrawnBool(false);
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