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
     * @return
     */
    public FieldState getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(FieldState state) {
        FieldState oldState = this.state;
        this.state = state;
        firePropertyChange(AbstractController.PROPERTY_FIELD_STATE, oldState, this.state);
    }
}
