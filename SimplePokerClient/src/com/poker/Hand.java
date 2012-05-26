package com.poker;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Hand {

	private SortedSet<Card> cards = new TreeSet<Card>(new CardComparator());
	
	public void addCard(Card card){
		cards.add(card);
	}
	
	public void showHand(){
		Iterator<Card> handIter = cards.iterator();
		Card card = null;
	    while ( handIter.hasNext() ){
	    	card = handIter.next();
	    	card.showCard();
	    }
	}
	
	

}
