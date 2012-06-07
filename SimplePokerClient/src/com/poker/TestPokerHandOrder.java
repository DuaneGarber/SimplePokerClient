/**
 * File: TestPokerHandOrder.java
 *
 * Author: Duane Garber
 */
package com.poker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Duane Garber
 *
 */
public class TestPokerHandOrder {

    /**
     * Test method for {@link com.poker.Hand#evaluateHand()}.
     */
    @Test
    public void testEvaluateHand() {
        //Test Straight
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.CLUBS, Value.TWO));
        hand.addCard(new Card(Suit.CLUBS, Value.QUEEN));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.CLUBS, Value.TEN));
        assertEquals(HandOrder.FLUSH, hand.evaluateHand());
        
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.NINE));
        hand.addCard(new Card(Suit.CLUBS, Value.EIGHT));
        hand.addCard(new Card(Suit.HEARTS, Value.SEVEN));
        hand.addCard(new Card(Suit.CLUBS, Value.SIX));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        assertEquals(HandOrder.STRAIGHT, hand.evaluateHand());
        
    }

}
