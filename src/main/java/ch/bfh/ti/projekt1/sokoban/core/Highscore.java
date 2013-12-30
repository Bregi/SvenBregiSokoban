package ch.bfh.ti.projekt1.sokoban.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Highscore {

	private static final Logger LOG = Logger.getLogger(Highscore.class);
	private static final Highscore INSTANCE = new Highscore();
	private static String basepath = CoreConstants.getProperty("game.basepath");
	private static String highscore = CoreConstants
			.getProperty("game.folder.highscore");
	private static String filename = CoreConstants
			.getProperty("game.file.highscore");

	private Highscore() {

	}

	public static Highscore getInstance() {
		return INSTANCE;
	}

	public Map<String, Integer> getHighscoreForPlayer(String player) {
		Map<String, Integer> highscoreMap = new HashMap<>();

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(basepath + "/" + player + "/"
					+ highscore + "/" + filename));
			for (Entry<Object, Object> entry : prop.entrySet()) {
				highscoreMap.put(entry.getKey().toString(), new Integer(entry
						.getValue().toString()));
			}
		} catch (FileNotFoundException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}

		return highscoreMap;
	}

	public void addHighscore(String player, String levelUuid, Integer score) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(basepath + "/" + player + "/"
					+ highscore + "/" + filename));
			properties.setProperty(levelUuid, score.toString());
			properties.store(new FileOutputStream(basepath + "/" + player + "/"
					+ highscore + "/" + filename), null);
		} catch (FileNotFoundException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
	}

}
