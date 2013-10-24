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

    private JMenu menuFileNew;

    public EditorFrame() {
        frame = new JFrame("Editor");

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuFileNew = new JMenu("New");
        menuFile.add(menuFileNew);
        menuFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "create new level!");
            }
        });

        frame.setSize(500, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
