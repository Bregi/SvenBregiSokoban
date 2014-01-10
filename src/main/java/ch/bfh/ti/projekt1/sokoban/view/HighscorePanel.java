package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.Dimension;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ch.bfh.ti.projekt1.sokoban.core.CoreConstants;

/**
 * The panel that shows the highscores of the current player
 * @author svennyffenegger 
 * @since 01.01.2012
 */
public class HighscorePanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private Map<String, String> levelNameMap;
	private Map<String, Integer> levelScoreMap;
	
	private JTable table;
	private String[] header;
	private String[][] data;
	
	private JScrollPane scrollPane;
	
	/**
	 * @param levelNameMap
	 * @param levelScoreMap
	 */
	public HighscorePanel(Map<String, String> levelNameMap, Map<String, Integer> levelScoreMap) {
		this.levelNameMap = levelNameMap;
		this.levelScoreMap = levelScoreMap;
		
		header = new String[]{"Level Name", "Score"};
		data = new String[this.levelNameMap.size()][2];
		int count = 0;
		
		ValueComparator comparator = new ValueComparator(levelNameMap);
		TreeMap<String, String> sortedLevelNameMap = new TreeMap<>(comparator);
		sortedLevelNameMap.putAll(levelNameMap);

		
		
		for (Entry<String, String> entry : sortedLevelNameMap.entrySet()) {
			Integer score = this.levelScoreMap.get(entry.getKey());
			data[count][1] = score == null ? "no score" : score.toString();
			data[count][0] = entry.getValue();
			count++;
		}
		table = new JTable(data, header);
		table.setEnabled(false);
		scrollPane = new JScrollPane(table);
		
		table.setFillsViewportHeight(true);
		add(scrollPane);
		int width = new Integer(CoreConstants.getProperty("game.highscore.width"));
		int height = new Integer(CoreConstants.getProperty("game.highscore.height"));
		setPreferredSize(new Dimension(width, height));
		
	}
	
	class ValueComparator implements Comparator<String> {

	    Map<String, String> base;
	    /**
	     * @param base
	     */
	    public ValueComparator(Map<String, String> base) {
	        this.base = base;
	    }

	    public int compare(String a, String b) {
	    	return base.get(a).compareTo(base.get(b));
	    }
	}
}
