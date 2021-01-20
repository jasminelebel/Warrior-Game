// -------------------------------------------------------
// Assignment 4
// Written by: Jasmine Lebel (40135464)
// For COMP 248 Section EC – Fall 2019
// --------------------------------------------------------

// Class Player's purpose is to store names of both players, as well as players' 
// x-positions, y-positions, and the level player is at. Class also contains 
// player's energy and adjusts players energy according to player's current 
// position on the board. Class contains boolean which determines whether or 
// not player has won the game (reach highest position of the game), contains 
// an equals method to determine whether or not a player is already at another 
// player's position (which leads to a challenge). ToString() method in the class 
// also returns all player attributes in a descriptive message at the end of each 
// round.

public class Player {

	// Declaration of private instance variables
	private String name;
	private int level;
	private int x;
	private int y;
	private int energy;

	// Declaration of default constructor
	public Player() {
		name = "";
		energy = 10;
		level = 0;
		x = 0;
		y = 0;
	}

	// Constructor which uses String parameter to represent player's name
	public Player(String name) {
		this.name = name;
		energy = 10;
		level = 0;
		x = 0;
		y = 0;
	}

	// Constructor assigns value of 3 location integer parameters to new object
	public Player(int level, int x, int y) {
		this.level = level;
		this.x = x;
		this.y = y;
		energy = 10;
		name = "";
	}

	// Copy constructor to duplicate passed Player object
	public Player(Player player) {
		this.level = player.level;
		this.x = player.x;
		this.y = player.y;
		this.energy = player.energy;
		this.name = player.name;
	}

	// Declaration of accessor methods for all attributes
	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getEnergy() {
		return energy;
	}

	// Declaration of mutator methods for all attributes
	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	// method moves calling Player object to passed Player object's location
	public void moveTo(Player p) {
		this.x = p.x;
		this.y = p.y;
	}

	// boolean method returns true if calling player's location is at the last
	// location of the board, and false otherwise. Returning true means player has
	// won the game
	public boolean won(Board b) {
		return ((b.getSize() - 1 == x) && (b.getSize() - 1 == y));
	}

	// boolean method returns true if location of calling player is the same as the
	// location of passed player, and false otherwise
	public boolean equals(Player p) {
		return ((this.level == p.getLevel()) && (this.x == p.getX()) && (this.y == p.getY()));
	}

	// toString method returns string containing values of all attributes of calling
	// Player object
	public String toString() {
		return (getName() + " is on level " + level + " at location (" + getX() + ", " + getY() + ") and has "
				+ getEnergy() + " units of energy.");
	}

}
