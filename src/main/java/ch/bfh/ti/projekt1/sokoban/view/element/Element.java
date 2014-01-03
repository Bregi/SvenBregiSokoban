package ch.bfh.ti.projekt1.sokoban.view.element;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import ch.bfh.ti.projekt1.sokoban.controller.FieldController;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.view.AbstractView;

/**
 * TODO: Element nur mit Konstruktor, der FieldState voraussetzt?
 */
public abstract class Element extends JComponent implements AbstractView {
	private static final long serialVersionUID = 1L;
	public static final String DIAMOND_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/diamond.jpg";
	public static final String EMPTY_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/empty.jpg";
	public static final String FINISH_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/diamonfinish.jpg";
	public static final String FLOOR_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/floor.jpg";
	public static final String PLAYER_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/player.jpg";
	public static final String START_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/start.jpg";
	public static final String WALL_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/wall.jpg";
	public static final String GOAL_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/finish.jpg";
	public static final String PLAYER_GOAL_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/player.jpg";
	protected FieldController controller;
	private Image backgroundImage;
	private int x, y;

	public Element(FieldController controller) {
		this.controller = controller;
	}

	public Element() {
		this(null);
	}

	public void addImage(String imageUrl) {
		try {
			this.backgroundImage = ImageIO.read(new File(imageUrl));
		} catch (IOException ex) {
			System.out.println(ex.toString());
			System.out.println("Image file was not found " + backgroundImage);
			this.backgroundImage = null;
		}
	}

	public void addImage(FieldState state) {
		switch (state) {
		case EMPTY:
			addImage(EMPTY_PATH);
			break;
		case WALL:
			addImage(WALL_PATH);
			break;
		case DIAMOND:
			addImage(DIAMOND_PATH);
			break;
		case PLAYER:
			addImage(PLAYER_PATH);
			break;
		case COMPLETED:
			addImage(FINISH_PATH);
			break;
		case GOAL:
			addImage(GOAL_PATH);
			break;
		default:
			System.out
					.println("check the code: a fieldstate is not implemented!");
		}
		repaint();
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		this.setSize(40, 40);
		repaint();
	}

	public void moveElement(int x, int y) {
		this.setLocation(x, y);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (backgroundImage != null) {
			g2.drawImage(backgroundImage, x, y, null);
		}

	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		if (evt.getNewValue() instanceof FieldState) {
			addImage((FieldState) evt.getNewValue());
		}
	}
}
