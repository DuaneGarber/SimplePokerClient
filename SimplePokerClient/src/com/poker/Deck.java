/**
 * The deck class, this will contain all of the functions pertinent to a physical deck of cards
 */
package com.poker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import org.apache.log4j.Logger;

public class Deck {
	private static Logger LOG = Logger.getLogger(Deck.class);
	
	
	//LinkedList was chosen because
	// Because I didn't want to have to track Card slots in an ArrayList
	// No need for the overhead of a set
	// Pop O(1)
	// Add O(1)
	private LinkedList<Card> playingCards = new LinkedList<Card>();
	private LinkedList<Card> usedCards = new LinkedList<Card>();
	//DeckState Enum?
	private boolean isShuffled = false;
	
	Deck(){
		initializeDeck();
	}
	
	/**
	 * Add the cards to the deck
	 */
	private void initializeDeck(){
		for (Suit suit : Suit.values()) {
			  for(Value value : Value.values()){
				  playingCards.add(new Card(suit, value));
			  }
		}
	}
	
	/**
	 * Shuffle the cards in the deck
	 * 
	 */
	public void shuffle(){
		LOG.trace("Begin -- shuffle");
		
		//If a single instance of a deck can be reused, add the cards back to the deck
		returnUsedCards();
		//Seed the random -- yes I know this isnt true random
		long seed = System.nanoTime();
		Collections.shuffle(this.playingCards, new Random(seed));
		
		//set the Shuffled state to true
		this.setShuffled(true);
		LOG.trace("Ending -- shuffle");
	}

	/**
	 * Add used cards back to the deck
	 */
	private void returnUsedCards() {
		while(!usedCards.isEmpty()){
			playingCards.add(usedCards.pop());	
		}
	}

	
	public boolean isShuffled() {
		return isShuffled;
	}

	public void setShuffled(boolean isShuffled) {
		this.isShuffled = isShuffled;
	}
	
	/**
	 * Retrieves the next card from the deck
	 * @return Card -- next card on the list
	 */
	public Card getNextCard(){
		LOG.trace("Begin -- getNextCard");
		Card card = null;
		//If the deck is shuffled, get the next card
		if(isShuffled()){
			card = playingCards.pop();
			usedCards.add(card);
		//Other wise shuffle the deck and get the next card
		} else {
			shuffle();
			return getNextCard();
		}
		
		LOG.trace("Ending -- getNextCard");
		return card;
	}
	
}
