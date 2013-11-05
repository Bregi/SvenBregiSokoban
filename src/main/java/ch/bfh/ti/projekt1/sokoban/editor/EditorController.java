package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.controller.AbstractController;
import ch.bfh.ti.projekt1.sokoban.model.Board;

/**
 * @author svennyffenegger
 * @since 31/10/13 16:25
 */
public class EditorController extends AbstractController {

    public Board getSingleBoard() {
        return (Board) getModel();//TODO
    }

    public LevelEditorView getSingleEditorView() {
        return (LevelEditorView) getView();//TODO
    }

}
