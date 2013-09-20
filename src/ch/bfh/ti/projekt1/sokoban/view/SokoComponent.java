package ch.bfh.ti.projekt1.sokoban.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author svennyffenegger
 * @since 20.09.13 16:01
 */
public class SokoComponent extends JComponent {

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawString("Test", 100,100);

        graphics.drawLine(0,0,200,200);
    }
}
