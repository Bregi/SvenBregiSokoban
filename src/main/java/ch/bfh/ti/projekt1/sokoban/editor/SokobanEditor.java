package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.controller.FieldController;
import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author svennyffenegger
 * @since 24/10/13 14:29
 */
public class SokobanEditor {
    private JFrame frame;

    private JMenuBar menuBar;

    private JMenu menuFile;

    private JMenuItem menuFileNew;

    private JMenuItem menuFileSave;

    private Board currentLevel;

    private LevelService levelService = new LevelServiceImpl();

    public SokobanEditor() {
        frame = new JFrame("Editor");

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuFileNew = new JMenuItem("New");
        menuFile.add(menuFileNew);
        menuFileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LevelDimensionDialog dialog = new LevelDimensionDialog();
                Dimension dim = dialog.showDimensionDialog(frame);

                if (dim != null) {
                    EditorController controller = new EditorController();
                    currentLevel = new Board(dim.width, dim.height);

                    controller.addModel(currentLevel);

                    LevelEditorView editorView = new LevelEditorView(controller, dim.width, dim.height);
                    controller.addView(editorView);

                    for (int i = 0; i < dim.width; i++) {
                        for (int j = 0; j < dim.height; j++) {
                            Field field = new Field(FieldState.EMPTY);
                            currentLevel.setField(i, j, field);
                            FieldController fieldController = new FieldController();
                            DraggableElementDestination elementDestination = new DraggableElementDestination(fieldController);

                            fieldController.addView(elementDestination);
                            fieldController.addModel(field);

                            editorView.addElement(elementDestination);
                        }
                    }


                    frame.setContentPane(editorView);
                    frame.getContentPane().revalidate();
                }

            }
        });

        menuFileSave = new JMenuItem("Save");
        menuFile.add(menuFileSave);
        menuFileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelService.saveLevel(currentLevel);
            }
        });

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
