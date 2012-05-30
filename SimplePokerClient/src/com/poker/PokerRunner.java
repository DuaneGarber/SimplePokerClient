package com.poker;

import org.apache.log4j.Logger;

public class PokerRunner {

	private static Logger LOG =
            Logger.getLogger(PokerRunner.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.debug("Starting Poker Command Line Runner");
		Deck deck = new Deck();
		Hand hand = new Hand();
		deck.shuffle();
		for (int i = 0; i < 10; i++) {
			
			hand.addCard(deck.getNextCard());
		}
		hand.showHand();
		LOG.debug("Execution Poker Command Line Complete");
	}
	
	public Deck init(){
		LOG.trace("Begin -- init");
		Deck deck = new Deck();
		
		deck.shuffle();
		
		
		LOG.trace("Ending -- init");
		return deck;
	}
	
	public Hand getHand(Deck deck){
		LOG.trace("Begin -- getHand");
		Hand hand = new Hand();
		for (int i = 0; i < 10; i++) {
			
			hand.addCard(deck.getNextCard());
		}
		LOG.trace("Ending -- getHand");
		return hand;
	}

}
