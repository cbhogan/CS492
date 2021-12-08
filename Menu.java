
package blackjack;

/**
 * WIU CS492
 * @author Brandon Rias and Collin Hogan
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import sun.audio.*;

public class Menu extends JComponent implements ActionListener {

  ///initialize the 4 buttons for the menu component
  private JButton playBTN = new JButton("PLAY");
  private JButton exitBTN = new JButton("EXIT");
  private JButton helpBTN = new JButton("HELP");
  private JButton infoBTN = new JButton("INFO");
  private static BufferedImage backgroundImage; 

  //action listeners for all buttons to control what happens on click
  public Menu() { 
    playBTN.addActionListener(this); 
    exitBTN.addActionListener(this);
    helpBTN.addActionListener(this);
    infoBTN.addActionListener(this);
  }

  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g; 
    try {
      backgroundImage = ImageIO.read(new File("images/background.png")); 
    }
    catch(IOException e) {}
    
    //draw the background image
    g2.drawImage(backgroundImage, 0, 0, null);
    g2.setFont(new Font("Times New Roman", Font.BOLD, 100));
    g2.setColor(Color.WHITE);
    g2.drawString("Lets", 370, 100);
    g2.drawString("Play", 550, 190);
    g2.drawString("BLACKJACK!", 280, 300);
    g2.setFont(new Font("Arial", Font.BOLD, 30));
    g2.drawString("Developed by Brandon Rias and Collin Hogan", 220, 580); 
    
    //set the bounds of the buttons.
    playBTN.setBounds(400, 400, 150, 80);
    exitBTN.setBounds(600, 400, 150, 80);
    helpBTN.setBounds(200, 400, 150, 80);
    infoBTN.setBounds(800, 400, 150, 80);

    //set the fonts of writings on the buttons.
    playBTN.setFont(new Font("Times New Roman", Font.BOLD, 40));
    exitBTN.setFont(new Font("Times New Roman", Font.BOLD, 40));
    helpBTN.setFont(new Font("Times New Roman", Font.BOLD, 40));
    infoBTN.setFont(new Font("Times New Roman", Font.BOLD, 40));
    super.add(playBTN);
    super.add(exitBTN);
    super.add(helpBTN);
    super.add(infoBTN);
  }

  //controls button actions
  public void actionPerformed(ActionEvent e) {
    JButton selectedButton = (JButton)e.getSource();
    ///if the selected button is exit, exits the game
    if(selectedButton == exitBTN) { 
      System.exit(0);
    }
    
    //if the selected button is play, start the game.
    else if(selectedButton == playBTN) {
      Tester.currentState = Tester.menuSTATE.GAME; 
      Tester.menuFrame.dispose(); 
      Tester.refreshGame.start();
      Tester.checkGame.start();
    }
    
    //if the selected button is help, opens J Option Pane that will contain information
    else if(selectedButton == helpBTN) {
      JOptionPane.showMessageDialog(this, "The goal of blackjack is to beat the dealer's hand without going over 21." +
                      "\nFace cards are worth 10. Aces are wild, meaning they're worth 1 or 11." +
                      "\nEach player starts with two cards, one of the dealer's cards is hidden until the end." +
                      "\n'Hitting' is asking for another card for your hand." + 
                      "\n'Standing' is to hold your total and end your turn for the round." +
                      "\nIf you go over 21 you bust and the dealer wins." +
                      "\nIf the dealer goes over 21 they bust and the player wins." +
                      "\nThe dealer stands on 17 or higher, meaning they won't try for a better hand once their value is 17." +
                      "\nIf you are dealt 21 from the start (Ace & 10), you have blackjack.", "QUICK&EASY BLACKJACK HELP",
              JOptionPane.INFORMATION_MESSAGE);
    }
    
    //if the selected button is info, author information is shown.
    else if(selectedButton == infoBTN) {
      JOptionPane.showMessageDialog(this, "This project completed by Brandon Rias and Collin Hogan for" +
                                          "\nWestern Illinois University CS492.", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
    }
  }
  
  //plays a wav file
  public static void playAmbienceMusic() {
    try {
      InputStream in = new FileInputStream("sounds/casinoAmbience.wav"); 
      AudioStream audio = new AudioStream(in); 
      AudioPlayer.player.start(audio); 
    }
    catch(IOException e) {}
  }
}