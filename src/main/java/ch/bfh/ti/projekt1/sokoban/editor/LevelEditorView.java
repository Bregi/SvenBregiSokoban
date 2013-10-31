package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.view.AbstractView;
import ch.bfh.ti.projekt1.sokoban.view.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

/**
 * @author svennyffenegger
 * @since 27/10/13 19:19
 */
public class LevelEditorView extends JPanel implements AbstractView {

    private EditorController controller;

    public LevelEditorView(EditorController controller, int width, int height) {
        this.controller = controller;

        GridLayout gridLayout = new GridLayout(height, width);

        JPanel levelPanel = new JPanel(gridLayout);
        levelPanel.setBackground(Color.YELLOW);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                levelPanel.add(new DraggableElementDestination());
            }
        }

        setLayout(new FlowLayout());
        add(levelPanel);
        levelPanel.setPreferredSize(new Dimension(width * 40, height * 40));

        JPanel elementsPanel = new JPanel();
        elementsPanel.setBackground(Color.blue);
        BoxLayout boxLayout = new BoxLayout(elementsPanel, BoxLayout.Y_AXIS);
        elementsPanel.setLayout(boxLayout);

        for (FieldState state : FieldState.values()) {
            elementsPanel.add(new DraggableElementSource(state));
        }

        elementsPanel.setPreferredSize(new Dimension(40, 6 * 40));
        add(elementsPanel);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    private class DraggableElementSource extends Element implements DragGestureListener {

        private DragSource source;
        private FieldState state;

        private DraggableElementSource(FieldState state) {
            addImage(state);
            source = new DragSource();
            source.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
            this.state = state;
        }

        @Override
        public void dragGestureRecognized(DragGestureEvent dge) {
            Transferable transferable = new LevelEditorTransferable(state);

            //TODO hier Cursor mit Bild ersetzen
            source.startDrag(dge, DragSource.DefaultCopyDrop, transferable, new DragSourceAdapter() {
            });
        }
    }

    private class DraggableElementDestination extends Element implements DropTargetListener {

        private DropTarget dropTarget;

        private DraggableElementDestination() {
            dropTarget = new DropTarget(this, this);
            addImage(FieldState.EMPTY);
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

        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable transferable = dtde.getTransferable();

                if (transferable.isDataFlavorSupported(LevelEditorTransferable.FIELD_STATE_DATA_FLAVOR)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    FieldState state = (FieldState) transferable.getTransferData(LevelEditorTransferable.FIELD_STATE_DATA_FLAVOR);
                    addImage(state);
                    repaint();
                    dtde.getDropTargetContext().dropComplete(true);
                } else {
                    dtde.rejectDrop();
                }
            } catch (IOException e) {
                dtde.rejectDrop();
            } catch (UnsupportedFlavorException e) {
                dtde.rejectDrop();
            }
        }
    }
}
