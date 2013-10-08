package ch.bfh.ti.projekt1.sokoban.model2;

public class Field extends AbstractModel {
	private FieldState state;

	private Field upper, lower, left, right;

	public FieldState getState() {
		return state;
	}

	public void setState(FieldState state) {
		FieldState oldState = this.state;
		this.state = state;
		firePropertyChange("state", oldState, this.state);
	}

	public Field getUpper() {
		return upper;
	}

	public void setUpper(Field upper) {
		Field oldField = this.upper;
		this.upper = upper;
		firePropertyChange("upper", oldField, this.upper);
	}

	public Field getLower() {
		return lower;
	}

	public void setLower(Field lower) {
		Field oldField = this.lower;
		this.lower = lower;
		firePropertyChange("lower", oldField, this.lower);
	}

	public Field getLeft() {
		return left;
	}

	public void setLeft(Field left) {
		Field oldField = this.left;
		this.left = left;
		firePropertyChange("left", oldField, this.left);
	}

	public Field getRight() {
		return right;
	}

	public void setRight(Field right) {
		Field oldField = this.right;
		this.right = right;
		firePropertyChange("right", oldField, this.right);
	}

}
