package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.model.Element;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.IOException;

/**
 * @author svennyffenegger
 * @since 27/10/13 19:55
 */
public class DraggableElement implements DragGestureListener, DragSourceListener, DropTargetListener {
    private Element element;
    private DragSource dragSource;

    public DraggableElement(Element element) {
        this.element = element;
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(element, DnDConstants.ACTION_COPY_OR_MOVE, this);
        new DropTarget(element, this);
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        Transferable transferable = new StringSelection(element.getImageUrl());
        dragSource.startDrag(dge, DragSource.DefaultCopyDrop, transferable, this);

    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        System.out.println("dropped!");

        try {

            Transferable transferable = dtde.getTransferable();

            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                String dragContents = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                dtde.getDropTargetContext().dropComplete(true);

                element.addImage(dragContents);
                element.repaint();
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
