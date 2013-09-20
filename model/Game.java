package model;

public class Game {
	
	//Config values
		private final int FRAMERATE = 30;
		private final int ELEMENT_SIZE = 30;
		private final int GRID_SIZE_X = 30;
		private final int GRID_SIZE_Y = 20;
		private final int MAX_SAVE_FILES = 5;
		private final char WALL = '#';
		private final char FLOOR = ' ';
		private final char PLAYER = 'P';
		private final char TARGET = 'O';
		private final char DIAMOND = 'X';
		
		public int getSizeX(){
			return GRID_SIZE_X * ELEMENT_SIZE;
		}
		public int getSizeY(){
			return GRID_SIZE_Y * ELEMENT_SIZE;
		}
		
		public char getTargetCharacter(){
			return TARGET;
		}
		public char getWallCharacter(){
			return WALL;
		}
		public char getFloorCharacter(){
			return FLOOR;
		}
		public char getPlayerCharacter(){
			return PLAYER;
		}
		public char getDiamondCharacter(){
			return DIAMOND;
		}
}
