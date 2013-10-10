package ch.bfh.ti.projekt1.sokoban.view2;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

import ch.bfh.ti.projekt1.sokoban.controller2.BoardController;

public class BoardView extends JPanel implements KeyListener, AbstractView{

	private BoardController controller;
		
	public BoardView(BoardController controller) {
		this.controller = controller;
		
		setBackground(Color.BLUE);
		setBounds(0, 0, 800, 800);
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void modelPropertyChange(PropertyChangeEvent evt) {
		//Model zum board hat geändert, jetzt muss etwas am GUI geändert werden
		System.out.println("modelChanged!");
	}

	public void keyTyped(KeyEvent e) {
		controller.keyTyped(e);
	}

	public void keyPressed(KeyEvent e) {
		controller.keyTyped(e);
	}

	public void keyReleased(KeyEvent e) {
		
	}
}
