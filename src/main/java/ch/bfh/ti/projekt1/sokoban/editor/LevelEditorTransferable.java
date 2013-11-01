package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.model.FieldState;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * @author svennyffenegger
 * @since 31/10/13 17:03
 */
public class LevelEditorTransferable implements Transferable {
    public static final DataFlavor FIELD_STATE_DATA_FLAVOR = new DataFlavor(FieldState.class, "State");
    private FieldState state;

    public LevelEditorTransferable(FieldState state) {
        this.state = state;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{FIELD_STATE_DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(FIELD_STATE_DATA_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return state;
    }
}
