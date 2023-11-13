package org.example.uno.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.example.uno.cards.*;
import org.example.uno.game.Player;
import org.example.uno.game.UnoGame;

public class View extends JFrame implements UnoGameModelView {
    UnoGame model;
    JLabel playerLabel;
    JPanel topCardView;
    JPanel userArea;
    JPanel statusArea;
    JTextArea statusField;
    JTextField topCard;
    JPanel hand;
    JButton drawOneButton;
    JButton nextPlayer;
    Controller unoController;
    ArrayList<JButton> cards;
    boolean enable_buttons_flag = true;

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
        this.unoController = new Controller(model);
        this.model.addUnoView(this);
        cards = new ArrayList<JButton>();
        setGuiLayout();

        this.setDefaultCloseOperation(3);
        this.setSize(1000, 1000);
        this.setVisible(true);
    }

    private void setGuiLayout(){
        setLayout(new BorderLayout());
        this.playerLabel = new JLabel(model.getCurrentPlayer().getName());
        add(playerLabel,BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,0));
        add(mainPanel,BorderLayout.CENTER);

        this.topCardView = new JPanel();
        topCardView.setBorder(new TitledBorder("Top Card"));
        topCard = new JTextField(model.getCurrentCard().toString());
        topCardView.add(topCard);
        mainPanel.add(topCardView);

        this.userArea = new JPanel();
        userArea.setLayout(new BorderLayout());
        mainPanel.add(userArea);

        statusArea = new JPanel();
        statusArea.setLayout(new BorderLayout());
        userArea.add(statusArea,BorderLayout.WEST);

        this.statusField = new JTextArea("Status");
        statusField.setEnabled(false);
        statusArea.add(statusField,BorderLayout.CENTER);

        this.nextPlayer = new JButton("Next Player");
        nextPlayer.addActionListener(unoController);
        statusArea.add(nextPlayer,BorderLayout.SOUTH);

        this.hand = new JPanel();
        JScrollPane scroller = new JScrollPane(hand);
        userArea.add(scroller,BorderLayout.CENTER);

        hand.setLayout(new GridLayout());
        updateHand();
        nextPlayer.setEnabled(false);


        drawOneButton = new JButton("Draw A Card");
        drawOneButton.addActionListener(unoController);
        userArea.add(drawOneButton,BorderLayout.EAST);

    }

    private void setIcon(JButton button, Card card){

        String image = card.toString()+ ".jpg";
        // Set icon
        ImageIcon cardIcon = new ImageIcon(image);
        button.setIcon(cardIcon);
    }
    private void updateHand(){
        if(!cards.isEmpty()){
            for(JButton b: cards){
                b.removeActionListener(unoController);
                hand.remove(b);
            }
        }

        cards.clear();
        for(Card c: model.getCurrentPlayer().getHand()){
            JButton b = new JButton();
            setIcon(b,c);
            b.addActionListener(unoController);
            cards.add(b);
            hand.add(b);
        }
    }

    @Override
    public void updateView(UnoEvent e) {

      updateHand();
      if(e.isMoveMade()) {
          for (JButton b : cards) {
              b.setEnabled(false);
          }
          drawOneButton.setEnabled(false);
          nextPlayer.setEnabled(true);
      }
      else{
          nextPlayer.setEnabled(false);
          drawOneButton.setEnabled(true);
      }
      statusField.setText(e.getMessage());
      playerLabel.setText(model.getCurrentPlayer().getName());
      topCard.setText(model.getCurrentCard().toString());

      if(e.isSkipNextPlayer()){
          for (JButton b : cards) {
              b.setEnabled(false);
          }
          drawOneButton.setEnabled(false);
          nextPlayer.setEnabled(true);
          model.setSkipNextPlayer(false);
      }
      if(model.isRoundOver()){
          String str = " ";
          //get scores
          for(Player p: model.getPlayers()){
             str += (p.getName() +"s Score: " + p.getScore() + "\n");
          }
          Object[] selectionValues = new Object[]{"YES","NO"};
          String initialSelection = "YES";
          Object selection = JOptionPane.showInputDialog((Component)null,
                  model.getCurrentPlayer().getName() + " won this round!\n" + str,
                  "Round Over!", 3,
                  (Icon)null, selectionValues, initialSelection);
          switch((String) selection){
              case "Yes":
                  model.clearHand();
                  model.startGame();
                  break;
              case "NO":
                  setVisible(false);
                  dispose(); //Destroy the JFrame object
          }
      }

    }

    public static void main(String[] args) {
        new View();
    }
}
