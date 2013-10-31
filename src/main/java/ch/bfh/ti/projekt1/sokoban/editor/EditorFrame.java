package ch.bfh.ti.projekt1.sokoban.editor;

import ch.bfh.ti.projekt1.sokoban.model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author svennyffenegger
 * @since 24/10/13 14:29
 */
public class EditorFrame {
    private JFrame frame;

    private JMenuBar menuBar;

    private JMenu menuFile;

    private JMenuItem menuFileNew;

    private JMenuItem menuFileSave;

    public EditorFrame() {
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
                    Board board = new Board(dim.width, dim.height);
                    controller.addModel(board);

                    LevelEditorView editorView = new LevelEditorView(controller, dim.width, dim.height);
                    controller.addView(editorView);

                    frame.setContentPane(editorView);
                    frame.getContentPane().revalidate();
                }

            }
        });

        menuFileSave = new JMenuItem("Save");
        menuFile.add(menuFileSave);
        menuFileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save here!");
            }
        });

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
