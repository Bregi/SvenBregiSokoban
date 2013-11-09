package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.AbstractModel;
import ch.bfh.ti.projekt1.sokoban.view.AbstractView;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * This abstract controller adds the functionality of adding multiple views
 * and models
 *
 * @author svennyffenegger
 * @since 04/11/13 20:37
 */
public abstract class AbstractMultiController extends AbstractController {

    protected ArrayList<AbstractView> registeredViews;
    protected ArrayList<AbstractModel> registeredModels;

    public AbstractMultiController() {
        registeredViews = new ArrayList<AbstractView>();
        registeredModels = new ArrayList<AbstractModel>();
    }

    public void addModel(AbstractModel model) {
        registeredModels.add(model);
        model.addPropertyChangeListener(this);
    }

    public void removeModel(AbstractModel model) {
        registeredModels.remove(model);
        model.removePropertyChangeListener(this);
    }

    public void addView(AbstractView view) {
        registeredViews.add(view);
    }

    public void removeView(AbstractView view) {
        registeredViews.remove(view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        for (AbstractView view : registeredViews) {
            view.modelPropertyChange(evt);
        }
    }
}
