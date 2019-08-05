package com.skilldistillery.cards.blackjack;

public enum HitOrStay {
	HIT("HIT"), STAY("STAY");
	
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
