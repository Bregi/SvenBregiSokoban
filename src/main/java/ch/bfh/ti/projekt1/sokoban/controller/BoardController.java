package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.Direction;

import java.awt.event.KeyEvent;

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
}
