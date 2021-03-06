package ch.bfh.ti.projekt1.sokoban.editor;

import javax.swing.*;

import ch.bfh.ti.projekt1.sokoban.core.CoreConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog for requesting bounds of new level
 * 
 * Contains:
 * -width textfield
 * -height textfield
 * -buttons ok/cancel
 * 
 * @author svennyffenegger
 * @since 28/10/13 10:19
 */
public class LevelDimensionDialog {

	private JDialog dialog;
	private Dimension dimension;
	private JPanel content;
	private JTextField textWidth;
	private JTextField textHeight;

	public Dimension showDimensionDialog(Frame owner) {
		dialog = new JDialog(owner);

		content = new JPanel(new GridLayout(4, 2));

		JLabel labelWidth = new JLabel("Breite");
		JLabel labelHeight = new JLabel("Höhe");

		textWidth = new JTextField();
		textHeight = new JTextField();

		JButton buttonConfirm = new JButton("Ok");
		buttonConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int height = Integer.parseInt(textHeight.getText());
					int width = Integer.parseInt(textWidth.getText());
					dimension = new Dimension(width, height);
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(dialog,
							"Keine gültigen Eingaben");
				}
				dialog.dispose();
			}
		});

		JButton buttonCancel = new JButton("Abbrechen");
		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dimension = null;
				dialog.dispose();
			}
		});

		content.add(labelWidth);
		content.add(textWidth);

		content.add(labelHeight);
		content.add(textHeight);

		content.add(buttonConfirm);
		content.add(buttonCancel);

		dialog.setContentPane(content);

		Integer width = new Integer(
				CoreConstants.getProperty("editor.dimension.dialog.width"));
		Integer height = new Integer(
				CoreConstants.getProperty("editor.dimension.dialog.height"));

		dialog.setSize(width, height);

		dialog.setModal(true);
		dialog.setVisible(true);
		dialog.dispose();

		return dimension;
	}

}
