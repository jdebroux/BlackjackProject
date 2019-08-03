package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.cards.common.Deck;

public class BlackjackGameDriver {
	private Scanner kb = new Scanner(System.in);
	private Deck masterDeck;
	private BlackjackHand dealerHand;
	private BlackjackHand playerHand;
	private Player player;
	private Dealer dealer;

	public BlackjackGameDriver() {
		this.masterDeck = new Deck();
		this.dealerHand = new BlackjackHand();
		this.playerHand = new BlackjackHand();
		this.player = new Player(playerHand);
		this.dealer = new Dealer(dealerHand);
	}

	public void launch() {
		startUp();
		mainMenu();

	}

	private void mainMenu() {
		deckQuestion();
	}

	private void deckQuestion() {
		int input = 0;
		System.out.print("HOW MANY DECKS WOULD YOU LIKE TO PLAY WITH? >> ");
		do {
			try {
				input = kb.nextInt();
			} catch (InputMismatchException ime) {
				kb.nextLine();
			}
			if (input < 1 || input > 10) {
				System.out.println("PLEASE ENTER 1 - 10");
			}
		} while (input < 1 || input > 10);
		amountOfDecks(input);
	}

	private void amountOfDecks(int input) {
		List<Deck> decks = new ArrayList<>();
		for (int i = 1; i < input; i++) {  //started i at 1 because masterDeck starts with 1 deck.
			Deck deck = new Deck();
			decks.add(deck);
		}
		if(decks.size() > 0) {
			for (Deck deck : decks) {
				masterDeck.addADeck(deck);
			}
		}
	}

	private void startUp() {
		System.out.println();
		System.out.println("********************************************************************");
		System.out.println("*                                                                  *");
		System.out.println("* 888     888                888       d8b                888      *");
		System.out.println("* 888     888                888       Y8P                888      *");
		System.out.println("* 888     888                888                          888      *");
		System.out.println("* 888     888                888                          888      *");
		System.out.println("* 88888b. 888 8888b.  .d8888b888  888 8888 8888b.  .d8888b888  888 *");
		System.out.println("* 888  88b888     88bd88P    888 .88P  888     88bd88P    888 .88P *");
		System.out.println("* 888  888888.d888888888     888888K   888.d888888888     888888K  *");
		System.out.println("* 888 d88P888888  888Y88b.   888  88b  888888  888Y88b.   888  88b *");
		System.out.println("* 88888P  888 Y888888  Y8888P888  888  888 Y888888  Y8888P888  888 *");
		System.out.println("*                                      888                         *");
		System.out.println("*                                     d88P                         *");
		System.out.println("*                                   888P                           *");
		System.out.println("********************************************************************");
		System.out.println();
	}
}
