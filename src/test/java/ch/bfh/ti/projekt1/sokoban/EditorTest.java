package ch.bfh.ti.projekt1.sokoban;

import ch.bfh.ti.projekt1.sokoban.editor.SokobanEditor;
import org.apache.log4j.BasicConfigurator;

/**
 * @author svennyffenegger
 * @since 24/10/13 11:38
 */
public class EditorTest {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new SokobanEditor();

    }
}
