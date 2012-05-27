package com.poker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;

public class Hand {

	private static Logger LOG = Logger.getLogger(Hand.class);
	
	private SortedSet<Card> cards = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> clubsBucket = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> diamondsBucket = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> heartsBucket = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> spadesBucket = new TreeSet<Card>(new CardComparator());
	private HashMap<Value, Suit> straightBucket = new HashMap<Value, Suit>();
	 
	
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
		straightBucket.put(card.getValue(), card.getSuit());
//		straightBucket.
			
	}
	
	public void showHand(){
		Iterator<Card> handIter = cards.iterator();
		Card card = null;
	    while ( handIter.hasNext() ){
	    	card = handIter.next();
	    	card.showCard();
	    }
	    LOG.info("Hand Value = " + evaluateHand().name());
	}
	
	
	public HandOrder evaluateHand(){
		LOG.trace("Begin -- evaluateHand");
		Iterator<Card> handIter = cards.iterator();
		Card card = null;
//		boolean isStraight = false;
//		boolean isFlush = false;
		
		HandOrder handOrder = HandOrder.HIGH_CARD;
		
		if(clubsBucket.size() >= 5 || diamondsBucket.size() >= 5 || heartsBucket.size() >= 5 || spadesBucket.size() >= 5){
//			isFlush = true;
			LOG.debug("Found a Flush");
			handOrder = setHandOrder(handOrder, HandOrder.FLUSH);
		}
		
		int currentValue = 0;
		int previousValue = -1;
		int runCount = 0;
		boolean isPair = false;
		boolean isThreeOfAKind = false;
		
	    while ( handIter.hasNext() ){
	    	card = handIter.next();
	    	currentValue = card.getValue().ordinal();
	    	if(previousValue == -1){
	    		previousValue = currentValue;
	    		continue;
	    	}
	    	if(previousValue == currentValue){
	    		handOrder = setHandOrder(handOrder, HandOrder.PAIR);
	    		if(isPair){
	    			handOrder = setHandOrder(handOrder, HandOrder.THREE_OF_A_KIND);
	    			if(isThreeOfAKind){
	    				handOrder = setHandOrder(handOrder, HandOrder.FOUR_OF_A_KIND);
	    			}
	    		} else {
	    			isPair = true;
	    		}	
	    	} else {
	    		isPair = false;
	    		isThreeOfAKind = false;
	    		if((previousValue - 1) == currentValue ){
	    			runCount++;
	    			if(runCount >= 5){
	    				handOrder = setHandOrder(handOrder, HandOrder.STRAIGHT);
	    			}
	    		}
	    	}
	    	previousValue = currentValue;
	    }
	    LOG.trace("Ending -- evaluateHand");
	    return handOrder;
	    
	}
	
	private HandOrder setHandOrder(HandOrder hand, HandOrder order){
		if(hand.ordinal() < order.ordinal()){
			return hand;
		} else {
			return order;
		}
	}
	
	private boolean hasStraight(){
		
		int tempHandCount = cards.size();
		Card card = null;
		int currentValue = 0;
		int previousValue = -1;
		
		Iterator<Card> handIter = cards.iterator();
		
		while ( handIter.hasNext() ){
	    	card = handIter.next();
	    	tempHandCount--;
	    	currentValue = card.getValue().ordinal();
	    	if(previousValue == -1){
	    		continue;
	    	} else {
	    		//slots?
	    	}
	    	
	    	
	    	//cards.
	    	
	    }
		
		return false;
		
	}
	

}
