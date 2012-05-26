package com.poker;

public class PokerRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Deck deck = new Deck();
		Hand hand = new Hand();
		deck.shuffle();
		for (int i = 0; i < 10; i++) {
			
			hand.addCard(deck.getNextCard());
		}
		hand.showHand();
	}

}
