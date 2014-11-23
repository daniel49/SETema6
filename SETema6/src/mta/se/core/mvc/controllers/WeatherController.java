/**
 * Created by Daniel on 21/11/2014
 */
package mta.se.core.mvc.controllers;


import mta.se.core.mvc.interfaces.IController;
import mta.se.core.mvc.interfaces.IView;
import mta.se.core.mvc.models.WeatherModel;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import mta.se.core.mvc.webservice.ParseJSONObject;
import mta.se.core.mvc.webservice.WeatherServiceHTTPRequest;

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
        	// Weather report for Bucharest
            String weatherData = WeatherServiceHTTPRequest.getWeatherData("lat=44&lon=26");
            try {
            	// Create a new JSON object based on the data received from the service
            	JSONObject jsonObject = new JSONObject(weatherData);
            	// Get main object that contains temp,humidity,pressure,etc from the json object
            	JSONObject mainJsonObj = ParseJSONObject.getObject("main", jsonObject);
				float temperature=ParseJSONObject.getFloat("temp", mainJsonObj);
				// Transform from Kelvin to Celsius
				temperature = (float) (temperature - 273.15);
				BigDecimal decValue = new BigDecimal(temperature);
				// Trunc de float value to only 2 decimals
				decValue = decValue.setScale(2,RoundingMode.HALF_EVEN);
				mModel.setTemperatureValue(decValue.floatValue());
				
				int humidity=ParseJSONObject.getInt("humidity", mainJsonObj);
				mModel.setHumidityValue(humidity);
				
				JSONObject windJsonObj = ParseJSONObject.getObject("wind", jsonObject);
				float windSpeed = ParseJSONObject.getFloat("speed", windJsonObj);
				// Transform from m/s to km/h
				windSpeed =(float) (windSpeed * 3.6);
				decValue = new BigDecimal(windSpeed);
				decValue = decValue.setScale(2,RoundingMode.HALF_EVEN);
				mModel.setWindSpeedValue(decValue.floatValue());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
