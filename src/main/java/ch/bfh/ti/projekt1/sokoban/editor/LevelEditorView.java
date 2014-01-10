package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.core.CoreConstants;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.view.AbstractView;

import javax.swing.*;

import java.awt.*;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.beans.PropertyChangeEvent;

/**
 * View implementation for the editor.it contains the board and the elements the
 * user can drag to the board ("toolbox")
 * 
 * @author svennyffenegger
 * @since 27/10/13 19:19
 */
public class LevelEditorView extends JPanel implements AbstractView,
		DragGestureListener {
	private static final long serialVersionUID = 1L;

	//for the "toolbox" on the right side
	private JPanel elementsPanel;
	
	//all the elements of the board
	private JPanel levelPanel;

	public LevelEditorView(int width, int height) {
		GridLayout gridLayout = new GridLayout(height, width);

		levelPanel = new JPanel(gridLayout);

		setLayout(new FlowLayout());
		add(levelPanel);
		Integer size = new Integer(
				CoreConstants.getProperty("game.element.size"));

		levelPanel.setPreferredSize(new Dimension(width * size, height * size));

		elementsPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(elementsPanel, BoxLayout.Y_AXIS);
		elementsPanel.setLayout(boxLayout);

		elementsPanel.add(new DraggableElementSource(FieldState.WALL));
		elementsPanel.add(new DraggableElementSource(FieldState.PLAYER));
		elementsPanel.add(new DraggableElementSource(FieldState.GOAL));
		elementsPanel.add(new DraggableElementSource(FieldState.EMPTY));
		elementsPanel.add(new DraggableElementSource(FieldState.DIAMOND));

		elementsPanel.setPreferredSize(new Dimension(size, 5 * size));
		add(elementsPanel);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}

	/**
	 * Adds an element to this view
	 * 
	 * @param elementDestination
	 */
	public void addElement(DraggableElementDestination elementDestination) {
		levelPanel.add(elementDestination);
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
	}
}
