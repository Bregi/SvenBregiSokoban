package ch.bfh.ti.projekt1.sokoban.view;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.model.Element;
import ch.bfh.ti.projekt1.sokoban.model.Floor;
import ch.bfh.ti.projekt1.sokoban.model.Player;
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
    private String levelName;
    private Player p;
    private Floor f;
    private boolean levelLoaded;

    public BoardView(BoardController controller, Position playerPosition,
                     String levelName) {
        this.controller = controller;
        this.levelName = levelName;
        this.playerPosition = playerPosition;
        setBackground(Color.BLACK);
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);
    }

    /**
     * Gets called when the model has changed
     *
     * @param evt
     */
    public void modelPropertyChange(PropertyChangeEvent evt) {

        // if the level hasn't been loaded, load it
        if (levelLoaded == false) {
            levelLoaded = true;
            this.drawLevel();
        }
        Position oldPlayerPosition = playerPosition;
        // we have a new position, the player was moved
        if (evt.getNewValue() instanceof Position) {
            playerPosition = (Position) evt.getNewValue();
            movePlayer(p, f, playerPosition.getX(), playerPosition.getY(), oldPlayerPosition.getX(), oldPlayerPosition.getY());
            // repaint the board and the parent
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

    public void movePlayer(Player p, Floor f, int newX, int newY, int oldX, int oldY) {
        //addComponentToBoard(this.f, oldX, oldY);
        //addComponentToBoard(this.p, newX, newY);
        this.p.setBounds(newX * 40, newY * 40, 40, 40);
        this.f.setBounds(oldX * 40, oldY * 40, 40, 40);
        this.p.revalidate();
        this.p.repaint();
        revalidate();
        repaint();

        System.out.println("Player moved to:" + p.getBounds().toString());
    }

    public void drawLevel() {
        this.f = new Floor();
        this.f.initialize();
        addComponentToBoard(this.f, playerPosition.getX(), playerPosition.getY());

        this.p = new Player();
        this.p.initialize();
        addComponentToBoard(this.p, playerPosition.getX(), playerPosition.getY());
    }

    /*
     * Takes an element and adds it to the board at a given position
     */
    public void addComponentToBoard(Element element, int x, int y) {
        element.setBounds(x * 40, y * 40, 40, 40);
        this.add(element, null);
    }

    /**
     * Repaints the board
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        // setSize(dimension.getWidth(), dimension.getHeight());
        if (playerPosition != null) {
            g.setColor(Color.DARK_GRAY);
            g.drawString(playerPosition.toString(), 10, 10);
        }
        if (levelName != null) {
            g.setColor(Color.CYAN);
            g.drawString(levelName, 10, 20);
        }
    }
}
