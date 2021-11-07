package blackjack;

/**
 *
 * @author Brandon Rias and Collin Hogan
 */
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
import java.io.FileInputStream;
import sun.audio.*;
import java.io.*;

public class BlackJack {

  //this is the arraylist for the dealer's hand.
  ArrayList<Card> dealerHand; 
  //this is the arraylist for the player's hand.
  ArrayList<Card> playerHand; 

  //program checks if the is card facedown or faceup.
  public boolean cardFaceDown;
  //program checks if dealer won the round.
  public boolean dealerWon;
  //program checks if the round is over.
  public volatile boolean roundOver;

  //create a JFrame.
  JFrame mainFrame; 
  Deck deck;
  //background images, buttons, and the overall atmosphere.
  Board atmosphere;
  //cards printing out.
  Board cardElements;

  //j buttons
  JButton hitBTN;
  JButton standBTN;
  JButton doubleBTN;
  JButton exitBTN;
 // JButton hintBTN;

  ///constructor, JFrame as a parameter.
  public BlackJack(JFrame f) {
    deck = new Deck();
    deck.shuffleDeck();
    dealerHand = new ArrayList<>();
    playerHand = new ArrayList<>();
    atmosphere = new Board(dealerHand, playerHand);
    mainFrame = f;
    cardFaceDown = true;
    dealerWon = true;
    roundOver = false;
  }

  //background for game.
  public void createGame() {

    System.out.println("GAME START");
    //title
    mainFrame.setTitle("BLACKJACK!");
    mainFrame.setSize(1130, 665);
    //centers JFrame relitive to the screen
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //deny resizing of window
    mainFrame.setResizable(false);

    //initialize buttons and design them, set bounds, set fonts
    hitBTN = new JButton("HIT ME"); 
    hitBTN.setBounds(360, 550, 100, 50); 
    hitBTN.setFont(new Font("Times New Roman", Font.BOLD, 16)); 
    standBTN = new JButton("STAND");
    standBTN.setBounds(650, 550, 100, 50);
    standBTN.setFont(new Font("Times New Roman", Font.BOLD, 16));
    doubleBTN = new JButton("DOUBLE DOWN");
    doubleBTN.setBounds(470, 550, 170, 50);
    doubleBTN.setFont(new Font("Times New Roman", Font.BOLD, 16));
    exitBTN = new JButton("EXIT CASINO");
    exitBTN.setBounds(910, 100, 190, 50);
    exitBTN.setFont(new Font("Times New Roman", Font.BOLD, 16));
 // hintBTN = new JButton("Need a Hint?");
 // hintBTN.setBounds(930, 400, 190, 50);
 // hintBTN.setFont(new Font("Times New Roman", Font.BOLD, 16));

    //add the buttons to the JFrame
    mainFrame.add(hitBTN);
    mainFrame.add(standBTN);
    mainFrame.add(doubleBTN);
    mainFrame.add(exitBTN);
 // mainFrame.add(hintBTN);

    //exit button action listener
    exitBTN.addActionListener((ActionEvent e) -> {
        JOptionPane.showMessageDialog(mainFrame, "You're leaving the casino with " +  Tester.currentBalance + ". Have a great day!");
        System.exit(0); 
    });
    


    //initialize the board
    atmosphere = new Board(dealerHand, playerHand); 
    atmosphere.setBounds(0, 0, 1130, 665);  
    mainFrame.add(atmosphere); 
    mainFrame.setVisible(true); 
}

  //starts the game: cards are drawn and printed out.
  //adds card to dealer and player hands and removes cards from deck
    public void startGame() {
        for(int i = 0; i<2; i++) { 
            dealerHand.add(deck.getCard(i));
        }
        for(int i = 2; i<4; i++) { 
            playerHand.add(deck.getCard(i));
        }
        for (int i = 0; i < 4; i++) { 
            deck.removeCard(0);
        }

        cardElements = new Board(dealerHand, playerHand); 
        cardElements.setBounds(0, 0, 1130, 665); 
        mainFrame.add(cardElements); 
        mainFrame.setVisible(true); 

        //check hands for bust or Blackjack
        checkHand(dealerHand); 
        checkHand(playerHand);

        //hitbutton action listener
        hitBTN.addActionListener((ActionEvent e) -> {
            playCardDraw();
            addCard(playerHand);
            checkHand(playerHand);
            //dealer will stand at 17, if both parties have less than 17 after the player ends, then we will draw a card for the dealer and check again
    /*        if (getSumOfHand(playerHand)<17 && getSumOfHand(dealerHand)<17) {                                                 
                addCard(dealerHand);
                checkHand(dealerHand);
            } */
        });
        
        //adds one card to the player's hand, double down will double the bet
        doubleBTN.addActionListener((ActionEvent e) -> {
            //play card drawing sound effect
            playCardDraw(); 
            addCard(playerHand);
            checkHand(playerHand);
 //     currentBet = currentBet*2;

        while (getSumOfHand(dealerHand)<17) {
            addCard(dealerHand);
            checkHand(dealerHand);
        }
        if ((getSumOfHand(dealerHand)<21) && getSumOfHand(playerHand)<21) {
            if(getSumOfHand(playerHand) > getSumOfHand(dealerHand)) {
                cardFaceDown = false;
                dealerWon = false;
                JOptionPane.showMessageDialog(mainFrame, "PLAYER HAS THE BETTER HAND, PLAYER WINS!");
                rest();
                roundOver = true;
            }
            else {
                cardFaceDown = false;
                JOptionPane.showMessageDialog(mainFrame, "DEALER HAS THE BETTER HAND, DEALER WINS!");
                rest();
                roundOver = true;
            }
        }
        });

    //Stand button action listener
    standBTN.addActionListener((ActionEvent e) -> {
        //dealer draws until hitting at least 17
        while (getSumOfHand(dealerHand)<17) {
            addCard(dealerHand);
            checkHand(dealerHand);
        }
        if ((getSumOfHand(dealerHand)<21) && getSumOfHand(playerHand)<21) {
            if(getSumOfHand(playerHand) > getSumOfHand(dealerHand)) {
                cardFaceDown = false;
                dealerWon = false;
                JOptionPane.showMessageDialog(mainFrame, "PLAYER HAS THE BETTER HAND, PLAYER WINS!");
                rest();
                roundOver = true;
            }
            else {
                cardFaceDown = false;
                JOptionPane.showMessageDialog(mainFrame, "DEALER HAS THE BETTER HAND, DEALER WINS!");
                rest();
                roundOver = true;
            }
        }
        });
  }

