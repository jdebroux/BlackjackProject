package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.*;

public class Dealer extends AbstractBlackjackPlayer {
	private Deck deck;

	public Dealer(BlackjackHand bJH) {
		super(bJH);
	}

	public Dealer(AbstractHand hand, Deck deck) {
		super(hand);
		this.deck = deck;
	}

	public void hitOrStay() {
		while (hand.getHandValue() < AbstractHand.DEALER_STAYS) {
			System.out.println("Dealer hits.");
			Card newCard = deck.dealCard();
			System.out.println("Dealer draws: " + newCard.toString());
			hand.addCard(newCard);
			System.out.print("Dealers hand: ");
			for (Card card : hand.getHand()) {
				System.out.print(card + ", ");
			}
			System.out.println("\nWorth: " + hand.getHandValue());
			if (hand.getHandValue() > AbstractHand.BLACKJACK_MAX_VALUE) {
				System.out.println("Dealer busted.  Player wins!");
				return;
			}
		}
		System.out.println("Dealer stays with: " + hand.getHandValue());

	}

	public Deck getDeck() {
		return deck;
	}
	
	public void dealerShuffle() {
		deck.shuffleDeck();
	}


}
