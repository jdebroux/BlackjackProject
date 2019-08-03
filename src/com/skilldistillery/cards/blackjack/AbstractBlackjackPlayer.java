package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.AbstractHand;

public abstract class AbstractBlackjackPlayer {
	protected AbstractHand hand;

	public AbstractBlackjackPlayer(AbstractHand hand) {
		this.hand = hand;
	}

}
