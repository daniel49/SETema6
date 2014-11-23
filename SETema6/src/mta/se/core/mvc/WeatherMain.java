package mta.se.core.mvc;

import mta.se.core.mvc.controllers.WeatherController;
import mta.se.core.mvc.models.WeatherModel;
import mta.se.core.mvc.views.WeatherView;

/**
 * This is the Main class that represents the entry point to our program
 * 
 * @author Daniel
 * </p> Created on 21/11/2014
 *
 */
public class WeatherMain {

	public static void main(String[] args) {
		// Instantiate the MVC elements
		WeatherModel model = new WeatherModel();
		WeatherView view = new WeatherView();
        WeatherController controller = new WeatherController();

        // Attach the view to the model
        model.addModelListener(view);

        // Tell the view about the model and the controller
        view.addModel(model);
        view.addController(controller);

        // Tell the controller about the model and the view
        controller.addModel(model);
        controller.addView(view);

		// Display the view
		view.setVisible(true);
	}

}
