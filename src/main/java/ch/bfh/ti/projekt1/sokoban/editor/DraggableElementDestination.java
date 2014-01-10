package ch.bfh.ti.projekt1.sokoban.editor;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import org.apache.log4j.Logger;

import ch.bfh.ti.projekt1.sokoban.controller.FieldController;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.view.element.Element;

/**
 * Elements which allows content to be dropped upon (used in editor)
 * 
 * @author svennyffenegger
 * @since 31/10/13 20:14
 */
public class DraggableElementDestination extends Element implements
		DropTargetListener {
	private static final Logger LOG = Logger
			.getLogger(DraggableElementDestination.class);

	private static final long serialVersionUID = 1L;
	private DropTarget dropTarget;

	/**
	 * 
	 * @param controller of the field
	 */
	public DraggableElementDestination(FieldController controller) {
		this(controller, FieldState.EMPTY);
	}

	/**
	 * 
	 * @param controller
	 * @param fieldState
	 */
	public DraggableElementDestination(FieldController controller,
			FieldState fieldState) {
		super(controller);
		dropTarget = new DropTarget(this, this);
		addImage(fieldState);
	}

	/**
	 * Gets called when a content is dropped on this element
	 */
	@Override
	public void drop(DropTargetDropEvent dtde) {
		try {
			Transferable transferable = dtde.getTransferable();

			// we check if the type of the dropped element is appropriate
			if (transferable
					.isDataFlavorSupported(LevelEditorTransferable.FIELD_STATE_DATA_FLAVOR)) {
				
				// accept and process the drop
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
				FieldState state = (FieldState) transferable
						.getTransferData(LevelEditorTransferable.FIELD_STATE_DATA_FLAVOR);
				DraggableElementDestination.this.controller
						.fieldStateChanged(state);
				dtde.getDropTargetContext().dropComplete(true);
			} else {
				dtde.rejectDrop();
			}
		} catch (IOException e) {
			LOG.error(e);
			dtde.rejectDrop();
		} catch (UnsupportedFlavorException e) {
			LOG.error(e);
			dtde.rejectDrop();
		}
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
	}

}
