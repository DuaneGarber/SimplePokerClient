package com.poker;

public enum HandOrder {
    
	ROYAL_FLUSH() {
	},
	STRAIGHT_FLUSH() {

	},
	FOUR_OF_A_KIND() {
	},
	FULL_HOUSE() {
	},
	FLUSH() {
	},
	STRAIGHT() {
	},
	THREE_OF_A_KIND() {
	},
	TWO_PAIR() {
	},
	PAIR() {
	},
	HIGH_CARD() {
	};
	
	private Value highCard = null;
	private Value secondHighCard = null;
	
    public Value getHighCard() {
        return this.highCard;
    }
    public void setHighCard(Value value) {
        this.highCard = value;
    }

    public Value getSecondHighCard() {
        return this.secondHighCard;
    }
    public void setSecondHighCard(Value value) {
        this.secondHighCard = value;
    }
	

}
