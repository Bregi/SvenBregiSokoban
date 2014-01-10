package ch.bfh.ti.projekt1.sokoban.core;


/**
 * Checked Exception for the misconfiguration of a level
 * 
 * @author svennyffenegger
 * @since 27/12/13 19:43
 */
public class LevelMisconfigurationException extends Exception {
	private static final long serialVersionUID = 1L;

	public LevelMisconfigurationException(String msg) {
		super(msg);
	}
	
}
