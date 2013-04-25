package com.poker;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;

public class Hand {

	private static Logger LOG = Logger.getLogger(Hand.class);
	
	// SortedSet was chosen because
	// I wanted to ensure the cards were unique
	// I wanted to ensure the cards were in order (for straight calculation)
	private SortedSet<Card> cards = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> clubsBucket = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> diamondsBucket = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> heartsBucket = new TreeSet<Card>(new CardComparator());
	private SortedSet<Card> spadesBucket = new TreeSet<Card>(new CardComparator());
//	private HashMap<Value, Suit> straightBucket = new HashMap<Value, Suit>();
	private HandOrder handOrder = HandOrder.HIGH_CARD;
	 
	
	public SortedSet<Card> getCards(){
		return cards;
	}
	
	/**
	 * Add a card to the player's hand
	 * @param card
	 */
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
	
	/**
	 * Simple display function
	 */
	public void showHand(){
		Iterator<Card> handIter = cards.iterator();
		Card card = null;
	    while ( handIter.hasNext() ){
	    	card = handIter.next();
	    	card.showCard();
	    }
	    LOG.info("Hand Value = " + evaluateHand().name());
	}
	
	/**
	 * This method is a mess, went through brute force, will re-evaluate later after more functionality built in
	 * @return
	 */
	public HandOrder evaluateHand(){
		LOG.trace("Begin -- evaluateHand");
		Iterator<Card> handIter = cards.iterator();
		Card card = null;
		
		
		
		if(clubsBucket.size() >= 5 || diamondsBucket.size() >= 5 || heartsBucket.size() >= 5 || spadesBucket.size() >= 5){
			LOG.debug("Found a Flush");
			HandOrder order = HandOrder.FLUSH;
			if(clubsBucket.size() >= 5){
			    card = clubsBucket.first();
			} else if(diamondsBucket.size() >= 5){
			    card = diamondsBucket.first();
			} else if(heartsBucket.size() >= 5){
			    card = heartsBucket.first();
            } else if(spadesBucket.size() >= 5){
                card = spadesBucket.first();
            }
            order.setHighCard(card.getValue());
            
            setHandOrder(order);
		}
		
		int currentValue = 0;
		int previousValue = -1;
		int runCount = 1;
		boolean isPair = false;
		boolean isThreeOfAKind = false;
		boolean hasAce = false;
		
		
		int firstPairValue = -1;
		int firstThreeOfAKindValue = -1;
		Card firstStraightCard = null;
		
	    while ( handIter.hasNext() ){
	        HandOrder order = null;
	    	card = handIter.next();
	    	currentValue = card.getValue().ordinal();
	    	if(card.getValue() == Value.ACE){
	    	    hasAce = true;
	    	}
	    	if(previousValue == -1){
	    		previousValue = currentValue;
	    		continue;
	    	}
	    	if(previousValue == currentValue){
	    		if(isPair){
	    			//Ok so we know its at least a 3 of a kind
	    			//Check for a previous Pair
	    			if(firstPairValue != currentValue){
	    			    order = HandOrder.FULL_HOUSE;
	    				
	    				if(firstPairValue > currentValue){
	    				    order.setHighCard(Value.class.getEnumConstants()[firstPairValue]);
	    				    order.setSecondHighCard(Value.class.getEnumConstants()[currentValue]);
                        } else {
                            order.setHighCard(Value.class.getEnumConstants()[currentValue]);
                            if(firstPairValue != -1){
                                order.setSecondHighCard(Value.class.getEnumConstants()[firstPairValue]);
                            }
                        }
	    				setHandOrder(order);
	    			}
    				if(isThreeOfAKind){
    				    order = HandOrder.FOUR_OF_A_KIND;
    				    order.setHighCard(Value.class.getEnumConstants()[currentValue]);
    				    
    					setHandOrder(order);
    				}
    				else{
    				    order = HandOrder.THREE_OF_A_KIND;
    				    order.setHighCard(Value.class.getEnumConstants()[currentValue]);
	    				setHandOrder(order);
	    				isThreeOfAKind = true;
	    				//In case of 2 3 of a Kinds, we only care about the first one
	    				if(firstThreeOfAKindValue == -1){
	    					firstThreeOfAKindValue = currentValue;
	    					if(firstPairValue == currentValue){
	    						firstPairValue = -1;
	    					} else {
	    					    order = HandOrder.FULL_HOUSE;
	    					    if(firstPairValue > currentValue){
	                                order.setHighCard(Value.class.getEnumConstants()[firstPairValue]);
	                                order.setSecondHighCard(Value.class.getEnumConstants()[currentValue]);
	                            } else {
	                                order.setHighCard(Value.class.getEnumConstants()[currentValue]);
	                                if(firstPairValue != -1){
	                                    order.setSecondHighCard(Value.class.getEnumConstants()[firstPairValue]);
	                                }
	                            }
	    						setHandOrder(order);
	    					}
	    				}
	    				
	    			}
	    		} else {
	    			isPair = true;
	    			if(firstPairValue == -1){
	    				if(firstThreeOfAKindValue != -1){
	    				    order = HandOrder.FULL_HOUSE;
	    				    order.setHighCard(card.getValue());
	    					setHandOrder(order);
	    				} else {
	    				    order = HandOrder.PAIR;
	    				    order.setHighCard(card.getValue());
		    	    		setHandOrder(order);
	    				}
	    				firstPairValue = currentValue;
	    			} else {
	    			    order = HandOrder.TWO_PAIR;
	    			    if(firstPairValue > currentValue){
                            order.setHighCard(Value.class.getEnumConstants()[firstPairValue]);
                            order.setSecondHighCard(Value.class.getEnumConstants()[currentValue]);
                        } else {
                            order.setHighCard(Value.class.getEnumConstants()[currentValue]);
                            if(firstPairValue != -1){
                                order.setSecondHighCard(Value.class.getEnumConstants()[firstPairValue]);
                            }
                        }
	    				setHandOrder(order);
	    			}
	    		}	
	    	} else {
	    	    card.showCard();
	    		isPair = false;
	    		isThreeOfAKind = false;
	    		
	    		if((previousValue - 1) == currentValue ){
	    			if(runCount == 1){
	    			    firstStraightCard = card;
	    			}
	    		    runCount++;
    			
	    			if(runCount >= 5){
	    			    order = HandOrder.STRAIGHT;
	    			    order.setHighCard(firstStraightCard.getValue());
	    				setHandOrder(order);
	    		    //EDGE CASE FOR LOW Straight with Ace
	    			} else if(Value.TWO.ordinal() == previousValue  - 1 && runCount >= 4 && hasAce) {
	                    order = HandOrder.STRAIGHT;
	                    order.setHighCard(firstStraightCard.getValue());
	                    setHandOrder(order);
	                }
	    		}  else if(previousValue - 1  > currentValue) {
	    		    runCount=1;
	    		} 
	    	}
	    	previousValue = currentValue;
	    }
	    LOG.trace("Ending -- evaluateHand");
	    return getHandOrder();
	    
	}
	
	/**
	 * This is basically a safety function that ensures that the higher HandValue will always remain
	 * @param newhandOrder the HandOrder to be evaluated
	 */
	private void setHandOrder(HandOrder newhandOrder) {
	    LOG.debug("Current = " + getHandOrder().name());
	    LOG.debug("New = " + newhandOrder.name());
		if(getHandOrder().ordinal() > newhandOrder.ordinal()){
			handOrder = newhandOrder;
		} else if(getHandOrder().ordinal() == newhandOrder.ordinal()){
		    if(getHandOrder().getHighCard().ordinal() > newhandOrder.getHighCard().ordinal()){
		        handOrder = newhandOrder;
		    }
		}
	}
	
	private HandOrder getHandOrder(){
		return handOrder;
	}
	
	
	public void addHand(Hand hand){
		cards.addAll(hand.getCards());
	}
	

}
