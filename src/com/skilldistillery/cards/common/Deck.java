package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> deck = new ArrayList<>();
	
	public Deck() {
		for (Suit cardSuit : Suit.values()) {
			for (Rank cardRank : Rank.values()) {
				if(cardRank.getValue() != Rank.ACE1.getValue()) {
					deck.add(new Card(cardSuit, cardRank));
				}
			}
		}
	}//end Deck
	
	public void addADeck(Deck d) {
		deck.addAll(d.getAllCards());
	}
	
	public int checkDeckSize() {
		return deck.size();
	}
	
	public Card getCard(int i) {
		return deck.get(i);
	}
	
	public List<Card> getAllCards(){
		return deck;
	}
	
	public Card dealCard() {
		return deck.remove(0);
	}
	
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}
}
