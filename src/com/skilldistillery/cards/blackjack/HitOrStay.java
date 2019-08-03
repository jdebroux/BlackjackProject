package com.skilldistillery.cards.blackjack;

public enum HitOrStay {
	HIT("hit"), STAY("stay");
	
	private final String name;

	private HitOrStay(String n) {
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
