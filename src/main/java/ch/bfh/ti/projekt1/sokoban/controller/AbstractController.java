package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.AbstractModel;
import ch.bfh.ti.projekt1.sokoban.view.AbstractView;
import org.apache.log4j.Logger;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

/**
 * @author svennyffenegger
 * @since 11.10.13 13:32
 *        <p/>
 *        Abstract class for controller
 */
public abstract class AbstractController implements PropertyChangeListener {

    /**
     * The properties that can change
     */
    public static final String PROPERTY_POSITION = "Position";
    public static final String PROPERTY_NEXT_FIELD = "NextField";
    public static final String PROPERTY_FIELD = "Field";
    public static final String PROPERTY_FIELD_STATE = "State";
    public static final String PROPERTY_LEVEL_NAME = "LevelName";
    private static final Logger LOG = Logger.getLogger(AbstractController.class);
    private AbstractView view;
    private AbstractModel model;

    public AbstractController() {

    }

    public AbstractController(AbstractModel model, AbstractView view) {
        this.view = view;
        this.model = model;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        view.modelPropertyChange(evt);
    }

    public AbstractView getView() {
        return view;
    }

    public void setView(AbstractView view) {
        this.view = view;
    }

    public AbstractModel getModel() {
        return model;
    }

    public void setModel(AbstractModel model) {
        this.model = model;
        this.model.addPropertyChangeListener(this);
    }

    /**
     * This is a convenience method that subclasses can call upon to fire
     * property changes back to the models. This method uses reflection to
     * inspect each of the model classes to determine whether it is the owner of
     * the property in question. If it isn't, a NoSuchMethodException is thrown,
     * which the method ignores.
     *
     * @param propertyName = The name of the property.
     * @param newValue     = An object that represents the new value of the property.
     */
    protected void setModelProperty(String propertyName, Object newValue) {

        try {
            Method method = model.getClass().getMethod(
                    "set" + propertyName,
                    new Class[]{newValue.getClass()}
            );
            method.invoke(model, newValue);

        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

}