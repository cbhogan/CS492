/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package blackjack;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Brandon Rias and Collin Hogan
 */
public class DeckTest {
    
    public DeckTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of shuffleDeck method, of class Deck.
     */
    @Test
    public void testShuffleDeck() {
        System.out.println("shuffleDeck");
        Deck instance = new Deck();
        instance.shuffleDeck();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCard method, of class Deck.
     */
    @Test
    public void testGetCard() {
        System.out.println("getCard");
        int i = 0;
        Deck instance = new Deck();
        Card expResult = null;
        Card result = instance.getCard(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCard method, of class Deck.
     */
    @Test
    public void testRemoveCard() {
        System.out.println("removeCard");
        int i = 0;
        Deck instance = new Deck();
        Card expResult = null;
        Card result = instance.removeCard(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
