package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class HighscorePanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private Map<String, String> levelNameMap;
	private Map<String, Integer> levelScoreMap;
	
	public HighscorePanel(Map<String, String> levelNameMap, Map<String, Integer> levelScoreMap) {
		this.levelNameMap = levelNameMap;
		this.levelScoreMap = levelScoreMap;
	}

	@Override
	public void paint(Graphics g) {
		int offset = 20;
		for (Entry<String, String> entry : levelNameMap.entrySet()) {
			g.setColor(Color.BLACK);
			Integer score = levelScoreMap.get(entry.getKey());
			g.drawString(entry.getValue() + ": " + (score == null ? "no score" : score), 10, offset);
			offset += 20;
		}
	}
	
	
	
}
