package com.poker;

import java.util.Comparator;

import org.apache.log4j.Logger;

public class Card {

	private static Logger LOG = Logger.getLogger(Card.class);
	
	private Suit suit = null;	
	private Value value = null;
	
	Card(Suit suit, Value value){
		setSuit(suit);
		setValue(value);
	}
	
	Card() throws CardInitializationException{
		throw new CardInitializationException("Default Constructor disabled, Please use Card(Suit, Value)");
	}
	
	public Suit getSuit() {
		return suit;
	}
	private void setSuit(Suit suit) {
		this.suit = suit;
	}
	public Value getValue() {
		return value;
	}
	private void setValue(Value value) {
		this.value = value;
	}
	
	public void showCard() {
		LOG.info(getValue().name() + " of " + getSuit().name());
	}
	
	private class CardInitializationException extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2453753495204651166L;

		public CardInitializationException(String message) {
			super(message);
		}
		
	}
	
	
}

class CardComparator implements Comparator<Card>{

	@Override
	public int compare(Card card_a, Card card_b) {
		if(card_a.getValue().ordinal() < card_b.getValue().ordinal() ){
			return 1;
		}
		else if (card_a.getValue().ordinal() < card_b.getValue().ordinal()){
			return 0;
		}
		else{
			return -1;
		}	
	}
	
}