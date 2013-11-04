package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.view.element.Element;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

/**
 * @author svennyffenegger
 * @since 31/10/13 20:15
 */
public class DraggableElementSource extends Element implements DragGestureListener {

    private DragSource source;
    private FieldState state;

    public DraggableElementSource(FieldState state) {
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
