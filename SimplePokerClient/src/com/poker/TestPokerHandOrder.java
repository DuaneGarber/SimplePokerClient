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

        // Test Royal Flush
//        testRoyalFlush();

//        testStraightFlush();

        testFourOfAKind();

        testFullHouse();

        testFlush();

        testStraight();
        
        testThreeOfAKind();
        
        testTwoPair();
        
        testPair();

    }

    /**
     * 
     */
    private void testPair() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.HEARTS, Value.THREE));
        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
        hand.addCard(new Card(Suit.DIAMONDS, Value.FIVE));
        assertEquals(HandOrder.PAIR, hand.evaluateHand());
        
    }

    /**
     * 
     */
    private void testTwoPair() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Value.THREE));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.HEARTS, Value.FOUR));
        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
        hand.addCard(new Card(Suit.DIAMONDS, Value.FIVE));
        assertEquals(HandOrder.TWO_PAIR, hand.evaluateHand());
        
    }

    /**
     * 
     */
    private void testThreeOfAKind() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.SPADES, Value.THREE));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.HEARTS, Value.THREE));
        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
        hand.addCard(new Card(Suit.DIAMONDS, Value.FIVE));
        assertEquals(HandOrder.THREE_OF_A_KIND, hand.evaluateHand());
        
        
        
