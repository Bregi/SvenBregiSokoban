package ch.bfh.ti.projekt1.sokoban.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author svennyffenegger
 * @since 28/10/13 10:19
 */
public class LevelDimensionDialog {

    public static Dimension showDimensionDialog(Frame owner) {
        final JDialog dialog = new JDialog(owner);

        JPanel content = new JPanel(new GridLayout(4, 2));

        JLabel labelWidth = new JLabel("Breite");
        JLabel labelHeight = new JLabel("Höhe");

        JTextField textWidth = new JTextField();
        JTextField textHeight = new JTextField();

        JButton buttonConfirm = new JButton("Ok");
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });

        content.add(labelWidth);
        content.add(textWidth);

        content.add(labelHeight);
        content.add(textHeight);

        content.add(buttonConfirm);

        dialog.setContentPane(content);

        System.out.println("Vor");

        dialog.setVisible(true);
        System.out.println("Nach");

        return new Dimension(3, 3);
    }

}
