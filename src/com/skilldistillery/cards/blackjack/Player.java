package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.common.AbstractHand;
import com.skilldistillery.cards.common.Card;

public class Player extends AbstractBlackjackPlayer {
	private String name;
	
	public Player(BlackjackHand hand, String name) {
		super(hand);
		this.name = name;
	}

	public void hitOrStay(Dealer d, Scanner kb) {
		String input = "";
		while (this.getHand().getHandValue() <= AbstractHand.BLACKJACK_MAX_VALUE) {
			do {
				printHandValue();
				System.out.println();
				System.out.print(
						"WOULD YOU LIKE TO " + HitOrStay.HIT.getName() + " OR " + HitOrStay.STAY.getName() + "? ");
				input = kb.next();
				input = input.toUpperCase();
				if (!input.equals(HitOrStay.HIT.getName()) && !input.equals(HitOrStay.STAY.getName())) {
					System.out.println("YOU DIDN'T ENTER " + HitOrStay.HIT.getName() + " OR "
							+ HitOrStay.STAY.getName());
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
		System.out.println(this.name + "'S HAND IS CURRENTLY: " + this.getHand());
		System.out.println(this.name + "'S HAND VALUE: " + this.getHand().getHandValue());
	}

	private void hit(Dealer d) {
		System.out.println();
		System.out.println(this.name + " CHOSE TO " + HitOrStay.HIT.getName());
		Card newCard = d.getDeck().dealCard();
		System.out.println(this.name + " DREW A [" + newCard + "]");
		this.getHand().addCard(newCard);
		if(this.getHand().getHandValue() > AbstractHand.BLACKJACK_MAX_VALUE) {
			System.out.println(this.name + " HAS BUSTED WITH: " + this.getHand().getHandValue());
			return;
		}
	}

	private void stay() {
		System.out.println();
		System.out.println(this.name + " CHOSE TO " + HitOrStay.STAY.getName() + " WITH: " + this.getHand().getHandValue());
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
