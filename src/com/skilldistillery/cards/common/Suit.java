package com.skilldistillery.cards.common;

public enum Suit {
	HEARTS("HEARTS"), SPADES("SPADES"), CLUBS("CLUBS"), DIAMONDS("DIAMONDS");

	private final String name;

	private Suit(String n) {
		name = n;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getName() {
		return name;
	}
}
