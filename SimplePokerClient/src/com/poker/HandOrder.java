package com.poker;

public enum HandOrder {
	ROYAL_FLUSH() {
		Card highCard = null;
		public Card setHighCard() {

			return null;
		}
	},
	STRAIGHT_FLUSH() {
		public Card setHighCard() {

			return null;
		}
	},
	FOUR_OF_A_KIND() {
		public Card setHighCard() {

			return null;
		}
	},
	FULL_HOUSE() {
		public Card setHighCard() {

			return null;
		}
	},
	FLUSH() {
		public Card setHighCard() {

			return null;
		}
	},
	STRAIGHT() {
		public Card setHighCard() {

			return null;
		}
	},
	THREE_OF_A_KIND() {
		public Card setHighCard() {

			return null;
		}
	},
	TWO_PAIR() {
		public Card setHighCard() {

			return null;
		}
	},
	PAIR() {
		public Card setHighCard() {

			return null;
		}
	},
	HIGH_CARD() {
		private Card highCard = null;
		public void setHighCard(Card card) {
			highCard = card;
		}
	}

}
