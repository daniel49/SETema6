/**
 * Created by Daniel on 21/11/2014
 */
package mta.se.core.mvc.views;

import mta.se.core.mvc.interfaces.IController;
import mta.se.core.mvc.interfaces.IModelListener;
import mta.se.core.mvc.interfaces.IView;
import mta.se.core.mvc.models.WeatherModel;
//import mta.se.core.mvc.utils.CalculateAction;

import javax.swing.*;
import java.awt.*;

public class WeatherView extends JFrame implements IModelListener, IView {
    private static final long serialVersionUID = -5758555454500685115L;

    // View Components

    private JTextField mTemperature = new JTextField(4);
    private JTextField mWindSpeed = new JTextField(4);
    private JTextField mHumidity = new JTextField(4);
    private JButton mGenerateBtn = new JButton("Generate");

    private IController mWeatherController;

    private WeatherModel mModel;

    /**
     * Constructor
     */
    public WeatherView() {
        // Initialize components
    	mTemperature.setEditable(false);
    	mWindSpeed.setEditable(false);
    	mHumidity.setEditable(false);
    	


        // Layout the components.
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Click to generate the weather report   "));
        content.add(mGenerateBtn);
        content.add(new JLabel("   Temperature"));
        content.add(mTemperature);
        content.add(new JLabel("oC   "));
        content.add(new JLabel("   Wind speed"));
        content.add(mWindSpeed);
        content.add(new JLabel("km/h   "));
        content.add(new JLabel("   Humidity"));
        content.add(mHumidity);
        content.add(new JLabel("%   "));
        
        // Finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("WeatherReport");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Sets the view's reference to the model - Only get operations allowed
     *
     * @param model The weather model
     */
    public void addModel(WeatherModel model) {
        mModel = model;
        mTemperature.setText(model.getTemperatureValue());
        mWindSpeed.setText(model.getWindSpeedValue());
        mHumidity.setText(model.getHumidityValue());
    }

    /**
     * Sets the view's event listener - the controller - so that the changes made by the user in the view, can be reflected in the model
     *
     * @param controller The controller (event listener)
     */
    public void addController(IController controller) {
    	mGenerateBtn.setActionCommand(IController.ACTION_GENERATE);
    	mGenerateBtn.addActionListener(controller);


    }

    /**
     * Show error reports
     */
    @Override
    public void onMessage(boolean isError, String message) {
        if (isError) {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "WeatherReport MVC", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Get the temperature,wind speed and humidity values from the model and set them on the view
     */
    @Override
    public void onUpdate() {
    	mTemperature.setText(mModel.getTemperatureValue());
    	mWindSpeed.setText(mModel.getWindSpeedValue());
    	mHumidity.setText(mModel.getHumidityValue());
    }
}