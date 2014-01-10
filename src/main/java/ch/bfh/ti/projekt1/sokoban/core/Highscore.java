package ch.bfh.ti.projekt1.sokoban.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Stores and loads Highscores for the player
 * 
 * @author svennyffenegger
 * @since 27/12/13 19:43
 */
public class Highscore {

	private static final Logger LOG = Logger.getLogger(Highscore.class);
	private static Highscore instance;
	
	// root path of the game
	private static String basepath = CoreConstants.getProperty("game.basepath");
	
	// highscore folder name inside profile
	private static String highscore = CoreConstants
			.getProperty("game.folder.highscore");
	
	// highscore file name inside profile
	private static String filename = CoreConstants
			.getProperty("game.file.highscore");
	
	// path separator for the running platform
	private char DEL = File.separatorChar;

	private Highscore() {

	}

	/**
	 * Accessor to the singelton instance
	 * 
	 * @return instance of this singleton
	 */
	public static synchronized Highscore getInstance() {
		if (instance == null) {
			instance = new Highscore();
		}
		return instance;
	}

	/**
	 * loads the highscores for the given player
	 * 
	 * @param player profile to laod from
	 * @return Map with Key (Level UUID) and Value (Score) for the palyer
	 */
	public Map<String, Integer> getHighscoreForPlayer(String player) {
		Map<String, Integer> highscoreMap = new HashMap<>();

		Properties prop = new Properties();
		try {
			// loads the properties file
			prop.load(new FileInputStream(basepath + DEL + player + DEL
					+ highscore + DEL + filename));
			
			// add each property to the map
			for (Entry<Object, Object> entry : prop.entrySet()) {
				highscoreMap.put(entry.getKey().toString(), new Integer(entry
						.getValue().toString()));
			}
			LOG.debug("loading of highscore for player " + player +" successful");
		} catch (FileNotFoundException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
		return highscoreMap;
	}

	/**
	 * Adds a new score of a player for a certain level
	 * 
	 * @param player
	 * @param levelUuid
	 * @param score
	 */
	public void addHighscore(String player, String levelUuid, Integer score) {
		Properties properties = new Properties();
		try {
			// load the score file from the profile folder
			properties.load(new FileInputStream(basepath + DEL + player + DEL
					+ highscore + DEL + filename));
			
			// check if there is already a score and if the new score is lower than the existing
			String existingProp = properties.getProperty(levelUuid);
			if (existingProp != null && existingProp.isEmpty() == false) {
				Integer oldScore = new Integer(existingProp);
				// we have already a score that better or equal to the new one: quit!
				if (oldScore <= score) {
					return;
				}
			}
			
			// add the new score
			properties.setProperty(levelUuid, score.toString());
			
			// save the properties file
			properties.store(new FileOutputStream(basepath + DEL + player + DEL
					+ highscore + DEL + filename), null);
			LOG.debug("Score of " + score + " added for player " + player
					+ " and level " + levelUuid);
		} catch (FileNotFoundException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
	}

}
