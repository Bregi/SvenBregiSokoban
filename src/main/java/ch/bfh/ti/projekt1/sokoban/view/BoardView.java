package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.model.Position;
import ch.bfh.ti.projekt1.sokoban.view.element.Diamond;
import ch.bfh.ti.projekt1.sokoban.view.element.Element;
import ch.bfh.ti.projekt1.sokoban.view.element.Finish;
import ch.bfh.ti.projekt1.sokoban.view.element.Floor;
import ch.bfh.ti.projekt1.sokoban.view.element.Player;
import ch.bfh.ti.projekt1.sokoban.view.element.Wall;

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
	private int stepsUsed;
	private Player p;
	private Floor f;
	private Diamond[][] diamonds;
	private Field[][] grid;
	private int numberOfGoals;

	public BoardView(Board board, BoardController controller,
			Position playerPosition, String levelName) {
		this.controller = controller;
		this.stepsUsed = 0;
		this.levelName = levelName;
		this.grid = board.getGrid();
		this.diamonds = new Diamond[grid.length][grid.length];// TODO SET SIZE
																// CORRECT
		this.playerPosition = playerPosition;
		this.numberOfGoals = 0;
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

		// we have a new position, the player was moved
		if (evt.getNewValue() instanceof Position) {
			this.stepsUsed++;

			// repaint the board and the parent
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Position oldPlayerPosition = playerPosition;

					playerPosition = (Position) evt.getNewValue();
					System.out.println(evt.getNewValue().toString());
					movePlayer(playerPosition.getX(),
							playerPosition.getY(), oldPlayerPosition.getX(),
							oldPlayerPosition.getY());

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

	public void movePlayer(int newX, int newY, int oldX,
			int oldY) {
		grid[oldX][oldY].setState(FieldState.EMPTY);
		if (grid[newX][newY].getState() == FieldState.DIAMOND) {
			p.setBounds(newX * 40, newY * 40, 40, 40);
			f.setBounds(oldX * 40, oldY * 40, 40, 40);
			moveDiamond(newX+(newX-oldX), newY+(newY-oldY), newX, newY);
		}else{

			p.setBounds(newX * 40, newY * 40, 40, 40);
			f.setBounds(oldX * 40, oldY * 40, 40, 40);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					repaint();
				}
			});
		}

		//System.out.println("Player moved to:" + p.getBounds().toString());

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
	public void moveDiamond(int newX, int newY,int oldX, int oldY) {
		
		grid[oldX][oldY].setState(FieldState.PLAYER);
		grid[newX][newY].setState(FieldState.DIAMOND);
		String s="";
		for(int i = 0; i<5;i++){
			for(int y=0;y<5;y++){
				s+=(grid[y][i].getState()+ " ");
			}
			s+=("\n");
		}
		System.out.println(s);
		diamonds[newX][newY]=diamonds[oldX][oldY];
		diamonds[oldX][oldY] = null;
		diamonds[newX][newY].setBounds(newX * 40, newY * 40, 40, 40);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				repaint();
			}
		});
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
					Wall w = new Wall();
					addComponentToBoard(w, i, n);
					break;
				case GOAL:
					Finish fi = new Finish();
					numberOfGoals++;
					addComponentToBoard(fi, i, n);
					break;
				case EMPTY:
					Floor f = new Floor();
					addComponentToBoard(f, i, n);
					break;
				case DIAMOND:
					Diamond d = new Diamond();
					diamonds[i][n] = d;
					diamonds[i][n].setBounds(i, n, 40, 40);
					addComponentToBoard(diamonds[i][n], i, n);
					break;
				default:
					break;
				}
			}
		}
	}

	public int getStepsUsed() {
		return this.stepsUsed;
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
