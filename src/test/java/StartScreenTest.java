import org.apache.log4j.BasicConfigurator;

import ch.bfh.ti.projekt1.sokoban.view.GameWindowView;


/**
 * @author marcoberger
 * @since 24/10/13 11:38
 */
public class StartScreenTest {

    public static void main(String[] args) {

        BasicConfigurator.configure();


        new GameWindowView();

    }
}
