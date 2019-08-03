package com.skilldistillery.cards.common;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.cards.blackjack.BlackjackGameDriver;

public class GameDriver {
	private Scanner kb = new Scanner(System.in);
	private BlackjackGameDriver bjgd = new BlackjackGameDriver();

	public void launch() {
		mainMenu();

	}

	private void mainMenu() {
		int input = 1;
		System.out.println("** WELCOME TO CARD GAMES **");
		System.out.println();
		while (input > 0 && input < 4) {
			System.out.println("WHICH GAME WOULD YOU LIKE TO PLAY? ");
			System.out.println("1) BLACKJACK ");
			System.out.println("2) POKER ");
			System.out.println("3) SOLITAIRE ");
			System.out.println("4) QUIT ");
			System.out.println();
			System.out.print("SELECTION >> ");
			try {
				input = kb.nextInt();
			} catch (InputMismatchException ime) {
				kb.nextLine();
			}
			if(input < 1 || input > 4) {
				System.out.println("\n*** PLEASE ENTER A VALID OPTION ***\n");
			}
			mainMenuSwitch(input);
		}
	}

	private void mainMenuSwitch(int input) {
		switch (input) {
		case 1:
			bjgd.launch();
			break;
		case 2:
			System.out.println("\nGAME STILL UNDER DEVELOPMENT\n");
			break;
		case 3:
			System.out.println("\nGAME STILL UNDER DEVELOPMENT\n");
			break;
		case 4:
			System.out.println("\nTHANK YOU FOR PLAYING");
			break;
		}
	}

}
