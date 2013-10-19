package ch.bfh.ti.projekt1.sokoban.view;

import java.beans.PropertyChangeEvent;

/**
 * @author svennyffenegger
 * @since 11.10.13 16:12
 *        <p/>
 *        Interface for every view
 */
public interface AbstractView {

    /**
     * every view will get notified with this method if the model has changed in the background
     *
     * @param evt
     */
    void modelPropertyChange(PropertyChangeEvent evt);
}
