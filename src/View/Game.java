package src.View;
import javax.swing.JFrame;
import javax.swing.JLabel;

//import statements
//Check if window closes automatically. Otherwise add suitable code
public class Game extends JFrame {

	public static void main(String args[]) {
		new Game();
	}
	Game() {
		JLabel jlbHelloWorld = new JLabel("Sokobaaaaaaaaan");
		add(jlbHelloWorld);
		this.setSize(600, 300);
		// pack();
		setVisible(true);
	}
}
