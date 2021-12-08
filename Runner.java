 
package blackjack;

/**
 * 
 * @author Brandon Rias and Collin Hogan
 */
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import java.util.ArrayList;


//MouseListener
public class Runner extends JComponent implements MouseListener {
  //Three images: one for the background image
  public BufferedImage backgroundTable; 
  //one for the blackjack logo 
  public BufferedImage bjLogo;
  //one the chip.
  public BufferedImage chip;
  //card arraylists serve as hands: one for the dealer and for the player.
  private ArrayList<Card> dealerHand;
  private ArrayList<Card> playerHand;
  //dealer's score
  private int dealerScore;
  //player's score
  private int playerScore;
  //boolean for facedown or faceup
  public boolean faceDown = true; 
  //check for bet, stores current balance, and stores current bet
  public static boolean betPlaced = false;
  private int currentBalance; 
  public static int currentBet;

  //constructor for class
  public Runner(ArrayList<Card> dH, ArrayList<Card> pH) { 
    dealerHand = dH;
    playerHand = pH;
    dealerScore = 0;
    playerScore = 0;
    currentBalance = 1000; 
    addMouseListener(this);
  }

  public void paintComponent(Graphics g) {
    //Graphics2D g2 is amore powerful brush.
    Graphics2D g2 = (Graphics2D) g; 
    /*
     *reads a file for a poker table
     *reads a file for a blackjack logo
     *reads a file for a poker chip
    */
    try {
      backgroundTable = ImageIO.read(new File("images/background.png"));
      bjLogo = ImageIO.read(new File("images/blackjackLogo.png")); 
      chip = ImageIO.read(new File("images/chip.png")); 
    }
    catch(IOException e) {}

    //draw these images to the component.
    g2.drawImage(backgroundTable, 0, 0, null);
    g2.drawImage(bjLogo, 510, 200, null);
    g2.drawImage(chip, 90, 300, null);
    //set the colors.
    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Times New Roman", Font.BOLD, 30)); 
    //draw these strings which help to visualize the game.
    g2.drawString("DEALER'S HAND", 465, 50);
    g2.drawString("PLAYER'S HAND", 465, 380);
    g2.drawString("DEALER WINS: ", 50, 100);
    //draw the dealer's score
    g2.drawString(Integer.toString(dealerScore), 300, 100);
    g2.drawString("PLAYER WINS: ", 50, 150);
    //draw the player's score
    g2.drawString(Integer.toString(playerScore), 300, 150); 
    g2.setFont(new Font("Times New Roman", Font.BOLD, 15));
    g2.drawString("To start each round, you must place a bet.", 50, 250);
    g2.drawString("Place a bet by clicking the chip below.", 50, 270);
    g2.drawString("For a better gaming experience, ", 830, 550);
    g2.drawString("play with your sound on!", 830, 570);
    g2.setFont(new Font("Times New Roman", Font.BOLD, 20));
    //current balance
    g2.drawString("CHIP BALANCE: " + currentBalance, 50, 200); 

    //write the time in hh:mm:ss to the screen
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    g2.drawString(sdf.format(cal.getTime()), 1020, 20); 

    //catches blocks, printCard draws images chopped off a spritesheet image from a 2D-array.
    try { 
      //go through dealer's hand
      for (int i = 0; i < dealerHand.size(); i++) {
        if (i == 0) {
          //checks if card will be facedown or faceup
          if(faceDown) {
            //draw each card.
            dealerHand.get(i).printCard(g2, true, true, i);
          }
          else {
            dealerHand.get(i).printCard(g2, true, false, i); 
          }
        }
        else 
        {
          dealerHand.get(i).printCard(g2, true, false, i);
        }
      }
    }
    catch (IOException e) {}
    try {
      //go through each of the cards in user's hand
      for (int i = 0; i < playerHand.size(); i++) { 
        playerHand.get(i).printCard(g2, false, false, i);
      }
    }
    catch (IOException e) {}
  }

  //this refresh method will refresh the Board when it is called.
  public void refresh(int cB, int uS, int dS, boolean fD) { 
    currentBalance = cB;
    playerScore = uS;
    dealerScore = dS;
    faceDown = fD;
    this.repaint();
  }

  //controls mouse events of the user
  public void mousePressed(MouseEvent e) {
    //get the x and y coordinates of the user's mouse
    int mouseX = e.getX(); 
    int mouseY = e.getY();

    //only does something if the x and y coordinates fall on top of the chip image
    if(mouseX>= 50 && mouseX<=200 && mouseY>=300 && mouseY<=450) {
      //user clicks on the chip image, bet is made
      betPlaced = true; 
      String[] options = new String[] {"1", "5", "10", "25", "100"}; 
      //dialog box that will popup when user clicks the chip
      int response = JOptionPane.showOptionDialog(null, "Please enter your betting amount!", "BETTING",
              JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
      if(response == 0) {
        currentBet = 1; 
        currentBalance -= 1; 
      }
      else if(response == 1) {
        currentBet = 5;
        currentBalance -= 5;
      }
      else if(response == 2) {
        currentBet = 10;
        currentBalance -= 10;
      }
      else if(response == 3) {
        currentBet = 25;
        currentBalance -= 25;
      }
      else if(response == 4) {
        currentBet = 100;
        currentBalance -= 100;
      }
      //assigns the bet as 1 for default value.
      else {
        currentBet = 1;
        currentBalance -= 1;
        System.out.println("You haven't selected a proper bet. Thus, the bet is taken as 1.");
      }

      System.out.println("You have made your bet: " + currentBet + "." + " If you beat the dealer, you will increase your current balance by " + currentBet*2 +
              "; if the dealer beats you you will decrease your current balance by " + currentBet + ".");
      Tester.newGame.startGame();
    }

  }
  public void mouseExited(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}

    public static void playChipsSettle() {
    try {
      //first reads the wav file, stores it as audio stream, and then plays it.
      InputStream in = new FileInputStream("sounds/chipsSettle.wav"); 
      AudioStream audio = new AudioStream(in);
      AudioPlayer.player.start(audio); 
    }
    catch(IOException e) {}
  }
}