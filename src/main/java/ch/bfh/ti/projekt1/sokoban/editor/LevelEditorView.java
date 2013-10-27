package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author svennyffenegger
 * @since 27/10/13 19:19
 */
public class LevelEditorView extends JPanel {

    public LevelEditorView(int width, int height) {
        setBackground(Color.RED);

        GridLayout gridLayout = new GridLayout(height, width);

        JPanel levelPanel = new JPanel(gridLayout);
        levelPanel.setBackground(Color.YELLOW);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                DraggableElement element = new DraggableElement(new Empty());
                levelPanel.add(element.getElement());
            }
        }

        setLayout(new FlowLayout());
        add(levelPanel);
        levelPanel.setPreferredSize(new Dimension(width * 40, height * 40));

        JPanel elementsPanel = new JPanel();
        elementsPanel.setBackground(Color.blue);
        BoxLayout boxLayout = new BoxLayout(elementsPanel, BoxLayout.Y_AXIS);
        elementsPanel.setLayout(boxLayout);

        DraggableElement diamond = new DraggableElement(new Diamond());
        DraggableElement floor = new DraggableElement(new Floor());
        DraggableElement wall = new DraggableElement(new Wall());
        DraggableElement empty = new DraggableElement(new Empty());
        DraggableElement finish = new DraggableElement(new Finish());
        DraggableElement start = new DraggableElement(new Start());

        elementsPanel.add(diamond.getElement());
        elementsPanel.add(floor.getElement());
        elementsPanel.add(wall.getElement());
        elementsPanel.add(empty.getElement());
        elementsPanel.add(finish.getElement());
        elementsPanel.add(start.getElement());
        elementsPanel.setPreferredSize(new Dimension(40, 6 * 40));
        add(elementsPanel);
    }
}
