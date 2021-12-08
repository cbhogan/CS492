/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package blackjack;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Brandon Rias and Collin Hogan
 */
public class BlackJackTest {
    
    public BlackJackTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of createGame method, of class BlackJack.
     */
    @Test
    public void testCreateGame() {
        System.out.println("createGame");
        BlackJack instance = null;
        instance.createGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startGame method, of class BlackJack.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        BlackJack instance = null;
        instance.startGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkHand method, of class BlackJack.
     */
    @Test
    public void testCheckHand() {
        System.out.println("checkHand");
        ArrayList<Card> hand = null;
        BlackJack instance = null;
        instance.checkHand(hand);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCard method, of class BlackJack.
     */
    @Test
    public void testAddCard() {
        System.out.println("addCard");
        ArrayList<Card> hand = null;
        BlackJack instance = null;
        instance.addCard(hand);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasAceInHand method, of class BlackJack.
     */
    @Test
    public void testHasAceInHand() {
        System.out.println("hasAceInHand");
        ArrayList<Card> hand = null;
        BlackJack instance = null;
        boolean expResult = false;
        boolean result = instance.hasAceInHand(hand);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aceCountInHand method, of class BlackJack.
     */
    @Test
    public void testAceCountInHand() {
        System.out.println("aceCountInHand");
        ArrayList<Card> hand = null;
        BlackJack instance = null;
        int expResult = 0;
        int result = instance.aceCountInHand(hand);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSumWithHighAce method, of class BlackJack.
     */
    @Test
    public void testGetSumWithHighAce() {
        System.out.println("getSumWithHighAce");
        ArrayList<Card> hand = null;
        BlackJack instance = null;
        int expResult = 0;
        int result = instance.getSumWithHighAce(hand);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSumOfHand method, of class BlackJack.
     */
    @Test
    public void testGetSumOfHand() {
        System.out.println("getSumOfHand");
        ArrayList<Card> hand = null;
        BlackJack instance = null;
        int expResult = 0;
        int result = instance.getSumOfHand(hand);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rest method, of class BlackJack.
     */
    @Test
    public void testRest() {
        System.out.println("rest");
        BlackJack.rest();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playCardDraw method, of class BlackJack.
     */
    @Test
    public void testPlayCardDraw() {
        System.out.println("playCardDraw");
        BlackJack.playCardDraw();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
