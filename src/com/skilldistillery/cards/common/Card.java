package com.skilldistillery.cards.common;

public class Card {
	private Suit suit;
	private Rank rank;
	
	public Card(){
	}
	
	public Card(Suit s, Rank r) {
		suit = s;
		rank = r;
	}
	
	public String toString() {
		if(this.rank.equals(Rank.ACE1)) {
			return Rank.ACE + " OF " + this.suit;
		}
		String s = this.rank + " OF " + this.suit;
		return s;
	}

	public int getValue() {
		return rank.getValue();
	}
	
	public Suit getSuit() {
		return suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	
}
