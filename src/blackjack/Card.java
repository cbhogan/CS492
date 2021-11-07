package blackjack;

/*
 * WIU CS492
 * @author Brandon Rias and Collin Hogan
 */
//<code>Card</code> represents a playing card.
import java.io.File;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Card {

    //suit of the card (Clubs=[0], Diamonds=[1], Hearts=[3], or Spades=[4]) 
    private final int cardSuit;
    
    //rank of the card (Ace=[0], 2=[1], 3=[2], 4=[3], 5=[4], 6=[5], 7=[6], 8=[7], 9=[8], 10=[9], Jack=[10], Queen=[11], or King=[12])
    private final int cardRank;
    
    //value of the card in blackjack (from 1 to 11)
    private final int cardValue;
    
    //x position of the card image
    private int xPosition;
    
    //y position of the card image
    private int yPosition;

    //default constructor for a Card
    public Card() {
            cardSuit = 0;
            cardValue = 0;
            cardRank = 0;
        }
    
    //main attributes constructor
    public Card(int s, int r, int v) {
            cardSuit = s;
            cardValue = v;
            cardRank = r;
        }

    // returns the suit of the card.
    public int getSuit() { 
            return cardSuit;
        }

    //returns the rank of the card.
    public int getRank() { 
            return cardRank;
        }

    //returns the value of the card.
    public int getValue() {
            return cardValue;
        }

    /*
     *Draws a card (according to the parameters). May throw a IOException due to reading image files.
     *The first parameter is the g2, since we will draw images.
     *The second parameter tells the method if it is dealer's turn.
     *The third method checks if the card drawn will be face down or face up. 
     *The fourth parameter tells the method which card on the line will be drawn so that each line could be drawn next to each other in a horizontal line.
     */
    public void printCard(Graphics2D g2, boolean dealerTurn, boolean faceDown, int cardNumber) throws IOException {
        
            //reads the sprite sheet
            BufferedImage deckImg = ImageIO.read(new File("images/cardSpriteSheet.png"));
            int imgWidth = 950;
            int imgHeight = 392;
            
            //creates a two-dimensional array to store the individiual card pictures
            BufferedImage[][] cardPictures = new BufferedImage[4][13]; 
            BufferedImage backOfACard = ImageIO.read(new File("images/backsideOfACard.jpg")); 
            for (int c = 0; c < 4; c++) {
                for (int r = 0; r < 13; r++) {
                    cardPictures[c][r] = deckImg.getSubimage(r * imgWidth / 13, c * imgHeight / 4, imgWidth / 13, imgHeight / 4);  
                    //assigns the relative card pictures to the 2-D array.
                }
            }

            //if dealer's turn, card is printed at dealer's hand location
            if (dealerTurn) { 
                    yPosition = 75;
                }
            //if player's turn, card is printed at player's hand location
            else { 
                    yPosition = 400;
                }

            // draws cards side by side
            xPosition = 500 + 75 * cardNumber;

            //tells if printed card should be face down or face up.
            if (faceDown) { 
                g2.drawImage(backOfACard, xPosition, yPosition, null);
            } 
            else {
                g2.drawImage(cardPictures[cardSuit][cardRank], xPosition, yPosition, null);
            }
        }
}
