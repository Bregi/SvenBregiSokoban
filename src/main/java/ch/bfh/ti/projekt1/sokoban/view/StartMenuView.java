package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class StartMenuView extends JMenuBar implements ActionListener {

	//spiel
	JMenu menuFile = new JMenu("Spiel");
	JMenuItem itmNew = new JMenuItem("Neues Spiel starten");
	JMenuItem itmReload = new JMenuItem("Level neu starten");
	JMenuItem itmSave = new JMenuItem("Spiel speichern");
	JMenuItem itmLoad = new JMenuItem("Spiel laden");
	JMenuItem itmClose = new JMenuItem("Spiel beenden");

	//optionen
	JMenu menuEdit = new JMenu("Optionen");
	JMenuItem itmStatistics = new JMenuItem("Levelstatistik anzeigen");
	JMenuItem itmBest = new JMenuItem("Bestergebnisse anzeigen");
	
	//Leveleditor
	JMenu menuLevelEditor = new JMenu("Level Editor");
	JMenuItem itmLevelEditorStart = new JMenuItem("Level Editor Starten");

	public StartMenuView() {

		//Spiel
		menuFile.add(itmNew);
		menuFile.addSeparator();

		menuFile.add(itmReload);
		menuFile.addSeparator();

		menuFile.add(itmSave);
		menuFile.addSeparator();

		menuFile.add(itmLoad);
		menuFile.addSeparator();
		
		menuFile.add(itmClose);
		
		//optionen
		menuEdit.add(itmStatistics);
		menuEdit.addSeparator();
		menuEdit.add(itmBest);

		//leveleditor
		menuLevelEditor.add(itmLevelEditorStart);

		add(menuFile);
		add(menuEdit);
		add(menuLevelEditor);

		//spiel
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
		
		//optionen
		itmStatistics.setActionCommand("undo");
		itmStatistics.addActionListener(this);
		itmBest.setActionCommand("cut");
		itmBest.addActionListener(this);
		
		//leveleditor
		itmLevelEditorStart.setActionCommand("leveleditorstart");
		itmLevelEditorStart.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("new")) {
			JOptionPane.showMessageDialog(null,"Neues Spiel");
		} else if (e.getActionCommand().equals("close")) {
			JOptionPane.showMessageDialog(null,"beenden");
		} else if (e.getActionCommand().equals("reload")) {
			JOptionPane.showMessageDialog(null,"relo..");
		} else if (e.getActionCommand().equals("load")) {	
			JOptionPane.showMessageDialog(null,"load");
		}else if (e.getActionCommand().equals("save")) {	
			JOptionPane.showMessageDialog(null,"save");
		}else if (e.getActionCommand().equals("leveleditorstart")) {	
			JOptionPane.showMessageDialog(null,"starte editor..");
		}
	}
}