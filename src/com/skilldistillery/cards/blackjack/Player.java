package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.common.Card;

public class Player extends AbstractBlackjackPlayer {

	public Player(BlackjackHand hand, String name) {
		super(hand, name);
	}

	public void hitOrStay(Dealer d, Scanner kb) {
		String input = "";
		while (this.getHand().getHandValue() <= BlackjackHand.BLACKJACK_MAX_VALUE) {
			do {
				printHandValue();
				System.out.println();
				System.out.print(
						"WOULD YOU LIKE TO " + HitOrStay.HIT.getName() + " OR " + HitOrStay.STAY.getName() + "? ");
				input = kb.next();
				input = input.toUpperCase();
				if (!input.equals(HitOrStay.HIT.getName()) && !input.equals(HitOrStay.STAY.getName())) {
					System.out
							.println("YOU DIDN'T ENTER " + HitOrStay.HIT.getName() + " OR " + HitOrStay.STAY.getName());
				}
			} while (!input.equals(HitOrStay.HIT.getName()) && !input.equals(HitOrStay.STAY.getName()));
			kb.nextLine();
			switch (input) {
			case "HIT":
				hit(d);
				break;
			case "STAY":
				stay();
				return;
			}
		}
	}

	private void printHandValue() {
		System.out.println();
		System.out.println(this.getName() + "'S HAND IS CURRENTLY: " + this.getHand());
		System.out.println(this.getName() + "'S HAND VALUE: " + this.getHand().getHandValue());
	}

	private void hit(Dealer d) {
		System.out.println();
		System.out.println(this.getName() + " CHOSE TO " + HitOrStay.HIT.getName());
		Card newCard = d.getDeck().dealCard();
		System.out.println(this.getName() + " DREW A [" + newCard + "]");
		this.getHand().addCard(newCard);
		if (this.getHand().getHandValue() > BlackjackHand.BLACKJACK_MAX_VALUE) {
			bustWithAceLowerAceValue();
			if (this.getHand().getHandValue() > BlackjackHand.BLACKJACK_MAX_VALUE) {
				System.out.println(this.getName() + " HAS BUSTED WITH: " + this.getHand().getHandValue() + " **");
				return;
			}
		}
	}// end hit

	private void stay() {
		System.out.println();
		System.out.println(this.getName() + " CHOSE TO " + HitOrStay.STAY.getName() + " WITH: "
				+ this.getHand().getHandValue() + " **");
		System.out.println();
	}

	public void setName(String name) {
		this.setName(name);
	}
}
