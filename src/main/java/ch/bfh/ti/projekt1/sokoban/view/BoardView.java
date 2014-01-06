package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.Position;
import ch.bfh.ti.projekt1.sokoban.view.element.Diamond;
import ch.bfh.ti.projekt1.sokoban.view.element.Element;
import ch.bfh.ti.projekt1.sokoban.view.element.Finish;
import ch.bfh.ti.projekt1.sokoban.view.element.Floor;
import ch.bfh.ti.projekt1.sokoban.view.element.Goal;
import ch.bfh.ti.projekt1.sokoban.view.element.Player;
import ch.bfh.ti.projekt1.sokoban.view.element.PlayerOnGoal;
import ch.bfh.ti.projekt1.sokoban.view.element.Wall;

/**
 * @author svennyffenegger
 * @since 11.10.13 16:12
 *        <p/>
 *        Implementation of the board view
 */
public class BoardView extends JPanel implements KeyListener, MouseListener, AbstractView {

	private static final long serialVersionUID = 1L;
	private BoardController controller;
    private Position playerPosition;
    private String levelName;
    private Field[][] grid;
    private Element[][] field;
    private boolean levelIsFinished;
    public Board board;

    public BoardView(Board board, BoardController controller,
                     Position playerPosition, String levelName) {
        this.controller = controller;
        this.levelName = levelName;
        this.levelIsFinished = false;
        this.grid = board.getGrid();
        this.board = board;
        this.field = new Element[grid.length][grid[0].length];
        this.playerPosition = playerPosition;
        addMouseListener(this);
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);
        initLevel();
    }

    /**
     * Gets called when the model has changed
     *
     * @param evt
     */
    public void modelPropertyChange(final PropertyChangeEvent evt) {
       
        // we have a new position, the player was moved
        if (evt.getNewValue() instanceof Position) {

            // repaint the board and the parent
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    drawLevel();
                    playerPosition = (Position) evt.getNewValue();
                }
            });

        }
    }
    /**
     * Used for the resize of the window
     * 
     * @return int
     */
    public int getWindowSizeX(){
    	return grid.length*40+16;
    }

    /**
     * Used for the resize of the window
     * 
     * @return int
     */
    public int getWindowSizeY(){
    	return grid[0].length*40+60;
    }
    
    public void keyTyped(KeyEvent e) {
        controller.keyTyped(e);
    }

    public void keyPressed(KeyEvent e) {
        controller.keyTyped(e);
    }

    public void keyReleased(KeyEvent e) {

    }
    
    public void mousePressed(MouseEvent e){
    }

	@Override
	public void mouseClicked(MouseEvent e) {
    	controller.mousePressed(e);
    	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

    /**
     * takes the level file and draws the elements on screen
     */
    public void initLevel() {

        for (int i = 0; i < grid.length; i++) {
            for (int n = 0; n < grid[i].length; n++) {
                switch (grid[i][n].getState()) {
                    case PLAYER:
                        Player p = new Player();
                        this.add(p);
                        field[i][n] = p;
                        break;
                    case WALL:

                        Wall w = new Wall();
                        this.add(w);
                        field[i][n] = w;
                        break;
                    case GOAL:

                        Goal g = new Goal();
                        this.add(g);
                        field[i][n] = g;
                        break;
                    case EMPTY:
                        Floor f = new Floor();
                        this.add(f);
                        field[i][n] = f;
                        break;
                    case DIAMOND:
                        Diamond d = new Diamond();
                        this.add(d);
                        field[i][n] = d;
                        break;
                    case PLAYER_ON_GOAL:
                        PlayerOnGoal pl = new PlayerOnGoal();
                        this.add(pl);
                        field[i][n] = pl;
                        break;
                    case COMPLETED:
                    	Finish finish = new Finish();
                    	finish.setCompleted();
                    	this.add(finish);
                    	field[i][n] = finish;
                    	break;
                    default:
                        break;
                }
            }
        }
        repaint();
    }

    /**
     * takes the level file and draws the elements on screen
     */
    public void drawLevel() {
        boolean levelFinish = true;
        for (int i = 0; i < grid.length; i++) {
            for (int n = 0; n < grid[i].length; n++) {
                switch (grid[i][n].getState()) {
                    case PLAYER:
                        if (!(field[i][n] instanceof Player)) {
                            this.remove(field[i][n]);
                            Player p = new Player();
                            this.add(p);
                            field[i][n] = p;

                        }
                        break;
                    case WALL:

                        if (!(field[i][n] instanceof Wall)) {
                            this.remove(field[i][n]);
                            Wall w = new Wall();
                            this.add(w);
                            field[i][n] = w;
                        }
                        break;
                    case GOAL:
                        levelFinish = false;
                        if (!(field[i][n] instanceof Goal)) {
                            this.remove(field[i][n]);
                            Goal g = new Goal();
                            this.add(g);
                            field[i][n] = g;
                        }
                        break;
                    case EMPTY:
                        if (!(field[i][n] instanceof Floor)) {
                            this.remove(field[i][n]);
                            Floor f = new Floor();
                            this.add(f);
                            field[i][n] = f;
                        }
                        break;
                    case DIAMOND:
                        if (!(field[i][n] instanceof Diamond)) {
                            this.remove(field[i][n]);
                            Diamond d = new Diamond();
                            this.add(d);
                            field[i][n] = d;
                        }
                        break;
                    case COMPLETED:
                        if (!(field[i][n] instanceof Finish)) {
                            this.remove(field[i][n]);
                            Finish f = new Finish();
                            f.setCompleted();
                            this.add(f);
                            field[i][n] = f;
                        }
                        break;
                    case PLAYER_ON_GOAL:
                        if (!(field[i][n] instanceof PlayerOnGoal)) {
                            this.remove(field[i][n]);
                            PlayerOnGoal p = new PlayerOnGoal();
                            this.add(p);
                            field[i][n] = p;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        if (levelFinish == true) {
            this.levelIsFinished = true;
        }
        repaint();
    }

    public boolean getIsLevelFinished() {
        return this.levelIsFinished;
    }

    /**
     * Repaints the board
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {

        //check for finished level
        if (getIsLevelFinished()) {

        }
        for (int i = 0; i < field.length; i++) {

            //TODO
            for (int n = 0; n < field[i].length; n++) {
                if (field[i][n] != null) {
                field[i][n].setBounds(i * 40, n * 40, 40, 40);

                }

            }
        }
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
