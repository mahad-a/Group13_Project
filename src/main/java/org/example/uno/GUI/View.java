package org.example.uno.GUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.*;

import org.example.uno.cards.*;
import org.example.uno.game.Player;
import org.example.uno.game.UnoGame;

/**
 * The View class represents the GUI for the UNO game. It extends the JFrame class and implements the UnoGameModelView
 * interface. It is the visual representation of the game's current state and allows the players to interact with the
 * game using buttons and visual elements.
 *
 * @author Mahad Ahmed
 * @author Firas El-Ezzi
 * @author Hasib Khodayar
 * @author Hajar Assim
 * @author Yusuf Ibrahim
 *
 * @version 1.0
 */
public class View extends JFrame implements UnoGameModelView {
    UnoGame model;
    JLabel playerLabel;
    JPanel topCardView;
    JPanel userArea;
    JPanel statusArea;
    JTextArea statusField;
    JButton topCard;
    JPanel hand;
    JButton drawOneButton;
    JButton currentDrawnCard;
    JButton nextPlayer;
    Controller unoController;
    ArrayList<JButton> cards;

    /**
     * Constructs a View, by initializing the elements of the GUI.
     */
    public View() {
        super("UNO");
        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues = new Object[]{2, 3, 4};
        int initialSelection = 2;
        ImageIcon optionPanePicture = new ImageIcon("src/main/java/org/example/uno/GUI/images/uno_pic.png");
        Image scaledImage = optionPanePicture.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH);
        ImageIcon scaledOptionPanePicture = new ImageIcon(scaledImage);
        Object selection = JOptionPane.showInputDialog((Component)null, "How many players?", "Select Players", 3, scaledOptionPanePicture, selectionValues, Integer.valueOf(initialSelection));
        this.startGame((Integer)selection);
    }

    /**
     * Initializes the layout of the GUI elements and starts the UNO game with the specified number of players.
     *
     * @param numPlayer The number of players playing the game.
     */
    private void startGame(int numPlayer) {
        this.model = new UnoGame(true,numPlayer);
        this.unoController = new Controller(model);
        this.model.addUnoView(this);
        cards = new ArrayList<JButton>();
        setGuiLayout();

        playBackgroundMusic();

        this.setDefaultCloseOperation(3);
        this.setSize(1600, 790);
        this.setVisible(true);
    }

    /**
     * Sets the layout and components of the GUI.
     */
    private void setGuiLayout(){
        setLayout(new BorderLayout());
        this.playerLabel = new JLabel(model.getCurrentPlayer().getName());
        Font customFont = new Font("Courier New", Font.BOLD, 16);
        // Set the custom Font to the JLabel
        this.playerLabel.setFont(customFont);
        add(playerLabel,BorderLayout.NORTH);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,0));
        add(mainPanel,BorderLayout.CENTER);

        // top card area
        this.topCardView = new JPanel();
        topCardView.setBorder(new TitledBorder("Top Card"));
        topCard = new JButton();
        setIcon(topCard, model.getCurrentCard(), true);
        topCard.setEnabled(false);
        topCardView.add(topCard);
        mainPanel.add(topCardView);

        // user area
        this.userArea = new JPanel();
        userArea.setLayout(new BorderLayout());
        mainPanel.add(userArea);

        // status area
        statusArea = new JPanel();
        statusArea.setLayout(new BorderLayout());
        userArea.add(statusArea,BorderLayout.WEST);

        // status bar
        this.statusField = new JTextArea("Welcome to UNO. The card status will be shown here.");
        statusField.setEnabled(false);
        statusField.setPreferredSize(new Dimension(200, 65));
        LineBorder innerBorder = new LineBorder(Color.BLACK, 1);
        statusField.setBorder(new EmptyBorder(10, 10, 10, 10));
        statusField.setLineWrap(true);
        statusField.setWrapStyleWord(true);
        statusArea.add(statusField,BorderLayout.NORTH);

        // drawn card view
        this.currentDrawnCard = new JButton();
        currentDrawnCard.setBorderPainted(false);
        currentDrawnCard.setFocusPainted(false);
        statusArea.add(currentDrawnCard, BorderLayout.CENTER);

        // next player button
        this.nextPlayer = new JButton("Next Player");
        nextPlayer.addActionListener(unoController);
        statusArea.add(nextPlayer,BorderLayout.SOUTH);

        // hand layout
        this.hand = new JPanel();
        JScrollPane scroller = new JScrollPane(hand);
        userArea.add(scroller,BorderLayout.CENTER);

        hand.setLayout(new GridLayout());
        updateHand();
        nextPlayer.setEnabled(false);

        // draw a card button
        drawOneButton = new JButton("Draw A Card");
        drawOneButton.addActionListener(unoController);
        userArea.add(drawOneButton,BorderLayout.EAST);

    }

    /**
     * Sets the icon of the buttons based on the cards dealt the player and the top card in the pile.
     *
     * @param button the JButton we are setting the icon for.
     * @param card The card to be represented by the icon.
     * @param topCard Checker to determine if card is top card.
     */
    private void setIcon(JButton button, Card card, Boolean topCard){
        String imagePath = "src/main/java/org/example/uno/GUI/images/" + card.toString()+ ".jpg";
        int width = 200;
        int height = 280;
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        if (topCard){
            button.setDisabledIcon(scaledIcon);
        }
        button.setIcon(scaledIcon);
    }

    /**
     * Updates the player's hand in the GUI depending on the current state of the Game Model.
     */
    private void updateHand(){
        if(!cards.isEmpty()){
            for(JButton b: cards){
                b.removeActionListener(unoController);
                hand.remove(b);
            }
        }

        cards.clear();
        for(Card c: model.getCurrentPlayer().getHand()){
            JButton b = new JButton(); // for debugging:c.toString()
            b.putClientProperty("card", c.toString());
            setIcon(b,c, false);
            b.addActionListener(unoController);
            cards.add(b);
            hand.add(b);
        }
    }

    /**
     * Updates the view of the GUI based on Uno Event from the Game Model.
     *
     * @param e The UnoEvent object that needs to be viewed.
     */
    @Override
    public void updateView(UnoEvent e) {

      updateHand();
      currentDrawnCard.setVisible(false);
      if(e.isMoveMade()) {
          for (JButton b : cards) {
              b.setEnabled(false);
          }
          drawOneButton.setEnabled(false);
          nextPlayer.setEnabled(true);

          // update status for drawn card
          if (e.getCardDrawn() != null){
              currentDrawnCard.setVisible(true);
              setIcon(currentDrawnCard, e.getCardDrawn(), false);
          }
      }
      else{
          nextPlayer.setEnabled(false);
          drawOneButton.setEnabled(true);
      }
      statusField.setText(e.getMessage());
      playerLabel.setText(model.getCurrentPlayer().getName());
      setIcon(topCard, model.getCurrentCard(), true);

      if(e.isSkipNextPlayer()){
          for (JButton b : cards) {
              b.setEnabled(false);
          }
          drawOneButton.setEnabled(false);
          nextPlayer.setEnabled(true);
          model.setSkipNextPlayer(false);
      }
      if(model.isRoundOver()){
          handleRoundOver(e);
      }

    }


    private void handleRoundOver(UnoEvent e){
        String str = "";
        //get scores
        for(Player p: model.getPlayers()){
            str += (p.getName() +"'s Score: " + p.getScore() + "\n");
        }
        Object[] selectionValues = new Object[]{"YES","NO"};
        String initialSelection = "YES";
        Object selection = JOptionPane.showInputDialog((Component)null,
                model.getCurrentPlayer().getName() + " won this round!\n" + str,
                "Round Over!", 3,
                (Icon)null, selectionValues, initialSelection);
        switch((String) selection){
            case "YES":
                model.clearHand();
                model.startGame();
                updateHand();
                statusField.setText("New Round!!");
                playerLabel.setText(model.getCurrentPlayer().getName());
                setIcon(topCard, model.getCurrentCard(), true);
                break;
            case "NO":
                setVisible(false);
                dispose(); //Destroy the JFrame object
        }
    }

    private static void playBackgroundMusic() {
        try {
            File musicFile = new File ("src/main/java/org/example/uno/GUI/UNO_FILP_MUSIC.wav");
            //System.out.println(musicFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method that launches the GUI model.
     *
     * @param args The arguments for the command line.
     */
    public static void main(String[] args) {
        new View();
    }
}
