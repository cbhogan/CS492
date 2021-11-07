package blackjack;

/**
 * WIU CS492
 * @author Brandon Rias and Collin Hogan
 */
import javax.swing.JFrame;
/*
 * frame shows when the user opens the game
 * frame for blackjack game
 * Player and dealer scores start at 0
 * chip balance starts at 1000
 * initializes 'BlackJack' to control, start, and calculate game
 * Boolean to check if this is the first run 
 * enum represents state of game (menu or game)
 */
public class Tester {
  public static JFrame menuFrame = new JFrame();
  public static JFrame gameFrame = new JFrame();
  private static int playerScore = 0; 
  private static int dealerScore = 0;
  public static int currentBalance = 1000; 
  public static BlackJack newGame = new BlackJack(gameFrame); 
  private static boolean isFirstTime = true;
  public static enum menuSTATE { 
    MENU, GAME
  };

  public static menuSTATE currentState = menuSTATE.MENU;

  public static void main(String[] args) throws InterruptedException {
    if(currentState == menuSTATE.MENU) {
      openMenu(); 
    }
  }

  //positions JFrame and titles it
  public static void openMenu() { 
    menuFrame.setTitle("BLACKJACK!");
    menuFrame.setSize(1130, 665);
    menuFrame.setLocationRelativeTo(null);
    menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    menuFrame.setResizable(false); 
    BoardRunner beginningComponent = new BoardRunner(); 
    menuFrame.add(beginningComponent); 
    menuFrame.setVisible(true); 
  }
  
  public static Thread refreshGame = new Thread () { 
    public void run () {
      while(true){
        newGame.atmosphere.refresh(currentBalance, playerScore, dealerScore-1, newGame.cardFaceDown);
      }
    }
  };

  //check for game over situation
  public static Thread checkGame = new Thread () { 
    public void run () {
      while(true) {
        if (isFirstTime||newGame.roundOver) {
          System.out.println("Lets refresh the game!");
          
          //if dealer wins game
          if (newGame.dealerWon) {
            dealerScore++;
            
            //subtract the bet from the current balance.
            currentBalance-= Board.currentBet;
          }
          
          //player wins game
          else {
            playerScore++;
            currentBalance+= Board.currentBet*2;
          }
          gameFrame.getContentPane().removeAll();
          newGame = new BlackJack(gameFrame);
          newGame.createGame(); 
          isFirstTime = false; 
        }
      }
    }
  };
}
