package org.example.uno.GUI;

import org.example.uno.cards.*;
import org.example.uno.game.UnoGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private UnoGame model;
    public Controller(UnoGame game) {
        this.model = game;
    }
    private Card.Colour getColourInput(){
        Object[] selectionValues = new Object[]{"RED","YELLOW","BLUE","GREEN"};
        String initialSelection = "Red";
        Object selection = JOptionPane.showInputDialog((Component)null, "Choose A Colour", "Wild Card Colour", JOptionPane.QUESTION_MESSAGE, (Icon)null, selectionValues, String.valueOf(initialSelection));
        return Card.Colour.valueOf((String) selection);
    }

    private String getChallengeInput(){
        Object[] selectionValues = new Object[]{"YES","NO"};
        String initialSelection = "YES";
        Object selection = JOptionPane.showInputDialog((Component)null, model.getCurrentPlayer().getName() + " played a Wild Draw Two card. Do you wish to challenge?", "Challenge", JOptionPane.QUESTION_MESSAGE, (Icon)null, selectionValues, String.valueOf(initialSelection));
        return ((String) selection);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o instanceof JButton){
            JButton b = (JButton) o;
            switch(b.getText()){

                case "Draw A Card":
                    Card c = model.takeFromDeck(model.getCurrentPlayer(),false,"Drew a Card: ");
                    break;
                case "Next Player":

                    model.nextPlayer();
                    break;
                default:
                    for(int i = 0; i < model.getCurrentPlayer().getHand().size(); i++ ){
                        Card ca = model.getCurrentPlayer().getHand().get(i);
                        if(b.getClientProperty("card").equals(ca.toString())){
                            if(ca instanceof WildCard){
                                ca.setColour(getColourInput());
                            }

                            if(ca instanceof WildDrawTwoCard){
                                ca.setColour(getColourInput());
                                ((WildDrawTwoCard) ca).setChallenged(getChallengeInput());
                            }

                            model.handleCurrentPlayerTurn(model.getCurrentPlayer(), ca);

                        }
                    }


            }
        }
    }
}