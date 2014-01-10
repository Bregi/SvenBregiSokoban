package ch.bfh.ti.projekt1.sokoban.model;

import ch.bfh.ti.projekt1.sokoban.controller.AbstractController;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:32
 *        <p/>
 *        A single field.
 */
public class Field extends AbstractModel {
    private FieldState state;

    public Field(FieldState fieldState) {
        this.state = fieldState;
    }

    /**
     * @return FieldState
     */
    public FieldState getState() {
        return state;
    }

    /**
     * Sets a new state to a field 
     * @param state
     */
    public void setState(FieldState state) {
        FieldState oldState = this.state;
        this.state = state;
        // If it is a new value, notify the view
        firePropertyChange(AbstractController.PROPERTY_FIELD_STATE, oldState, this.state);
    }
}
