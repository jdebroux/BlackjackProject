package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.cards.common.AbstractHand;
import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;
import com.skilldistillery.cards.common.Rank;
import com.skilldistillery.cards.common.Suit;

public class BlackjackGameDriver {
	private Scanner kb = new Scanner(System.in);
	private Player player;
	private Dealer dealer;
	private BlackjackTable table;

	public void launch() {
		startUp();
		mainGame();
	}// end launch

	private void mainGame() {
		boolean keepPlaying = true;
		this.table = new BlackjackTable();
		playersInfo();
		int input = deckQuestion();
		do {
			keepPlaying = blackjackGame(input, keepPlaying);
			for (Player individualPlayer : table.getPlayerList()) {
				individualPlayer.getHand().clear();
			}
			dealer.getHand().clear();
		} while (keepPlaying);
		kb.nextLine();
		System.out.println();
	}// end mainMenu

	private void playersInfo() {
		System.out.print("WHAT IS YOUR NAME? ");
		String name = kb.nextLine();
		name = name.toUpperCase();
		this.player = new Player(new BlackjackHand(), name);
		System.out.println();
		System.out.println("WELCOME, " + player.getName());
		System.out.println();
		addPlayersToTable();
		this.dealer = new Dealer(new BlackjackHand(), "GARY", new Deck());
	}// end playerName

	private void addPlayersToTable() {
		int amountOfPlayers = 5;
		do {
			System.out.print("HOW MANY ADDITIONAL PLAYERS WOULD YOU LIKE AT YOUR TABLE? (0-4): ");
			try {
				amountOfPlayers = kb.nextInt();
			} catch (InputMismatchException ime) {
				kb.nextLine();
				amountOfPlayers = 5;
			}
			if (amountOfPlayers < 0 || amountOfPlayers > 4) {
				System.out.println("\n*** PLEASE ENTER A VALID OPTION ***\n");
			}
		} while (amountOfPlayers < 0 || amountOfPlayers > 4);
		switch (amountOfPlayers) {
		case 4:
			table.addPlayer(new CompPlayer(new BlackjackHand(), "JON"));
		case 3:
			table.addPlayer(new CompPlayer(new BlackjackHand(), "JAMIE"));
		case 2:
			table.addPlayer(new CompPlayer(new BlackjackHand(), "ARYA"));
		case 1:
			table.addPlayer(new CompPlayer(new BlackjackHand(), "TYRION"));
		case 0:
			table.addPlayer(this.player);
			break;
		}
		if (amountOfPlayers > 0 && amountOfPlayers < 5) {
			System.out.print("YOU ARE PLAYING WITH: ");
			for (Player individualPlayer : table.getPlayerList()) {
				if (individualPlayer instanceof CompPlayer) {
					System.out.print(individualPlayer.getName() + " ");
				}
			}
			System.out.println();
		} // end if
	}// end addPlayersToTable

	private boolean blackjackGame(int input, boolean keepPlaying) {
		System.out.println("\n\n*********** NEW HAND ***********");
		if (dealer.getDeck().checkDeckSize() < (input * 52) / 2) {
			System.out.print("CARD COUNT LOW,");
			System.out.print(" DEALER " + dealer.getName() + " ADDS " + input + " DECK");
			if (input > 1) {
				System.out.println("S\n");
			} else {
				System.out.println();
			}
			this.dealer.setDeck(new Deck());
			amountOfDecks(input);
		} // end if
		System.out.println("DEALER " + dealer.getName() + " DEALS");
		System.out.println();
		Card firstCard = deal();
		if (firstCard.getValue() >= 10) {
			checkDealerForBlackjack(firstCard);
			if (dealer.getHand().getHandValue() == BlackjackHand.BLACKJACK_MAX_VALUE) {
				return gameOver();
			}
		}
		if (dealer.getHand().getHandValue() == BlackjackHand.BLACKJACK_MAX_VALUE) {
			finishCompPlayersGame();
			return gameOver();
		} // end if's
		if (player.getHand().getHandValue() < BlackjackHand.BLACKJACK_MAX_VALUE) {
			player.hitOrStay(dealer, kb);
		}
		finishCompPlayersGame();
		dealer.hitOrStay(player);
		if (dealer.getHand().getHandValue() > BlackjackHand.BLACKJACK_MAX_VALUE) {
			for (Player individualPlayer : table.getPlayerList()) {
				if (individualPlayer.getHand().getHandValue() <= BlackjackHand.BLACKJACK_MAX_VALUE) {
					System.out.println(individualPlayer.getName() + " HAS WON!");
				} else if (individualPlayer.getHand().getHandValue() > BlackjackHand.BLACKJACK_MAX_VALUE) {
					System.out.println(individualPlayer.getName() + " HAS LOST!");
				} // end else if
			} // end for
			return gameOver();
		} // end ifx1
		comparingHands();
		return gameOver();
	}// end startGame

	private void finishCompPlayersGame() {
		int stayAmount = 0;
		for (Player individualPlayer : table.getPlayerList()) {
			if (individualPlayer instanceof CompPlayer) {
				((CompPlayer) individualPlayer).hitOrStay(dealer, stayAmount);
				stayAmount++;
			}
		}
	}// end finishCompPlayersGame

