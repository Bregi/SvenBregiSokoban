package ch.bfh.ti.projekt1.sokoban.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Stores the PlayerName
 * @author marcoberger
 * @since 29/11/13 14:19
 */
public class PlayerName {

    private JDialog dialog;
    private JPanel content;
    private JTextField name;
    private String playerName;

    /**
     * @param owner
     * @return String
     */
    public String showDimensionDialog(Frame owner) {
    	
    	playerName = null;
        dialog = new JDialog(owner);

        content = new JPanel(new GridLayout(4, 2));

        JLabel labelName = new JLabel("Spielername");

        name = new JTextField();

        JButton buttonConfirm = new JButton("Ok");
        buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    playerName = name.getText();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(dialog, "Keine g�ltigen Eingaben");
                }

                dialog.dispose();


            }
        });

        JButton buttonCancel = new JButton("Abbrechen");
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        content.add(labelName);
        content.add(name);


        content.add(buttonConfirm);
        content.add(buttonCancel);

        dialog.setContentPane(content);

        dialog.setSize(200, 100);

        dialog.setModal(true);
        dialog.setVisible(true);
        dialog.dispose();

        return playerName;
    }

}
