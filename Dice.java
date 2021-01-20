// -------------------------------------------------------
// Assignment 4
// Written by: Jasmine Lebel (40135464)
// For COMP 248 Section EC – Fall 2019
// --------------------------------------------------------

// Class Dice's purpose is to store two integers, die 1 and die 2, which store the 
// role of two separate die. Class also contains methods such as rollDice(), which 
// returns the value of die 1 and 2, a boolean isDouble(), which returns the truth 
// value of whether or not dice one is equal to dice two, and a toString() method 
// which returns the value of both attributes in a descriptive message. 

import java.util.Random;

public class Dice {

	// Declaration of variables that will store the role of two die
	private int die1;
	private int die2;

	// Declaration of default constructor
	Dice() {
	}

	// Declaration of accessor methods that return the value of die 1 and of die 2
	public int getDie1() {
		return die1;
	}

	public int getDie2() {
		return die2;
	}

	// Random generator assigns a value between 1 and 6 to both die 1 and die 2 and
	// returns the sum of both die
	public int rollDice() {
		Random diceRoll = new Random();
		die1 = diceRoll.nextInt(6) + 1;
		die2 = diceRoll.nextInt(6) + 1;
		return die1 + die2;
	}

	// Boolean used to determine if die1 is equal to die 2 and return truth value
	public boolean isDouble() {
		if (die1 == die2) {
			return true;
		} else
			return false;
	}

	// Returns string containing the value of the two die respectively
	public String toString() {
		return "Die 1: " + die1 + " Die 2: " + die2;
	}

}
