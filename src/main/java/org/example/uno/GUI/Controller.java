package org.example.uno.GUI;

import org.example.uno.cards.Card;
import org.example.uno.cards.DrawOneCard;
import org.example.uno.cards.SkipCard;
import org.example.uno.cards.WildCard;
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
                    for(Card ca: model.getCurrentPlayer().getHand()){
                        if(b.getClientProperty("card").equals(ca.toString())){
                            if(ca instanceof WildCard){
                                ca.setColour(getColourInput());
                                model.handleCurrentPlayerTurn(model.getCurrentPlayer(),ca);
                            }

                            else {
                                model.handleCurrentPlayerTurn(model.getCurrentPlayer(), ca);
                            }
                        }
                    }


            }
        }
    }
}