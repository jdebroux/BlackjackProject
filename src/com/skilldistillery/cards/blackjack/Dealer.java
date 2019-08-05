package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.*;

public class Dealer extends AbstractBlackjackPlayer {
	private Deck deck;

	public Dealer(AbstractHand hand,  String name, Deck deck) {
		super(hand, name);
		this.deck = deck;
	}

	public void hitOrStay(Player player) {
		System.out.println("\nDEALER " + this.getName() + " FLIPS HIS SECOND CARD");
		System.out.println("DEALER " + this.getName() + "'S HAND: " + this.getHand());
		System.out.println("DEALER " + this.getName() + "'S HAND VALUE: " + this.getHand().getHandValue());
		System.out.println();
		dealerSoftSeventeen();
		while (getHand().getHandValue() < BlackjackHand.DEALER_STAYS) {
			System.out.println("DEALER " + this.getName() + " HITS");
			Card newCard = deck.dealCard();
			System.out.println("DEALER " + this.getName() + " DRAWS: " + newCard.toString());
			getHand().addCard(newCard);
			if (getHand().getHandValue() > BlackjackHand.BLACKJACK_MAX_VALUE) {
				bustWithAceLowerAceValue();
				if (this.getHand().getHandValue() > BlackjackHand.BLACKJACK_MAX_VALUE) {
					System.out.println(this.getName() + " HAS BUSTED WITH: " + this.getHand().getHandValue() + " **");
					System.out.println();
					return;
				}
			}
			System.out.println();
			System.out.print("DEALER " + this.getName() + "'S HAND: " + this.getHand());
			System.out.println("\nDEALER " + this.getName() + " HAND VALUE: " + getHand().getHandValue());
		}
		System.out.println("DEALER " + this.getName() + " STAYS WITH: " + getHand().getHandValue() + " **");
		System.out.println();
	}

	public Deck getDeck() {
		return deck;
	}
	
	private void dealerSoftSeventeen() {
		if (this.getHand().getHandValue() == BlackjackHand.DEALER_STAYS) {
			for (Card card : this.getHand().getCards()) {
				if (card.getValue() == Rank.ACE.getValue()) {
					card.setRank(Rank.ACE1);
				}
			}
		}
	}//end dealerSoftSeventeen
	
	public void dealerShuffle() {
		deck.shuffleDeck();
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	
}