//        hand.addCard(new Card(Suit.SPADES, Value.THREE));
//        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
//        hand.addCard(new Card(Suit.HEARTS, Value.THREE));
//        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
//        hand.addCard(new Card(Suit.DIAMONDS, Value.FOUR));
//        hand.addCard(new Card(Suit.CLUBS, Value.FOUR));
//        HandOrder order = hand.evaluateHand();
//        assertEquals(Value.FOUR, order.getHighCard());
        
    }

    /**
     * 
     */
    private void testStraight() {
        // Simple Ace High Case
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.HEARTS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.QUEEN));
        hand.addCard(new Card(Suit.SPADES, Value.JACK));
        hand.addCard(new Card(Suit.DIAMONDS, Value.TEN));
        assertEquals(HandOrder.STRAIGHT, hand.evaluateHand());

        // Ace Low case
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.HEARTS, Value.TWO));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
        hand.addCard(new Card(Suit.DIAMONDS, Value.FIVE));
        assertEquals(HandOrder.STRAIGHT, hand.evaluateHand());

        // Simple case
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.SIX));
        hand.addCard(new Card(Suit.HEARTS, Value.SEVEN));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
        hand.addCard(new Card(Suit.DIAMONDS, Value.FIVE));
        assertEquals(HandOrder.STRAIGHT, hand.evaluateHand());

        // Pair
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.SIX));
        hand.addCard(new Card(Suit.HEARTS, Value.SEVEN));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.HEARTS, Value.THREE));
        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
        hand.addCard(new Card(Suit.DIAMONDS, Value.FIVE));
        assertEquals(HandOrder.STRAIGHT, hand.evaluateHand());

        // two Pair
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.SIX));
        hand.addCard(new Card(Suit.HEARTS, Value.SEVEN));
        hand.addCard(new Card(Suit.SPADES, Value.SEVEN));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.HEARTS, Value.THREE));
        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
        hand.addCard(new Card(Suit.DIAMONDS, Value.FIVE));
        assertEquals(HandOrder.STRAIGHT, hand.evaluateHand());

        // 3 of a kind
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.SIX));
        hand.addCard(new Card(Suit.HEARTS, Value.SEVEN));
        hand.addCard(new Card(Suit.SPADES, Value.THREE));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.HEARTS, Value.THREE));
        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
        hand.addCard(new Card(Suit.DIAMONDS, Value.FIVE));
        assertEquals(HandOrder.STRAIGHT, hand.evaluateHand());
    }

    /**
     * 
     */
    private void testFlush() {
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.CLUBS, Value.FOUR));
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        assertEquals(HandOrder.FLUSH, hand.evaluateHand());

        // Pair
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.CLUBS, Value.FOUR));
        hand.addCard(new Card(Suit.HEARTS, Value.FOUR));
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        assertEquals(HandOrder.FLUSH, hand.evaluateHand());

        // 3 of a kind
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.SPADES, Value.FOUR));
        hand.addCard(new Card(Suit.CLUBS, Value.FOUR));
        hand.addCard(new Card(Suit.HEARTS, Value.FOUR));
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Value.QUEEN));
        assertEquals(HandOrder.FLUSH, hand.evaluateHand());

    }

    /**
     * 
     */
    private void testFourOfAKind() {
        // Simple
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.NINE));
        hand.addCard(new Card(Suit.DIAMONDS, Value.NINE));
        hand.addCard(new Card(Suit.HEARTS, Value.NINE));
        hand.addCard(new Card(Suit.SPADES, Value.NINE));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        assertEquals(HandOrder.FOUR_OF_A_KIND, hand.evaluateHand());

        // Three of a kind and 4 of a kind
        hand = new Hand();
        hand.addCard(new Card(Suit.HEARTS, Value.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.NINE));
        hand.addCard(new Card(Suit.DIAMONDS, Value.NINE));
        hand.addCard(new Card(Suit.HEARTS, Value.NINE));
        hand.addCard(new Card(Suit.SPADES, Value.NINE));
        assertEquals(HandOrder.FOUR_OF_A_KIND, hand.evaluateHand());
    }

    /**
     * 
     */
    private void testFullHouse() {
        // Simple
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.NINE));
        hand.addCard(new Card(Suit.DIAMONDS, Value.NINE));
        hand.addCard(new Card(Suit.HEARTS, Value.NINE));
        hand.addCard(new Card(Suit.HEARTS, Value.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        assertEquals(HandOrder.FULL_HOUSE, hand.evaluateHand());

        // Pair first, 3 of a kind late
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.NINE));
        hand.addCard(new Card(Suit.DIAMONDS, Value.NINE));
        hand.addCard(new Card(Suit.SPADES, Value.FIVE));
        hand.addCard(new Card(Suit.HEARTS, Value.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        assertEquals(HandOrder.FULL_HOUSE, hand.evaluateHand());

        // 2 Pair first, 3 of a kind late
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.NINE));
        hand.addCard(new Card(Suit.DIAMONDS, Value.NINE));
        hand.addCard(new Card(Suit.SPADES, Value.FIVE));
        hand.addCard(new Card(Suit.HEARTS, Value.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        assertEquals(HandOrder.FULL_HOUSE, hand.evaluateHand());

        // Two 3 of a kinds
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Value.KING));
        hand.addCard(new Card(Suit.SPADES, Value.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Value.NINE));
        hand.addCard(new Card(Suit.SPADES, Value.FIVE));
        hand.addCard(new Card(Suit.HEARTS, Value.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        assertEquals(HandOrder.FULL_HOUSE, hand.evaluateHand());
    }

    /**
     * 
     */
    private void testStraightFlush() {
        // Simple Case
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.QUEEN));
        hand.addCard(new Card(Suit.CLUBS, Value.JACK));
        hand.addCard(new Card(Suit.CLUBS, Value.TEN));
        hand.addCard(new Card(Suit.CLUBS, Value.NINE));
        assertEquals(HandOrder.STRAIGHT_FLUSH, hand.evaluateHand());

        // Another Simple
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.SIX));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Value.FOUR));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.CLUBS, Value.TWO));
        assertEquals(HandOrder.STRAIGHT_FLUSH, hand.evaluateHand());

        // Another pair
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.SIX));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Value.FOUR));
        hand.addCard(new Card(Suit.HEARTS, Value.FOUR));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.CLUBS, Value.TWO));
        assertEquals(HandOrder.STRAIGHT_FLUSH, hand.evaluateHand());// Another
                                                                    // pair

        // Two Pair
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.SIX));
        hand.addCard(new Card(Suit.DIAMONDS, Value.SIX));
        hand.addCard(new Card(Suit.CLUBS, Value.FIVE));
        hand.addCard(new Card(Suit.CLUBS, Value.FOUR));
        hand.addCard(new Card(Suit.HEARTS, Value.FOUR));
        hand.addCard(new Card(Suit.CLUBS, Value.THREE));
        hand.addCard(new Card(Suit.CLUBS, Value.TWO));
        assertEquals(HandOrder.STRAIGHT_FLUSH, hand.evaluateHand());
    }

    /**
     * 
     */
    private void testRoyalFlush() {

        // Simple Case
        Hand hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.QUEEN));
        hand.addCard(new Card(Suit.CLUBS, Value.JACK));
        hand.addCard(new Card(Suit.CLUBS, Value.TEN));
        assertEquals(HandOrder.ROYAL_FLUSH, hand.evaluateHand());

        // Pair in the way
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.QUEEN));
        hand.addCard(new Card(Suit.HEARTS, Value.QUEEN));
        hand.addCard(new Card(Suit.CLUBS, Value.JACK));
        hand.addCard(new Card(Suit.CLUBS, Value.TEN));
        assertEquals(HandOrder.ROYAL_FLUSH, hand.evaluateHand());

        // Two Pairs in the way
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.HEARTS, Value.KING));
        hand.addCard(new Card(Suit.CLUBS, Value.QUEEN));
        hand.addCard(new Card(Suit.HEARTS, Value.QUEEN));
        hand.addCard(new Card(Suit.CLUBS, Value.JACK));
        hand.addCard(new Card(Suit.CLUBS, Value.TEN));
        assertEquals(HandOrder.ROYAL_FLUSH, hand.evaluateHand());

        // Three of a kind
        hand = new Hand();
        hand.addCard(new Card(Suit.CLUBS, Value.ACE));
        hand.addCard(new Card(Suit.CLUBS, Value.KING));
        hand.addCard(new Card(Suit.DIAMONDS, Value.QUEEN));
        hand.addCard(new Card(Suit.CLUBS, Value.QUEEN));
        hand.addCard(new Card(Suit.HEARTS, Value.QUEEN));
        hand.addCard(new Card(Suit.CLUBS, Value.JACK));
        hand.addCard(new Card(Suit.CLUBS, Value.TEN));
        assertEquals(HandOrder.ROYAL_FLUSH, hand.evaluateHand());
    }

}
