package ch.bfh.ti.projekt1.sokoban.view;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

/**
 * @author svennyffenegger
 * @since 11.10.13 16:12
 *        <p/>
 *        Implementation of the board view
 */
public class BoardView extends JPanel implements KeyListener, AbstractView {

    private BoardController controller;
    private Position playerPosition;

    public BoardView(BoardController controller, Position playerPosition) {
        this.controller = controller;
        this.playerPosition = playerPosition;
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
    }

    /**
     * Gets called when the model has changed
     *
     * @param evt
     */
    public void modelPropertyChange(PropertyChangeEvent evt) {
        // we have a new position, the player was moved
        if (evt.getNewValue() instanceof Position) {
            playerPosition = (Position) evt.getNewValue();

            //repaint the board and the parent
            repaint();
            getParent().repaint();
        }
    }

    public void keyTyped(KeyEvent e) {
        controller.keyTyped(e);
    }

    public void keyPressed(KeyEvent e) {
        controller.keyTyped(e);
    }

    public void keyReleased(KeyEvent e) {

    }

    /**
     * Repaints the board
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        //setSize(dimension.getWidth(), dimension.getHeight());
        if (playerPosition != null) {
            g.drawString(playerPosition.toString(), 10, 10);

        }
    }
}
