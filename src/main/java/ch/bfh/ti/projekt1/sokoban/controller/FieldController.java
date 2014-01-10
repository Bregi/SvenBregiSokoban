package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.FieldState;

/**
 * 
 * @author svennyffenegger
 * @since 31/10/13 18:49
 */
public class FieldController extends AbstractController {

    /**
     * @param state
     */
    public void fieldStateChanged(FieldState state) {
        setModelProperty(PROPERTY_FIELD_STATE, state);
    }
}
