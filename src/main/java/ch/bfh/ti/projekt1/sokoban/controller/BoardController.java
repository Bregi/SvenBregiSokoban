package ch.bfh.ti.projekt1.sokoban.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import ch.bfh.ti.projekt1.sokoban.model.Direction;
import ch.bfh.ti.projekt1.sokoban.model.Position;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:32
 */
public class BoardController extends AbstractMultiController {

	/**
	 * Key has been typed. Player has to be moved if allowed
	 */
	public void keyTyped(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			setModelProperty(PROPERTY_NEXT_FIELD, Direction.DOWN);
		} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
			setModelProperty(PROPERTY_NEXT_FIELD, Direction.UP);
		} else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			setModelProperty(PROPERTY_NEXT_FIELD, Direction.LEFT);
		} else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			setModelProperty(PROPERTY_NEXT_FIELD, Direction.RIGHT);
		}
	}

	/**
	 * A key has been pressed (and not released yet). TODO: Move player with a
	 * timer?
	 */
	public void keyPressed(KeyEvent evt) {

	}
	
	/**
	 * A mouse has been clicked
	 * timer?
	 */
    public void mousePressed(MouseEvent e) {
		setModelProperty(PROPERTY_WALK, new Position((int)java.lang.Math.floor(e.getX()/40), (int)java.lang.Math.floor(e.getY()/40)));
    }
}
