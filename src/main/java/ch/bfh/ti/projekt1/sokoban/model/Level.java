package ch.bfh.ti.projekt1.sokoban.model;

import java.util.ArrayList;

public class Level {
	private String[] levels;
	
	public Level(){
		
		//TODO: SET ALL THE LEVEL AVAILABLE
		levels = new String[10];
		levels[0] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml";
		levels[1] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level2.xml";
		levels[2] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level3.xml";
		levels[3] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level4.xml";
		levels[4] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level5.xml";
		levels[5] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level6.xml";
		levels[6] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level7.xml";
		levels[7] = "src/test/resources/ch/bfh/ti/projekt1/sokoban/level8.xml";
	}
	/*f
	 * Loads a level and returns to view
	 */
	public String getLevel(int level) {
		return levels[level];
	}
	
	public String[] getLevels() {
		return levels;
	}
	
}
