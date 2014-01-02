package ch.bfh.ti.projekt1.sokoban;

import org.apache.log4j.BasicConfigurator;

import ch.bfh.ti.projekt1.sokoban.view.GameWindowView;

public class SokobanApp {
	public static void main(String[] args) {
		BasicConfigurator.configure();

		new GameWindowView();
	}
}
