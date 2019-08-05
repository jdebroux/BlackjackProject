package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.AbstractHand;
import com.skilldistillery.cards.common.Card;
 
public class BlackjackHand extends AbstractHand {
	public static final int BLACKJACK_MAX_VALUE = 21;
	public static final int DEALER_STAYS = 17;
	public static final int BJ_STARTING_HAND = 2;
	private int handValue;
	
	@Override
	public int getHandValue() {
		int handTotal = 0;
		for (Card card : super.getCards()) {
			handTotal += card.getValue();
		}
		this.handValue = handTotal;
		return handValue;
	}
}
