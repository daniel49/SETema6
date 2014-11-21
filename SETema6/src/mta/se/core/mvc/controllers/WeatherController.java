/**
 * Created by Daniel on 21/11/2014
 */
package mta.se.core.mvc.controllers;


import mta.se.core.mvc.interfaces.IController;
import mta.se.core.mvc.interfaces.IView;
import mta.se.core.mvc.model.WeatherModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherController implements IController {
    // The Controller needs to interact with both the Model and View.
    private WeatherModel mModel;

    // The list of views that listen for updates
    private List<IView> mViews;

    /**
     * WeatherController constructor
     */
    public WeatherController() {
    	
    }
    
    /**
     * Perform the action indicated by the event
     * </p>This function is called when the generate button is pressed
     * @param event the event that triggers the function call
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals(ACTION_GENERATE)) {
            // Make the operation
                JButton source = (JButton) event.getSource();
                if (source != null) {
                    makeOperation();
                } else {
                    notifyViews(true, "Invalid operation data");
                }
        }
    }

    /**
     * Adds a view reference in order to interact with it
     *
     * @param view The view from the controller will receive events and send messages
     */
    public void addView(IView view) {
        if (mViews == null) {
            mViews = new ArrayList<IView>();
        }

        mViews.add(view);
    }

    /**
     * Adds a reference to the model, so it can update it
     *
     * @param model The data model reference
     */
    public void addModel(WeatherModel model) {
        mModel = model;
    }

    /**
     * Notifies the views when a message must be displayed
     *
     * @param isError {@code true} if the message is an error, {@code false} otherwise
     * @param message The string to be displayed
     */
    private void notifyViews(boolean isError, String message) {
        if (mViews != null && !mViews.isEmpty()) {
            for (IView view : mViews) {
                view.onMessage(isError, message);
            }
        }
    }

    /**
     * Generate the values for the weather parameters:temperature, wind speed and humidity
     */
    private void makeOperation() {
        if (mModel != null) {
            BigInteger currentValue = new BigInteger("0");
            // Update the model
            //mModel.setValue(currentValue.multiply(new BigInteger(operand)).toString());
            currentValue = BigInteger.valueOf(this.randInt(-45, 45));
            mModel.setTemperatureValue(currentValue.toString());
            
            currentValue = BigInteger.valueOf(this.randInt(0, 120));
            mModel.setWindSpeedValue(currentValue.toString());
            
            currentValue = BigInteger.valueOf(this.randInt(0, 100));
            mModel.setHumidityValue(currentValue.toString());
        }
    }
    
    /**
     * The function used to generate a random number in a range
     * @param min the left side of the range
     * @param max the right side of the range
     * @return the random number
     */
    private int randInt(int min, int max) {
    	
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    
}
