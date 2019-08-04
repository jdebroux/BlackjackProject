package com.skilldistillery.cards.blackjack;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.cards.common.AbstractHand;
import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Deck;

public class BlackjackGameDriver {
	private Scanner kb = new Scanner(System.in);
	private BlackjackHand dealerHand;
	private BlackjackHand playerHand;
	private Player player;
	private Dealer dealer;
//	private List<Player> playerList;

	public BlackjackGameDriver() {
		this.dealerHand = new BlackjackHand();
		this.playerHand = new BlackjackHand();
//		this.playerList = new ArrayList<>();  //this is how I'll add multiple players
	}// end BlackjackGameDriver Constructor

	public void launch() {
		startUp();
		mainGame();

	}// end launch

	private void mainGame() {
		boolean keepPlaying = true;
		playerNames();
		int input = deckQuestion();
		do {
			keepPlaying = blackjackGame(input, keepPlaying);
			player.getHand().clear();
			dealer.getHand().clear();
		} while (keepPlaying);
		System.out.println();
	}// end mainMenu

	private void playerNames() {
		System.out.print("WHAT IS YOUR NAME? ");
		String name = kb.nextLine();
		name = name.toUpperCase();
		this.player = new Player(playerHand, name);
		System.out.println("WELCOME, " + player.getName());
	}// end playerName

	private boolean blackjackGame(int input, boolean keepPlaying) {
		System.out.println("\n\n\n\n");
		if (dealer.getDeck().checkDeckSize() < (input * 52)/3) {
			System.out.print("CARD COUNT LOW");
			System.out.print("DEALER " + dealer.getName() + " ADDS " + input + " DECK");
			if (input > 1) {
				System.out.println("S\n");
			} else {
				System.out.println();
			}
			amountOfDecks(input);
		} // end if
		System.out.println("DEALER " + dealer.getName() + " DEALS");
		player.hand.addCard(dealer.getDeck().dealCard());
		dealer.hand.addCard(dealer.getDeck().dealCard());
		player.hand.addCard(dealer.getDeck().dealCard());
		Card secondCard = dealer.getDeck().dealCard();
		dealer.hand.addCard(secondCard);
		System.out.println(player.getName() + "'S HAND: " + player.getHand());
		System.out.println(player.getName() + "'S HAND VALUE: " + player.hand.getHandValue() + "\n");
		System.out.println("DEALER " + dealer.getName() + " IS SHOWING: [" + secondCard + "]");

		if (secondCard.getValue() >= 10) {
			checkDealerForBlackjack(secondCard, player, dealer);
		} else if (secondCard.getValue() < 10) {
			checkPlayersForBlackjack(player);
		}

		if (dealer.getHand().getHandValue() == AbstractHand.BLACKJACK_MAX_VALUE
				|| player.getHand().getHandValue() == AbstractHand.BLACKJACK_MAX_VALUE) {
			return gameOver();
		} // end if's

		player.hitOrStay(dealer, kb);
		if (player.getHand().getHandValue() > AbstractHand.BLACKJACK_MAX_VALUE) {
			return gameOver();
		} // end if's
		System.out.println("\nDEALER " + dealer.getName() + " FLIPS HIS SECOND CARD");
		System.out.println("DEALER " + dealer.getName() + "'S HAND: " + dealer.getHand());
		System.out.println("DEALER " + dealer.getName() + "'S HAND VALUE: " + dealer.getHand().getHandValue());
		System.out.println();
		dealer.hitOrStay(player);
		comparingHands();
		return gameOver();
	}// end startGame

	private void comparingHands() {
		if (dealer.getHand().getHandValue() == player.getHand().getHandValue()) {
			System.out.println("DEALER " + dealer.getName() + " AND " + player.getName() + " TIED");
		} else if (dealer.getHand().getHandValue() > player.getHand().getHandValue()
				&& dealer.getHand().getHandValue() <= AbstractHand.BLACKJACK_MAX_VALUE) {
			System.out.println(player.getName() + " LOSES!");
		} else if (dealer.getHand().getHandValue() < player.getHand().getHandValue()
				&& player.getHand().getHandValue() <= AbstractHand.BLACKJACK_MAX_VALUE) {
			System.out.println(player.getName() + " WINS!");
		}
	}// end comparingHands

	private boolean gameOver() {
		System.out.println();
		String playAgain = "";
		do {
		System.out.print("WOULD YOU LIKE TO PLAY AGAIN (Y/N)? ");
		playAgain = kb.next();
		playAgain = playAgain.toUpperCase();
		if ( playAgain.startsWith("Y")) {
			return true;
		} else if (playAgain.startsWith("N")){
			return false;
		}else
			System.out.println("** PLEASE ENTER A VALID RESPONSE **");
		}while (!(playAgain.startsWith("Y") || playAgain.startsWith("N")));
		return false;
	}

	private void checkPlayersForBlackjack(Player eachPlayer) {
		if (eachPlayer.getHand().getHandValue() == AbstractHand.BLACKJACK_MAX_VALUE) {
			System.out.println(player.getName() + " HAS BLACKJACK!");
		}
	}// end checkPlayersForBlackjack

	private void checkDealerForBlackjack(Card secondCard, Player varyingPlayer, Dealer dealer) {
		System.out.println("DEALER " + dealer.getName() + " CHECKS HIS HAND FOR BLACKJACK");
		if (dealer.getHand().getHandValue() == AbstractHand.BLACKJACK_MAX_VALUE
				&& varyingPlayer.getHand().getHandValue() == AbstractHand.BLACKJACK_MAX_VALUE) {
			System.out.println(varyingPlayer.getName() + " AND DEALER " + dealer.getName() + " BOTH GOT BLACKJACK");
			System.out.println("ROUND IS A PUSH FOR " + varyingPlayer.getName());
			return;
		} else if (varyingPlayer.getHand().getHandValue() != AbstractHand.BLACKJACK_MAX_VALUE
				&& dealer.getHand().getHandValue() == AbstractHand.BLACKJACK_MAX_VALUE) {
			System.out.println(
					"DEALER " + dealer.getName() + " GOT BLACKJACK AND " + varyingPlayer.getName() + " DID NOT\n");
			System.out.println(varyingPlayer.getName() + " LOSES THIS ROUND\n");
		} else if (varyingPlayer.getHand().getHandValue() == AbstractHand.BLACKJACK_MAX_VALUE
				&& dealer.getHand().getHandValue() != AbstractHand.BLACKJACK_MAX_VALUE) {
			System.out.println(varyingPlayer.getName() + " GOT BLACKJACK AND DEALER " + dealer.getName() + " DID NOT");
			System.out.println(varyingPlayer.getName() + " WINS THIS ROUND");
		} else {
			System.out.println(dealer.getName() + " DID NOT GET BLACKJACK");
		}
	}// end checkForBlackjack

	private int deckQuestion() {
		int input = 0;
		System.out.print("\nHOW MANY DECKS WOULD YOU LIKE TO PLAY WITH? >> ");
		do {
			try {
				input = kb.nextInt();
			} catch (InputMismatchException ime) {
				kb.nextLine();
			}
			if (input < 1 || input > 100) {
				System.out.println("PLEASE ENTER 1 - 100");
			} // end if
		} while (input < 1 || input > 100);
		amountOfDecks(input);
		return input;
	}// end deckQuestion

	private void amountOfDecks(int input) {
		Deck masterDeck = new Deck();
		this.dealer = new Dealer(dealerHand, masterDeck, "GARY");
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
		System.out.println("DEALER " + dealer.getName() + " SHUFFLES");
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
