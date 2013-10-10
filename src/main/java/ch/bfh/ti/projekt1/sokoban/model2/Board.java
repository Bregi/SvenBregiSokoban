package ch.bfh.ti.projekt1.sokoban.model2;


import ch.bfh.ti.projekt1.sokoban.controller2.AbstractController;

public class Board extends AbstractModel {

	private Field position;

	public Field getPosition() {
		return position;
	}

	public void setPosition(Field position) {
		Field oldPosition = this.position;
		this.position = position;
		firePropertyChange(AbstractController.PROPERTY_POSITION, oldPosition,
				this.position);
	}

	public void setNextField(Direction direction) {
		System.out.println("Try goto:" + direction.name());

		switch (direction) {
		case DOWN:
			if (position.getLower() != null) {
				setPosition(position.getLower());
				break;
			}
		case UP:
			if (position.getUpper() != null) {
				setPosition(position.getUpper());
				break;
			}
		case LEFT:
			if (position.getLeft() != null) {
				setPosition(position.getLeft());
				break;
			}
		case RIGHT:
			if (position.getRight() != null) {
				setPosition(position.getRight());
				break;
			}
		default:
			System.out.println("Not possible to go there!");
			break;
		}
	}
}
