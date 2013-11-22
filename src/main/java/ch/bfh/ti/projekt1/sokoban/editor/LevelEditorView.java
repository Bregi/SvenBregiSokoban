package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.view.AbstractView;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * @author svennyffenegger
 * @since 27/10/13 19:19
 */
public class LevelEditorView extends JPanel implements AbstractView {

    private EditorController controller;

    private JPanel elementsPanel;
    private JPanel levelPanel;

    public LevelEditorView(EditorController controller, int width, int height) {
        this.controller = controller;

        GridLayout gridLayout = new GridLayout(height, width);

        levelPanel = new JPanel(gridLayout);


        setLayout(new FlowLayout());
        add(levelPanel);
        levelPanel.setPreferredSize(new Dimension(width * 40, height * 40));

        elementsPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(elementsPanel, BoxLayout.Y_AXIS);
        elementsPanel.setLayout(boxLayout);

        for (FieldState state : FieldState.values()) {
            DraggableElementSource elementSource = new DraggableElementSource(state);
            elementSource.setPreferredSize(new Dimension(40, 40));

            elementsPanel.add(elementSource);
        }

        elementsPanel.setPreferredSize(new Dimension(40, 6 * 40));
        add(elementsPanel);
        elementsPanel.setBackground(Color.RED);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addElement(DraggableElementDestination elementDestination) {
        levelPanel.add(elementDestination);
    }
}
