package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.AbstractHand;
import com.skilldistillery.cards.common.Card;
 
public class BlackjackHand extends AbstractHand {
	private int handValue;
	
	@Override
	public int getHandValue() {
		int handTotal = 0;
		for (Card card : cards) {
			handTotal += card.getValue();
		}
		this.handValue = handTotal;
		return handValue;
	}
}
