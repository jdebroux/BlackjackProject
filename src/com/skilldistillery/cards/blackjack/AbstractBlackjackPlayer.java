package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.AbstractHand;
import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Rank;

public abstract class AbstractBlackjackPlayer {
	private AbstractHand hand;
	private String name;

	public AbstractBlackjackPlayer(AbstractHand hand, String name) {
		this.hand = hand;
		this.name = name;
	}

	public AbstractHand getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void bustWithAceLowerAceValue() {
		for (Card card : this.getHand().getCards()) {
			if (card.getValue() == Rank.ACE.getValue()) {
				card.setRank(Rank.ACE1);
				break;
			}
		}
	}

}