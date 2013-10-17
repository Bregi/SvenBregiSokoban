package ch.bfh.ti.projekt1.sokoban.model2;

public class Field extends AbstractModel {
    private FieldState state;

    public Field(FieldState fieldState) {
        this(fieldState, null, null, null, null);
    }

    public Field(FieldState fieldState, Field upper, Field lower, Field left, Field right) {
        this.state = fieldState;
    }

    public FieldState getState() {
        return state;
    }

    public void setState(FieldState state) {
        FieldState oldState = this.state;
        this.state = state;
        firePropertyChange("state", oldState, this.state);
    }
}
