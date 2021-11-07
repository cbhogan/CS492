package blackjack;

/**
 * WIU CS492
 * @author Brandon Rias and Collin Hogan
 */

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
    
  // Creates the arraylist that stores all 52 cards.
  private final ArrayList<Card> deck; 
  
  //initilizes array list and creates nested loop that goes through the 52 cards.
  public Deck() {   
    deck = new ArrayList<Card>();
    for (int i = 0; i < 4; i++) { 
      for (int j = 0; j < 13; j++) {  
          
        //if j = 0, that card was an ace and will be given the value of 11
        if (j == 0) { 
            
          // creates card with ith suit and jth rank with the value of 11(ace.)
          Card card = new Card(i, j, 11); 
          
          // add the card to our deck.
          deck.add(card);
        }
        
        //if j >= 10, the card will be either a Jack, Queen, or King.
        else if (j >= 10) { 
            
          // creates card with the ith suit and jth rank which has the value of 10(J, Q, K)
          Card card = new Card(i, j, 10);
          deck.add(card); 
        }
        
        //fo every other card
        else { 
          Card card = new Card(i, j, j+1);
          deck.add(card);
        } 
      }
    }
  }
  
  // huffles the deck
  public void shuffleDeck() { 
    Collections.shuffle(deck); 
  }
  
  //returns the ith card of the deck.
  public Card getCard(int i) { 
    return deck.get(i); 
  }
  
  // removes the ith card of the deck.
  public Card removeCard(int i) { 
    return deck.remove(i); 
  }
}