package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Card;

public class CompPlayer extends Player {

	public CompPlayer(BlackjackHand hand, String name) {
		super(hand, name);
	}

	public void hitOrStay(Dealer d, int stayAmount) {
		while (getHand().getHandValue() < BlackjackHand.DEALER_STAYS - stayAmount) {
			System.out.println();
			System.out.println(this.getName() + " HITS");
			Card newCard = d.getDeck().dealCard();
			System.out.println(this.getName() + " IS DEALT A " + newCard);
			getHand().addCard(newCard);
			if (getHand().getHandValue() > BlackjackHand.BLACKJACK_MAX_VALUE) {
				bustWithAceLowerAceValue();
				if (this.getHand().getHandValue() > BlackjackHand.BLACKJACK_MAX_VALUE) {
					System.out.println(this.getName() + "'S HAND: " + this.getHand());
					System.out.println(this.getName() + "'S HAND VALUE: " + getHand().getHandValue());
					System.out.println(this.getName() + " HAS BUSTED WITH: " + this.getHand().getHandValue() + " **");
					System.out.println();
					return;
				}
			}
			System.out.println(this.getName() + "'S HAND: " + this.getHand());
			System.out.println(this.getName() + "'S HAND VALUE: " + getHand().getHandValue());
		}
		System.out.println(this.getName() + " STAYS WITH: " + getHand().getHandValue() + " **");
		System.out.println();
	}//end hitOrStay
	
	@Override
	public String getName() {
		return super.getName();
	}
}
