package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.*;

public class Dealer extends AbstractBlackjackPlayer {
	private Deck deck;
	private String name;

	public Dealer(BlackjackHand bJH) {
		super(bJH);
	}

	public Dealer(AbstractHand hand, Deck deck, String name) {
		super(hand);
		this.deck = deck;
		this.name = name;
	}

	public void hitOrStay(Player player) {
		while (hand.getHandValue() < AbstractHand.DEALER_STAYS) {
			System.out.println("DEALER " + this.getName() + " HITS");
			Card newCard = deck.dealCard();
			System.out.println("DEALER " + this.getName() + " DRAWS: " + newCard.toString());
			hand.addCard(newCard);
			System.out.println();
			System.out.print("DEALER " + this.getName() + "'S HAND: " + this.getHand());
			System.out.println("\nDEALER " + this.getName() + " HAND VALUE: " + hand.getHandValue());
			if (hand.getHandValue() > AbstractHand.BLACKJACK_MAX_VALUE) {
				System.out.println("DEALER " + this.getName() + " BUSTS!");
				return;
			}
		}
		System.out.println("DEALER " + this.getName() + " STAYS WITH: " + hand.getHandValue());
		System.out.println();
	}

	public Deck getDeck() {
		return deck;
	}
	
	public void dealerShuffle() {
		deck.shuffleDeck();
	}

	public String getName() {
		return name;
	}
	
}
