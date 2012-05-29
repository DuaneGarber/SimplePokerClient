package com.poker;

public enum Value {
	TWO() {
		@Override 
		public String displaySymbol(){
			return "2";
		}
	},
	THREE() {
		@Override 
		public String displaySymbol(){
			return "3";
		}
	},
	FOUR() {
		@Override 
		public String displaySymbol(){
			return "4";
		}
	},
	FIVE() {
		@Override 
		public String displaySymbol(){
			return "5";
		}
	},
	SIX() {
		@Override 
		public String displaySymbol(){
			return "6";
		}
	},
	SEVEN() {
		@Override 
		public String displaySymbol(){
			return "7";
		}
	},
	EIGHT() {
		@Override 
		public String displaySymbol(){
			return "8";
		}
	},
	NINE() {
		@Override 
		public String displaySymbol(){
			return "9";
		}
	},
	TEN() {
		@Override 
		public String displaySymbol(){
			return "10";
		}
	},
	JACK() {
		@Override 
		public String displaySymbol(){
			return "J";
		}
	},
	QUEEN() {
		@Override 
		public String displaySymbol(){
			return "Q";
		}
	},
	KING() {
		@Override 
		public String displaySymbol(){
			return "K";
		}
	},
	ACE() {
		@Override 
		public String displaySymbol(){
			return "A";
		}
	};
	public abstract String displaySymbol();
}
