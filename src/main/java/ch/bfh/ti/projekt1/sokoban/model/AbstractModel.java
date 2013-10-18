package ch.bfh.ti.projekt1.sokoban.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:32
 *        <p/>
 *        Abstract class for all model classes. It enabled the possibility for other classes (listener) to register
 *        themselves on the model (with method addPropertyChangeListener).
 *        <p/>
 *        When the model changed its data it can simply call firePropertyChange. Then all listeners get notified.
 */
public abstract class AbstractModel {
    protected PropertyChangeSupport propertyChangeSupport;

    public AbstractModel() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue,
                                      Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue,
                newValue);
    }
}
