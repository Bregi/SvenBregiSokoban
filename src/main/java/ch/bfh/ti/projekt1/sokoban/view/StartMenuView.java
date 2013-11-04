package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ch.bfh.ti.projekt1.sokoban.controller.GameController;
import ch.bfh.ti.projekt1.sokoban.editor.SokobanEditor;

public class StartMenuView extends JMenuBar implements ActionListener {

	// spiel
	JMenu menuFile = new JMenu("Spiel");
	JMenuItem itmNew = new JMenuItem("Neues Spiel starten");
	JMenuItem itmReload = new JMenuItem("Level neu starten");
	JMenuItem itmSave = new JMenuItem("Spiel speichern");
	JMenuItem itmLoad = new JMenuItem("Spiel laden");
	JMenuItem itmClose = new JMenuItem("Spiel beenden");

	// optionen
	JMenu menuEdit = new JMenu("Optionen");
	JMenuItem itmStatistics = new JMenuItem("Levelstatistik anzeigen");
	JMenuItem itmBest = new JMenuItem("Bestergebnisse anzeigen");

	// Leveleditor
	JMenu menuLevelEditor = new JMenu("Level Editor");
	JMenuItem itmLevelEditorStart = new JMenuItem("Level Editor Starten");

	public StartMenuView() {

		// Spiel
		menuFile.add(itmNew);
		menuFile.addSeparator();

		menuFile.add(itmReload);
		menuFile.addSeparator();

		menuFile.add(itmSave);
		menuFile.addSeparator();

		menuFile.add(itmLoad);
		menuFile.addSeparator();

		menuFile.add(itmClose);

		// optionen
		menuEdit.add(itmStatistics);
		menuEdit.addSeparator();
		menuEdit.add(itmBest);

		// leveleditor
		menuLevelEditor.add(itmLevelEditorStart);

		add(menuFile);
		add(menuEdit);
		add(menuLevelEditor);

		// spiel
		itmNew.setActionCommand("new");
		itmNew.addActionListener(this);
		itmReload.setActionCommand("reload");
		itmReload.addActionListener(this);
		itmSave.setActionCommand("save");
		itmSave.addActionListener(this);
		itmLoad.setActionCommand("load");
		itmLoad.addActionListener(this);
		itmClose.setActionCommand("close");
		itmClose.addActionListener(this);

		// optionen
		itmStatistics.setActionCommand("undo");
		itmStatistics.addActionListener(this);
		itmBest.setActionCommand("cut");
		itmBest.addActionListener(this);

		// leveleditor
		itmLevelEditorStart.setActionCommand("leveleditorstart");
		itmLevelEditorStart.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("new")) {
			Object[] options = { "Ja", "Nein" };
            int response = JOptionPane.showOptionDialog(
                    this,
                    "Bist du sicher, dass du ein neues Spiel starten willst? Ungespeicherter Fortschritt geht dabei verloren. ",
                    "Level neu starten", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options,
                    options[1]);

            if (response == JOptionPane.YES_OPTION) {

                //TODO unschön
                GameController controller = new GameController();

                JFileChooser jFileChooser = new JFileChooser("src/test/resources/ch/bfh/ti/projekt1/sokoban/generated");

                jFileChooser.showOpenDialog(null);


                BoardView view = controller.loadLevel(jFileChooser.getSelectedFile());
                getRootPane().setContentPane(view);
            }
		} else if (e.getActionCommand().equals("close")) {
			Object[] options = { "Ja", "Nein" };
			JOptionPane
					.showOptionDialog(
							this,
							"Bist du sicher, dass du das Spiel beenden willst? Ungespeicherter Fortschritt geht dabei verloren. ",
							"Spiel beenden", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);
		} else if (e.getActionCommand().equals("reload")) {
			Object[] options = { "Ja", "Nein" };
			JOptionPane
					.showOptionDialog(
							this,
							"Bist du sicher, dass du das Level neu laden willst? Ungespeicherter Fortschritt geht dabei verloren. ",
							"Level neu laden", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);
		} else if (e.getActionCommand().equals("load")) {
			JOptionPane.showMessageDialog(null, "load");
		} else if (e.getActionCommand().equals("save")) {
		} else if (e.getActionCommand().equals("leveleditorstart")) {
            new SokobanEditor();
		}
	}
}