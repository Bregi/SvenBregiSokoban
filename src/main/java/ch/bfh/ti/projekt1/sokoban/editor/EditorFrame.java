package ch.bfh.ti.projekt1.sokoban.editor;

import javax.swing.*;
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
                frame.setContentPane(new LevelEditorView(5, 5));
                frame.getContentPane().revalidate();
            }
        });

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
