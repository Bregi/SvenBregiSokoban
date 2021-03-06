package ch.bfh.ti.projekt1.sokoban.model;

import java.util.ArrayList;

import ch.bfh.ti.projekt1.sokoban.core.CoreConstants;

/**
 * The Level service
 * 
 * @author marcoberger
 * @since 24/12/13 14:29
 */
public class Level {
	private String[] levels;
	private int numberOfLevels;
	private String[] levelHashes;

	public Level() {

		numberOfLevels = new Integer(
				CoreConstants.getProperty("game.story.levels"));
		levels = new String[numberOfLevels];
		String levelPath = CoreConstants.getProperty("game.levelspath");
		levels[1] = levelPath + "level1.xml";
		levels[2] = levelPath + "level2.xml";
		levels[3] = levelPath + "level3.xml";
		levels[4] = levelPath + "level4.xml";
		levels[5] = levelPath + "level5.xml";
		levels[6] = levelPath + "level6.xml";
		levels[7] = levelPath + "level7.xml";
		levels[8] = levelPath + "level8.xml";

		levelHashes = new String[numberOfLevels];
		levelHashes[1] = "4d913082805e39e321efa920a381b3d6473706c9";
		levelHashes[2] = "0ea71bdc345437a270c2007209fa12e40a9dd7d7";
		levelHashes[3] = "cc8b17d91422be1227eb016b999b67e4e9bcbae6";
		levelHashes[4] = "6505e9a18dc1fc446e5a8786c327b2272a03c027";
		levelHashes[5] = "86abf2c5591ee5f0b8c7eb607c8ab2846f82f66c";
		levelHashes[6] = "da0be83c521aadbef0e9d0f041bbdf2ef4c6db06";
		levelHashes[7] = "64e3a6d8617681fa90be242327197ddafa71dce0";
		levelHashes[8] = "9dc1db7fd3b48d35a95c71057fad26882ebc0d9b";
	}

	/**
	 * Loads a level and returns to view
	 * 
	 * @param level
	 * @return
	 */
	public String getLevel(int level) {
		return levels[level];
	}

	/**
	 * Returns just the level String
	 * 
	 * @param level
	 * @return
	 */
	public String getLevelOnly(int level) {
		return "level" + level;
	}

	/**
	 * Returns the level number from a given hash string
	 */
	public int getLevelByHash(String hash) {
		for (int i = 1; i < numberOfLevels; i++) {
			if (levelHashes[i].equals(hash)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the level hash, used to identify the level
	 * 
	 * @param level
	 * @return String
	 */
	public String getLevelHash(int level) {
		return levelHashes[level];
	}

	/**
	 * Returns all the available levels from story mode
	 * 
	 * @return String[]
	 */
	public String[] getLevels() {
		return levels;
	}

}
