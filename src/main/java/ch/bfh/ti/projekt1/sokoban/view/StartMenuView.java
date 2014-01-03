package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ch.bfh.ti.projekt1.sokoban.core.LevelService;

public class StartMenuView extends JMenuBar {

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
    private LevelService levelService = LevelService.getInstance();

    public StartMenuView(ActionListener actionListener) {

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
        itmNew.addActionListener(actionListener);
        itmReload.setActionCommand("reload");
        itmReload.addActionListener(actionListener);
        itmSave.setActionCommand("save");
        itmSave.addActionListener(actionListener);
        itmLoad.setActionCommand("load");
        itmLoad.addActionListener(actionListener);
        itmClose.setActionCommand("close");
        itmClose.addActionListener(actionListener);

        // optionen
        itmStatistics.setActionCommand("undo");
        itmStatistics.addActionListener(actionListener);
        itmBest.setActionCommand("cut");
        itmBest.addActionListener(actionListener);

        // leveleditor
        itmLevelEditorStart.setActionCommand("leveleditorstart");
        itmLevelEditorStart.addActionListener(actionListener);
    }
}