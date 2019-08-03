package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.common.AbstractHand;
import com.skilldistillery.cards.common.Card;

public class Player extends AbstractBlackjackPlayer {

	public Player(BlackjackHand hand) {
		super(hand);
	}

	public void hitOrStay(Dealer d, Scanner kb) {
		boolean keepGoing = true;
		String input = "";
		while (hand.getHandValue() < AbstractHand.BLACKJACK_MAX_VALUE || keepGoing == true) {
			do {
				System.out.print(
						"Would you like to " + HitOrStay.HIT.getName() + " or " + HitOrStay.STAY.getName() + "? ");
				input = kb.nextLine();
				input = input.toLowerCase();
				if (!input.equals(HitOrStay.HIT.getName()) && !input.equals(HitOrStay.STAY.getName())) {
					System.out.println("You didn't enter '" + HitOrStay.HIT.getName() + "' or '"
							+ HitOrStay.STAY.getName() + "'.");
				}
			} while (!input.equals(HitOrStay.HIT.getName()) && !input.equals(HitOrStay.STAY.getName()));

			switch (input) {
			case "hit":
				hit(d);
				break;
			case "stay":
				stay();
				return;
			}
		}
	}

	private void hit(Dealer d) {
		System.out.println("Player has chosen to " + HitOrStay.HIT.getName() + ".");
		hand.addCard(d.getDeck().dealCard());
		readHand();
	}

	private void stay() {
		System.out.println("Player has chosen to " + HitOrStay.STAY.getName() + ".");
		readHand();
	}

	private void readHand() {
		System.out.print("Player hand is: ");
		for (Card card : hand.getHand()) {
			System.out.print(card + " ");
		}
		System.out.println("\nand worth: " + hand.getHandValue());
	}

}
