package org.example.uno.GUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.*;
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
    JButton topCard;
    JPanel hand;
    JButton drawOneButton;
    JButton nextPlayer;
    Controller unoController;
    ArrayList<JButton> cards;


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

    private void startGame(int numPlayer) {
        this.model = new UnoGame(true,numPlayer);
        this.unoController = new Controller(model);
        this.model.addUnoView(this);
        cards = new ArrayList<JButton>();
        setGuiLayout();

        playBackgroundMusic();

        this.setDefaultCloseOperation(3);
        this.setSize(1600, 700);
        this.setVisible(true);
    }

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

        this.topCardView = new JPanel();
        topCardView.setBorder(new TitledBorder("Top Card"));
        topCard = new JButton();
        setIcon(topCard, model.getCurrentCard(), true);
        topCard.setEnabled(false);
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

    public static void main(String[] args) {
        new View();
    }
}
