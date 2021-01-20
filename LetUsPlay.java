// -------------------------------------------------------
// Assignment 4
// Written by: Jasmine Lebel (40135464)
// For COMP 248 Section EC – Fall 2019
// --------------------------------------------------------

// LetUsPlay Class is program's driver class. The program's purpose is to create a 
// game, namely "Nancy's 3D Warrior Game," where two players, one turn each, roll a 
// pair of two die, and the sum of these die corresponds to the new position they 
// will take on the generated array board. Point of the game is to have a player reach 
// the highest step of the last board, where it will be declared as a winner. Throughout 
// the course of the game, players will win and lose energy units based on where they 
// land on the board. Players will also win or lose energy or change position of a player 
// lands on the same position and the other player and is given the option to challenge 
// or forfeit. This gives the player a 50% chance to either steal other player's position 
// and/or lose half of their energy units. Player always needs more than 0 energy units, 
// and loop loops the game until one of the two players is declared as a winner.

import java.util.Random;
import java.util.Scanner;

public class LetUsPlay {

	public static void main(String[] args) {

		// Prints welcome banner
		System.out.println("\t*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
		System.out.println("\t*                                           *");
		System.out.println("\t*    WELCOME to Nancy's 3D Warrior Game!    *");
		System.out.println("\t*                                           *");
		System.out.println("\t*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");

		// Prompts user to determine if they want to keep the default board size or if
		// they want to pick their own board size
		System.out.println("\nThe default game board has 3 levels and each level has a 4x4 board.");
		System.out.println("You can use this default board size or change the size");
		System.out.println("\t0 to use the default board size");
		System.out.println("\t-1 to enter your own size");
		System.out.print("==> What do you want to do? ");

		Scanner scanner = new Scanner(System.in);

		int boardSize = scanner.nextInt();
		Board board = new Board();

		// User chooses default board size
		if (boardSize == 0) {
			System.out.println("Default board size is used.");
			board = new Board(4, 4);

		}
		// User chooses to enter their own size and program will prompt user to enter
		// desired number of levels
		else if (boardSize == -1) {
			System.out.print("How many levels would you like? (minimum size 3, max 10) ");

		}

		// Loop ensures that user enters either 0 or -1
		else
			while (boardSize != 0 || boardSize != -1) {
				System.out.println("Sorry but " + boardSize + " is not a legal choice.");
				boardSize = scanner.nextInt();
				if (boardSize == 0) {
					System.out.println("Default board size is used.");
					break;

				}
				// User chooses to enter their own size and program prompts user to enter
				// desired number of levels
				else if (boardSize == -1) {
					System.out.print("How many levels would you like? (minimum size 3, max 10) ");
					break;

				}
			}

		// Loop ensures that user enters a level number between 3 and 10
		if (boardSize == -1) {
			int levelNum = scanner.nextInt();

			while (levelNum < 3 || levelNum > 10) {
				System.out.println("Sorry but " + levelNum + " is not a legal choice.");
				levelNum = scanner.nextInt();
			}

			// Prompts user to enter level size between 4x4 and 10x10
			System.out.println("\nWhat size do you want the nxn boards on each level to be?");
			System.out.println("Minimum size is 3x3, max is 10x10.");
			System.out.println("==> Enter the value of n: ");

			int levelSize = scanner.nextInt();
			// Loop ensures that user enters a level number between 3 and 10
			while (levelSize < 4 || levelSize > 10) {
				System.out.println("Sorry but " + levelSize + " is not a legal choice.");
				levelSize = scanner.nextInt();
			}

			board = new Board(levelNum, levelSize);
		}

		// Prints 3D board
		System.out.println("\nYour 3D board has been set up and looks like this:");
		System.out.println();
		System.out.println(board);

		// Prompts user to input Player 0's name and stores name into Player array
		System.out.print("What is player 0's name (one word only): ");

		// Initializes array to store player names
		Player players[] = new Player[2];
		players[0] = new Player(scanner.next());

		// Prompts user to input Player 1's name and stores name into Player array
		System.out.print("\nWhat is player 1's name (one word only): ");
		players[1] = new Player(scanner.next());

		// Randomly determines which of the two players will go first
		Random start = new Random();
		int startingPlayer = start.nextInt(2);

		// The starting player is chosen so a message is printed to state that the game
		// has started
		System.out.println("\nThe game has started " + players[startingPlayer].getName() + " goes first");
		System.out.println("===================================");

		// Game begins!!
		gameStarts: while (true) {

			// Loop for each of the two players' turns
			for (int i = 0; i < 2; i++) {
				int playerPlaying = (startingPlayer + i) % 2;
				Player player = players[playerPlaying];
				System.out.println("It is " + player.getName() + "'s turn");

				// If player has less than 0 or 0 energy units, the dice is tossed three times
				if (player.getEnergy() <= 0) {
					System.out.println(player.getName()
							+ "'s energy is too low to move, therefore you must roll the die three times");

					// Rolls the die three times
					for (int j = 0; j < 3; j++) {
						Dice dice = new Dice();
						dice.rollDice();

						// Adds two energy units to player each time a double is rolled
						if (dice.isDouble() == true) {
							player.setEnergy(player.getEnergy() + 2);
						}
					}

					// Prints player's new amount of energy after three dice rolls
					System.out.println("After rolling the die three times, " + player.getName() + " has "
							+ player.getEnergy() + " units of energy.");

					// Notifies player if energy is still less than or equal to zero, which is too
					// low to move
					if (player.getEnergy() <= 0) {
						System.out.println("Sorry " + player.getName() + ", your energy is still too low to move.");
						continue;
					}

				}

				// Initializes game's main die
				Dice dice = new Dice();
				dice.rollDice();

				// Outputs the result of the two die the player has rolled
				System.out.println("\t" + player.getName() + " you rolled " + dice);

				// Adds two energy units to player if a double is rolled
				if (dice.isDouble() == true) {
					player.setEnergy(player.getEnergy() + 2);
					System.out.println("\tCongratulations you rolled double " + dice.getDie1()
							+ ". Your energy went up by 2 units.");
				}

				// Sum of the two die
				int dieSum = (dice.getDie1() + dice.getDie2());

				// Calculates player's new potential x-location following dice roll
				int new_x = dieSum / board.getSize() + player.getX();

				// Calculates player's new potential y-location following dice roll
				int new_y = dieSum % board.getSize() + player.getY();

				if ((new_x + player.getX()) > board.getSize()) {
					new_x = new_x % board.getSize();
				}

				if ((new_y + player.getY()) > board.getSize()) {
					new_y = new_y % board.getSize();
				}

				// Adjusts player's position and increases level by one if player is out of
				// bounds
				if ((player.getX() > board.getSize())) {
					player.setLevel(player.getLevel() + 1);
					player.setX(player.getX() % board.getSize());
					player.setY(player.getY() % board.getSize());
				}

				if ((player.getY() > board.getSize())) {
					player.setLevel(player.getLevel() + 1);
					player.setX(player.getX() % board.getSize());
					player.setY(player.getY() % board.getSize());
				}

				// CHALLENGE/FORFEIT
				if (new_x == player.getX() && new_y == player.getY()
						&& player.getLevel() == player.getLevel()) {
					System.out.println("A player is at your new location");
					System.out.println("What do you want to do?");
					System.out.println("\t0 - Challenge and risk losing 50% of your energy units if you lose");
					System.out.println("\t\tor move to new location and get 50% of other players energy units");
					System.out.println(
							"\t1 - to move down one level or move to (0, 0) if at level 0 and lose 2 energy units");

					int challenge = scanner.nextInt();

					
					
					// If user decides to challenge
						if (challenge == 0) {
							Random challenging = new Random();
							int challengeResult = challenging.nextInt(10);

							// If player loses challenge, player loses half of its energy units
							if (challengeResult < 6) {
								player.setEnergy(player.getEnergy() / 2);
							} else if (challengeResult > 6) {
								// If player wins challenge, position is switched with player B, and half of B's
								// energy units are given to player A
								players[0].setX(players[1].getX());
								players[1].setX(players[0].getX());
								players[0].setY(players[1].getY());
								players[1].setY(players[0].getY());
								players[0].setLevel(players[1].getLevel());
								players[1].setLevel(players[0].getLevel());
								players[i % 2].setEnergy(players[i % 2].getEnergy() / 2);
								player.setEnergy(player.getEnergy() + players[i % 2].getEnergy());
								System.out.println("Bravo!! You won the challenge.");
							}

						} else if (challenge == 1) {
							player.setLevel(player.getLevel() - 1);
							player.setEnergy(player.getEnergy() - 2);
						} else while (challenge != 0 || challenge != 1) {
							System.out.println("Sorry but " + challenge + " is not a legal choice.");
							challenge = scanner.nextInt();
					}
						
					
				}

				// Determines if resulting level is >= level number, and removes two energy
				// units if true
				if (player.getLevel() > board.getLevel()) {
					System.out.println(
							"!!! Sorry you need to stay where you are - that throw takes you off the grid and you lose 2 units of energy.");
					player.setEnergy(player.getEnergy() - 2);
				}

				if (player.getLevel() <= board.getLevel()) {
					player.setX(player.getX() + new_x);
					player.setY(player.getY() + new_y);

				}

				// Determines if the player has won
				if (player.won(board)) {
					System.out.println("The winner is player " + player.getName() + ". Well done!!!");
					break gameStarts;
				}

			}

			// Loop to output each players' current statistics at the end of each round
			System.out.println("\nAt the end of this round:");
			for (int i = 0; i < 2; i++) {
				// Prints statistic statement for player
				System.out.println(players[i]);
			}

			// Prompts user to input any key to move on to the next round
			System.out.println("Any key to continue to next round ...");
			scanner.next();
			System.out.println();
		}
		// Closes scanner
		scanner.close();
	}

}
