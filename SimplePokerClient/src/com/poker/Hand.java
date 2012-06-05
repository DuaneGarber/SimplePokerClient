package com.poker;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;

public class Hand {

	private static Logger LOG = Logger.getLogger(Hand.class);
	
	// SortedSet was chosen because
	// I wanted to ensure the cards were unique
	// I wanted to ensure the cards were in order (for straight calculation)
	private SortedSet<Card> cards = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> clubsBucket = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> diamondsBucket = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> heartsBucket = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> spadesBucket = new TreeSet<Card>(new CardComparator());
//	private HashMap<Value, Suit> straightBucket = new HashMap<Value, Suit>();
	private HandOrder handOrder = HandOrder.HIGH_CARD;
	 
	
	public SortedSet<Card> getCards(){
		return cards;
	}
	
	/**
	 * Add a card to the player's hand
	 * @param card
	 */
	public void addCard(Card card){
		
		cards.add(card);
		
		
		// Eliminate Loops at the cost of memory?
		switch (card.getSuit()){
		case CLUBS:
			clubsBucket.add(card);
			break;
		case DIAMONDS:
			diamondsBucket.add(card);
			break;
		case HEARTS:
			heartsBucket.add(card);
			break;
		case SPADES:
			spadesBucket.add(card);
			break;
		}
			
	}
	
	/**
	 * Simple display function
	 */
	public void showHand(){
		Iterator<Card> handIter = cards.iterator();
		Card card = null;
	    while ( handIter.hasNext() ){
	    	card = handIter.next();
	    	card.showCard();
	    }
	    LOG.info("Hand Value = " + evaluateHand().name());
	}
	
	/**
	 * This method is a mess, went through brute force, will re-evaluate later after more functionality built in
	 * @return
	 */
	public HandOrder evaluateHand(){
		LOG.trace("Begin -- evaluateHand");
		Iterator<Card> handIter = cards.iterator();
		Card card = null;
		
		
		
		if(clubsBucket.size() >= 5 || diamondsBucket.size() >= 5 || heartsBucket.size() >= 5 || spadesBucket.size() >= 5){
			LOG.debug("Found a Flush");
			setHandOrder(HandOrder.FLUSH);
		}
		
		int currentValue = 0;
		int previousValue = -1;
		int runCount = 0;
		boolean isPair = false;
		boolean isThreeOfAKind = false;
		
		
		int firstPairValue = -1;
		int firstThreeOfAKindValue = -1;
		
	    while ( handIter.hasNext() ){
	    	card = handIter.next();
	    	currentValue = card.getValue().ordinal();
	    	if(previousValue == -1){
	    		previousValue = currentValue;
	    		continue;
	    	}
	    	if(previousValue == currentValue){
	    		if(isPair){
	    			//Ok so we know its atleast a 3 of a kind
	    			//Check for a previous Pair
	    			if(firstPairValue != currentValue){
	    				setHandOrder(HandOrder.FULL_HOUSE);
	    			}
    				if(isThreeOfAKind){
    					setHandOrder(HandOrder.FOUR_OF_A_KIND);
    				}
    				else{
	    				setHandOrder(HandOrder.THREE_OF_A_KIND);
	    				isThreeOfAKind = true;
	    				//Incase of 2 3 of a Kinds, we only care about the first one
	    				if(firstThreeOfAKindValue == -1){
	    					firstThreeOfAKindValue = currentValue;
	    					if(firstPairValue == currentValue){
	    						firstPairValue = -1;
	    					} else {
	    						setHandOrder(HandOrder.FULL_HOUSE);
	    					}
	    				}
	    			}
	    		} else {
	    			isPair = true;
	    			if(firstPairValue == -1){
	    				if(firstThreeOfAKindValue != -1){
	    					setHandOrder(HandOrder.FULL_HOUSE);
	    				} else {
		    	    		setHandOrder(HandOrder.PAIR);
	    				}
	    				firstPairValue = currentValue;
	    			} else {
	    				setHandOrder(HandOrder.TWO_PAIR);
	    			}
	    		}	
	    	} else {
	    		isPair = false;
	    		isThreeOfAKind = false;
	    		if((previousValue - 1) == currentValue ){
	    			runCount++;
	    			if(runCount >= 5){
	    				setHandOrder(HandOrder.STRAIGHT);
	    			}
	    		}
	    	}
	    	previousValue = currentValue;
	    }
	    LOG.trace("Ending -- evaluateHand");
	    return getHandOrder();
	    
	}
	
	/**
	 * This is basically a safety function that ensures that the higher HandValue will always remain
	 * @param newhandOrder the HandOrder to be evaluated
	 */
	private void setHandOrder(HandOrder newhandOrder) {
		if(getHandOrder().ordinal() > newhandOrder.ordinal()){
			handOrder = newhandOrder;
		}
	}
	
	private HandOrder getHandOrder(){
		return handOrder;
	}
	
	
	public void addHand(Hand hand){
		cards.addAll(hand.getCards());
	}
	

}
