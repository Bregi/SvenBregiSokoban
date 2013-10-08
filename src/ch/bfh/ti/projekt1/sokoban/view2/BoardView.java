package ch.bfh.ti.projekt1.sokoban.view2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

import ch.bfh.ti.projekt1.sokoban.controller2.BoardController;

public class BoardView extends AbstractView implements KeyListener{

	private BoardController controller;
	
	public BoardView(BoardController controller) {
		this.controller = controller;
	}
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		//Model zum board hat geändert, jetzt muss etwas am GUI geändert werden
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		controller.moveLeft();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