	private Card deal() {
		Card firstCard = new Card();
		for (int i = 0; i < BlackjackHand.BJ_STARTING_HAND; i++) {
			for (Player individualPlayer : table.getPlayerList()) {
				individualPlayer.getHand().addCard(dealer.getDeck().dealCard());
			}
			if (i == 0) {
				firstCard = dealer.getDeck().dealCard();
//				firstCard = new Card(Suit.CLUBS, Rank.ACE);
				dealer.getHand().addCard(firstCard);
			}
		}
		dealer.getHand().addCard(dealer.getDeck().dealCard());
//		dealer.getHand().addCard(new Card(Suit.CLUBS, Rank.SIX));
		checkTwoAces(dealer.getHand());
		for (Player individualPlayer : table.getPlayerList()) {
			checkTwoAces(individualPlayer.getHand());
			System.out.println(individualPlayer.getName() + " IS DEALT: " + individualPlayer.getHand());
			System.out.println(
					individualPlayer.getName() + "'S HAND VALUE: " + individualPlayer.getHand().getHandValue());
			if (individualPlayer.getHand().getHandValue() == BlackjackHand.BLACKJACK_MAX_VALUE) {
				System.out.println("***** " + individualPlayer.getName() + " HAS BLACKJACK! *****");
			}
			System.out.println();
		}
		System.out.println(
				"DEALER " + dealer.getName() + " IS SHOWING: " + firstCard.getValue() + " [" + firstCard + "]");
		return firstCard;
	}// end deal

	private void checkTwoAces(AbstractHand playerHand) {
		int counter = 0;
		for (Card card : playerHand.getCards()) {
			if (card.getValue() == Rank.ACE.getValue()) {
				counter++;
				if (counter == 2) {
					card.setRank(Rank.ACE1);
				}
			}
		}
	}// end checkTwoAces

	private void comparingHands() {
		for (Player individualPlayer : table.getPlayerList()) {
			if (dealer.getHand().getHandValue() == individualPlayer.getHand().getHandValue()
					&& dealer.getHand().getHandValue() <= BlackjackHand.BLACKJACK_MAX_VALUE) {
				System.out.println(individualPlayer.getName() + " TIED DEALER " + dealer.getName() + "!");
			} else if ((dealer.getHand().getHandValue() > individualPlayer.getHand().getHandValue()
					&& dealer.getHand().getHandValue() <= BlackjackHand.BLACKJACK_MAX_VALUE)
					|| (individualPlayer.getHand().getHandValue() > BlackjackHand.BLACKJACK_MAX_VALUE)) {
				System.out.println(individualPlayer.getName() + " LOSES!");
			} else if (dealer.getHand().getHandValue() < individualPlayer.getHand().getHandValue()
					&& individualPlayer.getHand().getHandValue() <= BlackjackHand.BLACKJACK_MAX_VALUE) {
				System.out.println(individualPlayer.getName() + " WINS!");
			}
		}
	}// end comparingHands

	private boolean gameOver() {
		System.out.println();
		String playAgain = "";
		do {
			System.out.print("\nWOULD YOU LIKE TO PLAY AGAIN (Y/N)? ");
			playAgain = kb.next();
			System.out.println();
			playAgain = playAgain.toUpperCase();
			if (playAgain.startsWith("Y")) {
				return true;
			} else if (playAgain.startsWith("N")) {
				return false;
			} else
				System.out.println("** PLEASE ENTER A VALID RESPONSE **");
		} while (!(playAgain.startsWith("Y") || playAgain.startsWith("N")));
		return false;
	}// end gameOver

	private void checkDealerForBlackjack(Card secondCard) {
		System.out.println("DEALER " + dealer.getName() + " CHECKS HIS HAND FOR BLACKJACK");
		if (dealer.getHand().getHandValue() != BlackjackHand.BLACKJACK_MAX_VALUE) {
			System.out.println(dealer.getName() + " DID NOT GET BLACKJACK");
			return;
		} else {
			System.out.println("DEALER " + dealer.getName() + " GOT BLACKJACK **");
			System.out.println();
		}
		for (Player individualPlayer : table.getPlayerList()) { 
			if (dealer.getHand().getHandValue() == BlackjackHand.BLACKJACK_MAX_VALUE
					&& individualPlayer.getHand().getHandValue() == BlackjackHand.BLACKJACK_MAX_VALUE) {
				System.out.println(
						individualPlayer.getName() + " AND DEALER " + dealer.getName() + " BOTH GOT BLACKJACK");
				System.out.println(individualPlayer.getName() + " WINS");
			} else if (individualPlayer.getHand().getHandValue() != BlackjackHand.BLACKJACK_MAX_VALUE
					&& dealer.getHand().getHandValue() == BlackjackHand.BLACKJACK_MAX_VALUE) { 
				System.out.println(individualPlayer.getName() + " LOSES!\n");
			}
		}
	}// end checkForBlackjack

	private int deckQuestion() {
		int input = 0;
		do {
			System.out.print("\nHOW MANY DECKS WOULD YOU LIKE TO PLAY WITH? >> ");
			try {
				input = kb.nextInt();
			} catch (InputMismatchException ime) {
				kb.nextLine();
			}
			if (input < 1 || input > 10000) {
				System.out.println("PLEASE ENTER A VALID NUMBER 1-1000 ");
			} // end if
		} while (input < 1 || input > 10000);
		amountOfDecks(input);
		return input;
	}// end deckQuestion

	private void amountOfDecks(int input) {
		List<Deck> decks = new ArrayList<>();
		for (int i = 1; i < input; i++) { // started i at 1 because masterDeck starts with 1 deck.
			Deck deck = new Deck();
			decks.add(deck);
		} // end for
		if (decks.size() > 0) {
			for (Deck deck : decks) {
				dealer.getDeck().addADeck(deck);
			} // end for
		} // end if
		dealer.dealerShuffle();
		System.out.println();
		System.out.print("DEALER " + dealer.getName() + " SHUFFLES " + input + " DECK");
		if (input > 1) {
			System.out.println("S\n");
		} else {
			System.out.println();
		}
	}// end amountOfDecks

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
	}// end startUp
}// end BlackjackGameDriver Class
