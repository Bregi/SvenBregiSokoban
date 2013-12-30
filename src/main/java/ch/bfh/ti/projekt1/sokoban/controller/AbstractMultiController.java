package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.AbstractModel;
import ch.bfh.ti.projekt1.sokoban.view.AbstractView;

import java.beans.PropertyChangeEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * This abstract controller adds the functionality of adding multiple views and
 * models
 * 
 * @author svennyffenegger
 * @since 04/11/13 20:37
 */
public abstract class AbstractMultiController extends AbstractController {

	protected ArrayList<AbstractView> registeredViews;
	protected ArrayList<AbstractModel> registeredModels;
	private static final Logger LOG = Logger
			.getLogger(AbstractController.class);

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

	public AbstractView getView(Class<? extends AbstractView> clazz) {
		for (AbstractView view : registeredViews) {
			if (clazz.isInstance(view)) {
				return view;
			}
		}
		return null;
	}
	
	public AbstractModel getModel(Class<? extends AbstractModel> clazz) {
		for (AbstractModel model : registeredModels) {
			if (clazz.isInstance(model)) {
				return model;
			}
		}
		return null;
	}

	@Override
	public void setView(AbstractView view) {
		addView(view);
	}

	@Override
	public void setModel(AbstractModel model) {
		addModel(model);
	}

	protected void setModelProperty(String propertyName, Object newValue) {
		for (AbstractModel model : registeredModels) {
			try {
				Method method = model.getClass().getMethod(
						"set" + propertyName,
						new Class[] { newValue.getClass() });
				method.invoke(model, newValue);

			} catch (Exception ex) {
				LOG.error(ex);
			}
		}
	}

}
