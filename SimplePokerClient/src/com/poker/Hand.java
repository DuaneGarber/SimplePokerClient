package com.poker;

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
	private SortedSet<Card> straightBucket = new TreeSet<Card>(new CardComparator());
	
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
	
	public void showHand(){
		Iterator<Card> handIter = cards.iterator();
		Card card = null;
	    while ( handIter.hasNext() ){
	    	card = handIter.next();
	    	card.showCard();
	    }
	}
	
	
	private HandOrder evaluateHand(){
		LOG.trace("Begin -- evaluateHand");
		Iterator<Card> handIter = cards.iterator();
		Card card = null;
		boolean isStraight = false;
		boolean isFlush = false;
		
		if(clubsBucket.size() >= 5 || diamondsBucket.size() >= 5 || heartsBucket.size() >= 5 || spadesBucket.size() >= 5){
			isFlush = true;
			LOG.debug("Found a Flush");
		}
		
	    while ( handIter.hasNext() ){
	    	card = handIter.next();
	    	
	    	
	    }
	    LOG.trace("Ending -- evaluateHand");
	    return null;
	    
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
