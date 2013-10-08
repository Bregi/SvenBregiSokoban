package ch.bfh.ti.projekt1.sokoban.model2;

public class Board extends AbstractModel{
	private Field position;

	public Field getPosition() {
		return position;
	}

	public void setPosition(Field position) {
		Field oldPosition = this.position;
		this.position = position;
		firePropertyChange("position", oldPosition, this.position);
	}
}
