package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.AbstractHand;
 
public class BlackjackHand extends AbstractHand {
	private int handValue;
	
	@Override
	public int getHandValue() {
		return handValue;
	}
}
