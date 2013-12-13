package ch.bfh.ti.projekt1.sokoban.model;

import java.util.ArrayList;

public class Level {
	private String[] levels;
	private int numberOfLevels
	;
	private String[] levelHashes;
	public Level(){
		
		numberOfLevels = 10;		
		//TODO: SET ALL THE LEVEL AVAILABLE
		levels = new String[numberOfLevels];
		levels[0] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml";
		levels[1] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level2.xml";
		levels[2] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level3.xml";
		levels[3] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level4.xml";
		levels[4] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level5.xml";
		levels[5] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level6.xml";
		levels[6] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level7.xml";
		levels[7] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level8.xml";
		
		levelHashes = new String[numberOfLevels];
		levelHashes[0] = "4d913082805e39e321efa920a381b3d6473706c9";
		levelHashes[1] = "0ea71bdc345437a270c2007209fa12e40a9dd7d7";
		levelHashes[2] = "cc8b17d91422be1227eb016b999b67e4e9bcbae6";
		levelHashes[3] = "6505e9a18dc1fc446e5a8786c327b2272a03c027";
		levelHashes[4] = "86abf2c5591ee5f0b8c7eb607c8ab2846f82f66c";
		levelHashes[5] = "da0be83c521aadbef0e9d0f041bbdf2ef4c6db06";
		levelHashes[6] = "64e3a6d8617681fa90be242327197ddafa71dce0";
		levelHashes[7] = "9dc1db7fd3b48d35a95c71057fad26882ebc0d9b";
	}
	
	/**
	 * Loads a level and returns to view
	 */
	public String getLevel(int level) {
		return levels[level];
	}
	
	/**
	 * Returns the level number from a given hash string
	 */
	public int getLevelByHash(String hash) {
		for(int i= 0 ; i<numberOfLevels; i++){
			if(levelHashes[i].equals(hash)){
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * Gets the level hash, used to identify the level
	 */
	public String getLevelHash(int level) {
		return levelHashes[level];
	}
	
	/**
	 * Returns all the available levels from story mode
	 * @return String[]
	 */
	public String[] getLevels() {
		return levels;
	}
	
}
