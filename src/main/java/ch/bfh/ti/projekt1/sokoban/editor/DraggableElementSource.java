package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.core.CoreConstants;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.view.element.Element;

import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

/**
 * Class that can be dragged (used in editor)
 * 
 * @author svennyffenegger
 * @since 31/10/13 20:15
 */
public class DraggableElementSource extends Element implements
		DragGestureListener {
	private static final long serialVersionUID = 1L;

	private DragSource source;
	private FieldState state;
	private static Integer size = new Integer(
			CoreConstants.getProperty("game.element.size"));

	/**
	 * 
	 * @param state
	 */
	public DraggableElementSource(FieldState state) {
		this(state, new Dimension(size, size));
	}

	/**
	 * 
	 * @param state
	 * @param preferredDimension
	 */
	public DraggableElementSource(FieldState state, Dimension preferredDimension) {
		addImage(state);
		source = new DragSource();
		source.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_MOVE, this);
		setPreferredSize(preferredDimension);
		this.state = state;
	}

	/**
	 * When user wants to drag, then initialize dragging
	 */
	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		Transferable transferable = new LevelEditorTransferable(state);
		source.startDrag(dge, DragSource.DefaultCopyDrop, transferable,
				new DragSourceAdapter() {
				});
	}

}
