package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Dealing {
	private Scanner kb = new Scanner(System.in);
	private Deck deck = new Deck();
	private boolean keepPlaying = true;

	public static void main(String[] args) {
		Dealing app = new Dealing();
		app.launch();
	}

	private void launch() {
		List<Card> hand = new ArrayList<>();
		int cardAmount = 0;
		do {
			cardAmount = getCardAmount(cardAmount);
			hand = dealCards(cardAmount, hand);
			displayHand(hand);
			playAgain();
		} while (keepPlaying);
	}

	private void playAgain() {
		System.out.print("Would you like to play again? ");
		String cont = kb.next();
		switch (cont) {
		case "":
		case "y":
		case "Y":
		case "Yes":
		case "yes":
		case "ok":
		case "Ok":
		case "hit me":
			break;
		default:
			keepPlaying = false;
			break;
		}
		kb.nextLine();
	}

	private void displayHand(List<Card> hand) {
		int handValue = 0;
		for (Card card : hand) {
			System.out.println(card);
			handValue += card.getValue();
		}
		System.out.println("Hand value: " + handValue);
		System.out.println("Deck size: " + deck.checkDeckSize());
	}

	private List<Card> dealCards(int cardAmount, List<Card> hand) {
		deck.shuffleDeck();
		deck.shuffleDeck();
		deck.shuffleDeck();
		for (int i = 0; i < cardAmount; i++) {
			hand.add(deck.dealCard());
		}
		return hand;
	}

	private int getCardAmount(int cardAmount) {
		boolean b = true;
		do {
			System.out.print("How many cards would you like? ");
			try {
				cardAmount = kb.nextInt();
				if (cardAmount > 0 && cardAmount <= deck.checkDeckSize()) {
					b = false;
				} else {
					System.out.println("Please enter a valid amount of cards.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid amount of cards.");
				kb.nextLine();
			}
		} while (b);
		return cardAmount;
	}
}
