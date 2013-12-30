package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HighscorePanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private Map<String, String> levelNameMap;
	private Map<String, Integer> levelScoreMap;
	
	private JTable table;
	private String[] header;
	private String[][] data;
	
	private JScrollPane scrollPane;
	
	public HighscorePanel(Map<String, String> levelNameMap, Map<String, Integer> levelScoreMap) {
		this.levelNameMap = levelNameMap;
		this.levelScoreMap = levelScoreMap;
		
		header = new String[]{"Level Name", "Score"};
		data = new String[this.levelNameMap.size()][2];
		int count = 0;
		for (Entry<String, String> entry : levelNameMap.entrySet()) {
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
		setPreferredSize(new Dimension(500, 500));
		
	}
}
