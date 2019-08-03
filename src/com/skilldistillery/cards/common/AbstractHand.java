package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHand {
	protected List<Card> cards;
	public static final int BLACKJACK_MAX_VALUE = 21;
	public static final int DEALER_STAYS = 17;
	
	public AbstractHand() {
	cards = new ArrayList<>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void clear() {
		cards.clear();
	}
	
	public abstract int getHandValue();
	
	public List<Card> getHand(){
		return cards;
	}

	@Override
	public String toString() {
		//TODO: fancy display of cards in hand?
		return cards.toString();
	}
	
	
	
}
