package ch.bfh.ti.projekt1.sokoban.view2;

import ch.bfh.ti.projekt1.sokoban.controller2.BoardController;
import ch.bfh.ti.projekt1.sokoban.controller2.BoardDimension;
import ch.bfh.ti.projekt1.sokoban.controller2.BoardService;
import ch.bfh.ti.projekt1.sokoban.controller2.BoardServiceImpl;
import ch.bfh.ti.projekt1.sokoban.model2.Field;
import ch.bfh.ti.projekt1.sokoban.model2.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;

public class BoardView extends JPanel implements KeyListener, AbstractView {

    private BoardController controller;
    private BoardService boardService = new BoardServiceImpl();
    private BoardDimension dimension;
    private Position playerPosition;

    public BoardView(BoardController controller, Position playerPosition) {
        this.controller = controller;
        this.playerPosition = playerPosition;
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
    }

    public void modelPropertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof Field) {
            Field field = (Field) evt.getNewValue();
            if (dimension == null) {
                dimension = boardService.getBoardDimension(field);
            }
        }

        if (evt.getNewValue() instanceof Position) {
            playerPosition = (Position) evt.getNewValue();
        }

        repaint();
        getParent().repaint();
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

    @Override
    protected void paintComponent(Graphics g) {
        //setSize(dimension.getWidth(), dimension.getHeight());
        if (playerPosition != null) {
            g.drawString(playerPosition.toString(), 10, 10);

        }
    }
}
