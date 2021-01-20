// -------------------------------------------------------
// Assignment 4
// Written by: Jasmine Lebel (40135464)
// For COMP 248 Section EC – Fall 2019
// --------------------------------------------------------

// Class board's purpose is to store a 3D array called board, in order to create the 
// game board based on the chosen number of levels and the desired nxn size. The board 
// class thus prints the board and assigns the energy values to their respective positions.

public class Board {
	
	// Declaration of private instance variables
		private int board[][][];
		private static int MIN_LEVEL = 3;
		private static int MIN_SIZE = 3;
		private int level;
		private int size;

		// Declaration of default constructor which creates board using assigned values
		// of level and size
		public Board() {
			level = 3;
			size = 4;
			createBoard(level, size);
		}

		// Declaration of parameterized constructor with integers l and x to represent
		// number of levels and number of rows and columns each board level has
		public Board(int l, int x) {
			level = l;
			size = x;
			createBoard(l, x);
		}

		// Private method creates 3D-array board using passed dimensions
		private void createBoard(int level, int size) {
			board = new int[level][][];

			for (int l = 0; l < level; l++) {
				board[l] = new int[size][];

				for (int x = 0; x < size; x++) {
					board[l][x] = new int[size];

					// Initializes each location of the board based on the sum of level, x and y
					for (int y = 0; y < size; y++) {
						if ((l + x + y) % 3 == 0) {
							board[l][x][y] = -3;
						} else if ((l + x + y) % 5 == 0) {
							board[l][x][y] = -2;
						} else if ((l + x + y) % 7 == 0) {
							board[l][x][y] = 2;
						} else
							board[l][x][y] = 0;
					}
				}
			}
		}

		// Declaration of accessor methods which will return the values of the board
		// level and its size
		public int getLevel() {
			return level;
		}

		public int getSize() {
			return size;
		}

		// Method returns the energy adjustment value stored in that location of the
		// array board
		public int getEnergyAdj(int l, int x, int y) {
			return board[l][x][y];
		}

		// String method returns string showing energy adjustment values for each board
		// at each level
		public String toString() {
			String boardDisplay = "";
			for (int l = 0; l < level; l++) {
				boardDisplay += "Level " + (l) + ":\n" + "--------\n";
			
				for (int x = 0; x < size; x++) {
					for (int y = 0; y < size; y++) {
						board[0][0][0] = 0;
						boardDisplay += String.format("\t%2d", board[l][x][y]) + " ";
					}

					boardDisplay += "\n";
				}

				boardDisplay += "\n";

			}
			return boardDisplay;
		}

}


