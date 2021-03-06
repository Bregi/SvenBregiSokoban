package ch.bfh.ti.projekt1.sokoban.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import ch.bfh.ti.projekt1.sokoban.core.CoreConstants;
import ch.bfh.ti.projekt1.sokoban.core.EditorService;
import ch.bfh.ti.projekt1.sokoban.core.LevelMisconfigurationException;
import ch.bfh.ti.projekt1.sokoban.model.Board;

/**
 * Main Class for the editor. Contains the menu and the panel with all the elements (LevelEditorView).
 * 
 * @author svennyffenegger
 * @since 24/10/13 14:29
 */
public class SokobanEditor {
	private static final Logger LOG = Logger.getLogger(SokobanEditor.class);

	private JFrame frame;

	private JMenuBar menuBar;

	private JMenu menuFile;

	private JMenuItem menuFileNew;

	private JMenuItem menuFileSave;

	private JMenuItem menuFileLoad;

	private EditorController controller;

	private EditorService editorService = EditorService.getInstance();

	public SokobanEditor() {
		frame = new JFrame("Editor");

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuFile = new JMenu("Datei");
		menuBar.add(menuFile);

		/**
		 * File New
		 */
		menuFileNew = new JMenuItem("Neu");
		menuFile.add(menuFileNew);
		menuFileNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LevelDimensionDialog dialog = new LevelDimensionDialog();
				Dimension dim = dialog.showDimensionDialog(frame);

				if (dim != null) {
					controller = editorService.getNewLevel(dim.width,
							dim.height);
					frame.setContentPane((LevelEditorView) controller.getView());
					frame.getContentPane().revalidate();
				}

			}
		});

		/**
		 * File save
		 */
		menuFileSave = new JMenuItem("Speichern");
		menuFile.add(menuFileSave);
		menuFileSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Board board = (Board) controller.getModel();
				board.setLevelName(JOptionPane.showInputDialog(
						"Name des Levels:", board.getLevelName()));

				try {
					editorService.saveLevel(board);
					JOptionPane.showMessageDialog(frame,
							"Level wurde gespeichert.");
				} catch (LevelMisconfigurationException e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage());
					LOG.error(e1.getMessage());
				}
			}
		});

		/**
		 * File load
		 */
		menuFileLoad = new JMenuItem("Lade");
		menuFile.add(menuFileLoad);
		menuFileLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(CoreConstants.getProperty("editor.basepath"));
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
						"xml files (*.xml)", "xml");
				fc.setFileFilter(xmlfilter);
				int returnVal = fc.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						controller = editorService.getLevel(file);

						frame.setContentPane((LevelEditorView) controller
								.getView());

						frame.getContentPane().revalidate();
						frame.getContentPane().repaint();
					} catch (LevelMisconfigurationException ex) {
						JOptionPane.showMessageDialog(frame, ex.getMessage());
					}
				}
			}
		});

		Integer width = new Integer(
				CoreConstants.getProperty("editor.window.width"));
		Integer height = new Integer(
				CoreConstants.getProperty("editor.window.height"));

		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}

	public JFrame getFrame() {
		return frame;
	}
}
