package ch.bfh.ti.projekt1.sokoban.view;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.Position;
import ch.bfh.ti.projekt1.sokoban.view.element.*;

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
    private Board board;
    private Field[][] grid;
    private boolean levelLoaded;

    public BoardView(Board board, BoardController controller,
                     Position playerPosition, String levelName) {
        this.controller = controller;
        this.levelName = levelName;
        this.board = board;
        this.grid = board.getGrid();
        this.playerPosition = playerPosition;
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);
        drawLevel();
    }

    /**
     * Gets called when the model has changed
     *
     * @param evt
     */
    public void modelPropertyChange(final PropertyChangeEvent evt) {

        // if the level hasn't been loaded, load it
        //if (levelLoaded == false) {
        //    levelLoaded = true;
        //    this.drawLevel();
        //}
        // we have a new position, the player was moved
        if (evt.getNewValue() instanceof Position) {


            // repaint the board and the parent

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Position oldPlayerPosition = playerPosition;

                    playerPosition = (Position) evt.getNewValue();
                    movePlayer(p, f, playerPosition.getX(), playerPosition.getY(),
                            oldPlayerPosition.getX(), oldPlayerPosition.getY());

                }
            });

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

    public void movePlayer(Player p, Floor f, int newX, int newY, int oldX,
                           int oldY) {
        p.setBounds(newX * 40, newY * 40, 40, 40);
        f.setBounds(oldX * 40, oldY * 40, 40, 40);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                repaint();
            }
        });

        System.out.println("Player moved to:" + p.getBounds().toString());
    }

    /**
     * if a diamond is in the way move that one too
     *
     * @param p
     * @param f
     * @param d
     * @param newX
     * @param newY
     * @param oldX
     * @param oldY
     */
    public void movePlayerWithDiamond(Player p, Floor f, Diamond d, int newX, int newY, int oldX,
                                      int oldY) {
        p.setBounds(newX * 40, newY * 40, 40, 40);
        f.setBounds(oldX * 40, oldY * 40, 40, 40);
        d.setBounds((2 * newX - oldX) * 40, (2 * newY - oldY) * 40, 40, 40);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                repaint();
            }
        });


        System.out.println("Player moved to:" + p.getBounds().toString());
    }

    /**
     * takes the predefined level file and draws the elements on screen
     */
    public void drawLevel() {


        this.p = new Player();
        addComponentToBoard(this.p, playerPosition.getX(),
                playerPosition.getY());
        this.f = new Floor();
        addComponentToBoard(this.f, playerPosition.getX(),
                playerPosition.getY());

        for (int i = 0; i < grid.length; i++) {
            for (int n = 0; n < grid[i].length; n++) {
                switch (grid[i][n].getState()) {
                    case WALL:
                        addComponentToBoard(new Wall(), i, n);
                        break;
                    case GOAL:
                        addComponentToBoard(new Goal(), i, n);
                        break;
                    case EMPTY:
                        addComponentToBoard(new Floor(), i, n);
                        break;
                    case DIAMOND:
                        addComponentToBoard(new Diamond(), i, n);
                        break;
                    case COMPLETED:
                        addComponentToBoard(new Finish(), i, n);
                        break;
                    default:
                        break;
                }
            }
        }

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
