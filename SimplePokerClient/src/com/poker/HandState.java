/**
 * File: HandState.java
 *
 * Author: Duane Garber
 */
package com.poker;

/**
 * @author Duane Garber
 * 
 */
public enum HandState {
	NONE, FLOP, TURN, RIVER;

	/**
	 * @return 
	 * 
	 */
	public HandState getNext() {
	    return values()[(ordinal()+1) % values().length];
//	    return this.ordinal() < HandState.values().length - 1
//	                     ? HandState.values()[this.ordinal() + 1]
//	                     : null;

	}

}
