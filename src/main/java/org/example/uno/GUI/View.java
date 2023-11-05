package org.example.uno.GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.example.uno.game.UnoGame;

public class View extends JFrame {
    UnoGame model;
    JLabel playerLabel;
    JPanel topCard;
    JPanel userArea;
    JTextField statusField;
    JPanel hand;
    JButton drawOneButton;

    public View() {
        super("UNO");
        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues = new Object[]{2, 3, 4};
        int initialSelection = 2;
        Object selection = JOptionPane.showInputDialog((Component)null, "How many players?", "Select Players", 3, (Icon)null, selectionValues, Integer.valueOf(initialSelection));
        this.startGame((Integer)selection);
    }

    private void startGame(int numPlayer) {
        this.model = new UnoGame(true,numPlayer);

        setGuiLayout();

        this.setDefaultCloseOperation(3);
        this.setSize(1000, 1000);
        this.setVisible(true);
    }

    private void setGuiLayout(){
        setLayout(new BorderLayout());
        this.playerLabel = new JLabel("Player x");
        add(playerLabel,BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,0));
        add(mainPanel,BorderLayout.CENTER);

        this.topCard = new JPanel();
        topCard.setBorder(new TitledBorder("Top Card"));
        mainPanel.add(topCard);

        this.userArea = new JPanel();
        userArea.setLayout(new GridLayout(0,3));
        mainPanel.add(userArea);

        this.statusField = new JTextField("Status");
        userArea.add(statusField);


        this.hand = new JPanel();
        JScrollPane scroller = new JScrollPane(hand);
        userArea.add(scroller);

        hand.setLayout(new GridLayout());
        for(int i = 0; i < 7; i++) {
            JButton b = new JButton("card x");
            hand.add(b);
        }


        drawOneButton = new JButton("Draw A Card");
        userArea.add(drawOneButton);

    }

    public static void main(String[] args) {
        new View();
    }
}