   //checks the hand for a blackjack or bust.
  public void checkHand (ArrayList<Card> hand) {
    //checks if the parameter is player's hand.
    if (hand.equals(playerHand)) { 
      //if it is 21, player has blackjack and the game is over.
      if(getSumOfHand(hand) == 21) { 
        cardFaceDown = false;
        dealerWon = false; 
        JOptionPane.showMessageDialog(mainFrame, "BLACKJACK! PLAYER HAS WON!"); 
        rest();
        roundOver = true;
      }
       //if it is bigger than 21, then the player hand has busted, dealer has won.
      else if (getSumOfHand(hand) > 21) { 
        cardFaceDown = false; JOptionPane.showMessageDialog(mainFrame, "BUSTED! DEALER HAS WON!");
        rest();
        roundOver = true;
      }
    }
    //checks if it is dealer's hand.
    else { 
      if(getSumOfHand(hand) == 21) { 
        cardFaceDown = false;
        JOptionPane.showMessageDialog(mainFrame, "DEALER BLACKJACK! DEALER HAS WON!");
        rest();
        roundOver = true;
      }
      else if (getSumOfHand(hand) > 21) {
        cardFaceDown = false;
        dealerWon = false;
        JOptionPane.showMessageDialog(mainFrame, "DEALER BUSTED! PLAYER HAS WON!");
        rest();
        roundOver = true;
      }
    }
  }

  public void addCard(ArrayList<Card> hand) {
    hand.add(deck.getCard(0)); 
    deck.removeCard(0);
    cardFaceDown = true;
  }

  // checks if the hand has ace, aces are wild
  public boolean hasAceInHand(ArrayList<Card> hand) {
    for (int i = 0; i < hand.size(); i++) {
      if(hand.get(i).getValue() == 11) {
        return true;
      }
    }
    return false; 
  }

  //finds the total aces found in the hand. decides ace's value as 1 or 11.
  public int aceCountInHand(ArrayList<Card> hand) {
    int aceCount = 0; 
    for (int i = 0; i < hand.size(); i++)  { 
      if(hand.get(i).getValue() == 11) { 
        aceCount++; 
      }
    }
    return aceCount; 
  }

  //gives the total value of the hand where the ace is high
  public int getSumWithHighAce(ArrayList<Card> hand) {
    int handSum = 0; 
    for (int i = 0; i < hand.size(); i++) { 
      handSum = handSum + hand.get(i).getValue(); 
    }
    return handSum; 
  }

  //sum of the hand
  public int getSumOfHand (ArrayList<Card> hand) {
    if(hasAceInHand(hand)) { 
      if(getSumWithHighAce(hand) <= 21) {
        return getSumWithHighAce(hand); 
      }
      else{
        for (int i = 0; i < aceCountInHand(hand); i++) { 
          int sumOfHand = getSumWithHighAce(hand)-(i+1)*10; 
          if(sumOfHand <= 21) {
            return sumOfHand; 
          }
        }
      }
    }
    
    //if no ace in hand, go through the hand and find value
    else { 
      int sumOfHand = 0;
      for (int i = 0; i < hand.size(); i++) {
        sumOfHand = sumOfHand + hand.get(i).getValue();
      }
      return sumOfHand;
    }
    return 22; 
  }

  //sleeps the program
  public static void rest() {
    try {
      Thread.sleep(500);
    }
    catch (InterruptedException e) {}
  }

  //plays sound effect
  public static void playCardDraw() { 
    try {
      //read in wav file
      InputStream in = new FileInputStream("sounds/cardDraw.wav");
      AudioStream audio = new AudioStream(in); 
      AudioPlayer.player.start(audio);
    }
    catch(IOException e) {}
  }
}