package ch.bfh.ti.projekt1.sokoban.view;

import ch.bfh.ti.projekt1.sokoban.controller.FieldController;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;


/**
 * TODO: Element nur mit Konstruktor, der FieldState voraussetzt?
 */
public abstract class Element extends JComponent implements AbstractView {

    public static final String DIAMOND_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/diamond.jpg";
    public static final String EMPTY_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/empty.jpg";
    public static final String FINISH_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/diamonfinish.jpg";
    public static final String FLOOR_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/floor.jpg";
    public static final String PLAYER_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/player.jpg";
    public static final String START_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/start.jpg";
    public static final String WALL_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/wall.jpg";
    public static final String GOAL_PATH = "src/main/resources/ch/bfh/ti/projekt1/sokoban/img/finish.jpg";


    private String imageUrl;
    private int x, y;

    protected FieldController controller;

    public Element(FieldController controller) {
        this.controller = controller;
    }

    public Element() {
        this(null);
    }

    public void addImage(String imageUrl) {
        this.imageUrl = imageUrl;
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
                System.out.println("check the code: a fieldstate is not implemented!");
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
        String backgroundImage = imageUrl;
        try {
            Image img = ImageIO.read(new File(backgroundImage));
            g2.drawImage(img, x, y, null);
        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("Image file was not found " + backgroundImage);
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof FieldState) {
            addImage((FieldState) evt.getNewValue());
        }
    }
}
