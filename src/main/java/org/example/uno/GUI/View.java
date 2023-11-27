package org.example.uno.GUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.*;
import javax.swing.ImageIcon;

import org.example.uno.AI.AIFirstCard;
import org.example.uno.AI.AIPlayer;
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
    private UnoGame model;
    private JLabel playerLabel;
    private JTextArea statusField;
    private JButton topCard;
    private JPanel hand;
    private JButton drawOneButton;
    private JButton currentCardDrawn;
    private JButton nextPlayer;
    private Controller unoController;
    private ArrayList<JButton> cards;
    private Integer playerNumber;
    private Integer AINumber;
    /**
     * Constructs a View, by initializing the elements of the GUI.
     */
    public View() {
        super("UNO");

        getNumPlayersInputs();

        while((this.playerNumber != null && this.AINumber != null) && (this.playerNumber + this.AINumber) < 2){

            JOptionPane.showMessageDialog(null, "Invalid Configuration. There must be at least one human player and a total of at least 2 players", "Invalid Player Configuration", JOptionPane.ERROR_MESSAGE);
            getNumPlayersInputs();
        }

        this.startGame(playerNumber, AINumber);
    }

    private void getNumPlayersInputs(){
        JDialog.setDefaultLookAndFeelDecorated(true);

        ImageIcon optionPanePicture = loadImage("/images/uno_pic.png");
        Image scaledImage = optionPanePicture.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH);
        ImageIcon scaledOptionPanePicture = new ImageIcon(scaledImage);

        Object selectionPlayer = JOptionPane.showInputDialog(null, "How many players?", "Select Players",
                JOptionPane.QUESTION_MESSAGE, scaledOptionPanePicture, new Object[]{1, 2, 3, 4, 5, 6}, 1);

        try {
            this.playerNumber = (Integer) selectionPlayer;

        } catch (NullPointerException e) {
            System.out.println("Exited player pane. Closing the game...");
        }


        Object selectionAI = JOptionPane.showInputDialog(null, "How many AI?",
                "Select AI Opponents", JOptionPane.QUESTION_MESSAGE, scaledOptionPanePicture, new Object[]{0, 1, 2, 3, 4, 5, 6},0);

        try {
            this.AINumber = (Integer) selectionAI;
        } catch (NullPointerException e) {
            System.out.println("Exited player pane. Closing the game...");
        }
    }

    /**
     * Initializes the layout of the GUI elements and starts the UNO game with the specified number of players.
     *
     * @param numPlayer The number of players playing the game.
     */
    private void startGame(Integer numPlayer, Integer numAI) {
        if (numPlayer != null) {
            this.model = new UnoGame(false, numPlayer, numAI);
            this.unoController = new Controller(model);
            this.model.addUnoView(this);
            cards = new ArrayList<>();
            setGuiLayout();

            playBackgroundMusic();

            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setSize(1600, 790);
            this.setVisible(true);
        }
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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,0));
        add(mainPanel,BorderLayout.CENTER);

        JPanel topCardView = new JPanel();
        topCardView.setBorder(new TitledBorder("Top Card"));
        topCard = new JButton();
        setIcon(topCard, model.getCurrentCard(), true);
        topCard.setEnabled(false);
        topCardView.add(topCard);
        mainPanel.add(topCardView);

        JPanel userArea = new JPanel();
        userArea.setLayout(new BorderLayout());
        mainPanel.add(userArea);

        JPanel statusArea = new JPanel();
        statusArea.setLayout(new BorderLayout());
        userArea.add(statusArea,BorderLayout.WEST);

        this.statusField = new JTextArea("Welcome to UNO.\nThe status will be shown here!");
        statusField.setEnabled(false);
        statusField.setPreferredSize(new Dimension(200, 65));
        new LineBorder(Color.BLACK, 1);
        statusField.setBorder(new EmptyBorder(10, 10, 10, 10));
        //statusField.setLineWrap(true);
        statusField.setWrapStyleWord(true);
        statusArea.add(statusField,BorderLayout.NORTH);

        // drawn card view
        this.currentCardDrawn = new JButton();
        currentCardDrawn.setBorderPainted(false);
        currentCardDrawn.setFocusPainted(false);
        statusArea.add(currentCardDrawn, BorderLayout.CENTER);


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

    /**
     * Sets the icon of the buttons based on the cards dealt the player and the top card in the pile.
     *
     * @param button the JButton we are setting the icon for.
     * @param card The card to be represented by the icon.
     * @param topCard Checker to determine if card is top card.
     */
    private void setIcon(JButton button, Card card, Boolean topCard){
        int width = 200;
        int height = 280;

        String imagePath = "/images/";
        imagePath += model.isDarkGame() ? ("DARK " + card.toString() + ".jpg") : (card.toString() + ".jpg");

        ImageIcon originalIcon = loadImage(imagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        if (topCard) {
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
        System.out.println(model.getCurrentPlayer().getName() + " has " + model.getCurrentPlayer().getHand().size() + " cards."); // for debugging
        System.out.println("Hand is: " + model.getCurrentPlayer().getHand());
    }

    /**
     * Updates the view of the GUI based on Uno Event from the Game Model.
     *
     * @param e The UnoEvent object that needs to be viewed.
     */
    @Override
    public void updateView(UnoEvent e) {

      updateHand();
      currentCardDrawn.setVisible(false);
      if(e.isMoveMade() || model.getCurrentPlayer() instanceof AIFirstCard) {
          for (JButton b : cards) {
              b.setEnabled(false);
          }
          drawOneButton.setEnabled(false);
          nextPlayer.setEnabled(true);

          if(e.getCardDrawn() != null){
              currentCardDrawn.setVisible(true);
              setIcon(currentCardDrawn,e.getCardDrawn(),false);
              model.setCardDrawn(null);
          }
      }
      else{
          nextPlayer.setEnabled(false);
          drawOneButton.setEnabled(true);
      }
      statusField.setText(e.getMessage());
      playerLabel.setText(model.getCurrentPlayer().getName());
      setIcon(topCard, model.getCurrentCard(), true);

      if(e.isSkipNextPlayer() && ! (model.getCurrentPlayer() instanceof AIPlayer)){
          for (JButton b : cards) {
              b.setEnabled(false);
          }
          drawOneButton.setEnabled(false);
          nextPlayer.setEnabled(true);
          model.setSkipNextPlayer(false);
      }
      if(model.isRoundOver()){
          handleRoundOver();
      }

    }


    /**
     * Handles the end of a round, displaying the scores of the players and asking if they would like to play a new round
     * or exit the game.
     *
     */
    private void handleRoundOver(){
        nextPlayer.setEnabled(false);
        drawOneButton.setEnabled(false);
        for (JButton b : cards) {
            b.setEnabled(false);
        }
        String str = "";
        //get scores
        for(Player p: model.getPlayers()){
            str += (p.getName() +"'s Score: " + p.getScore() + "\n");
        }
        str += "\nDo you wish to play again?";

        Object selection = JOptionPane.showInputDialog(null,
                model.getCurrentPlayer().getName() + " won this round!\n" + str,
                "Round Over!", JOptionPane.QUESTION_MESSAGE,
                null, new Object[]{"YES","NO"}, "YES");

        switch ((String) selection) {
            case "YES" -> {
                model.clearHand();
                model.startGame();
                updateHand();
                statusField.setText("New Round!!");
                playerLabel.setText(model.getCurrentPlayer().getName());
                setIcon(topCard, model.getCurrentCard(), true);
            }
            case "NO" -> {
                setVisible(false);
                dispose(); //Destroy the JFrame object
            }
        }
    }

    /**
     * Plays the background music for the UnoGame.
     */
    private static void playBackgroundMusic() {
        try {
            // Use class loader to get the URL of the resource
            URL url = View.class.getClassLoader().getResource("music/UNO_FLIP_MUSIC.wav");
            if (url != null) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.err.println("Could not find the music file: " + "UNO_FLIP_MUSIC.wav");
            }
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the image from the resources folder and returns it as an ImageIcon.
     * @param imagePath The path of the image.
     * @return ImageIcon of the image.
     */
    public static ImageIcon loadImage(String imagePath) {
        try {
            return new ImageIcon(Objects.requireNonNull(View.class.getResource(imagePath)));
        } catch (Exception e) {
            System.out.println("Error loading image: " + imagePath);
            e.printStackTrace();
            return null;
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
