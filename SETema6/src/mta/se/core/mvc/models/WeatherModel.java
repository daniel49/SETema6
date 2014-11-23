package mta.se.core.mvc.models;


import mta.se.core.mvc.interfaces.IModelListener;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class WeatherModel {
    // Constants
    public static final int INITIAL_VALUE = 0;

    // Member variables defining the weather report
    private float mTemperature;
    private float mWindSpeed;
    private int mHumidity;
    
    private List<IModelListener> mListeners;

    /**
     * Constructor
     */
    public WeatherModel() {
    	mTemperature = INITIAL_VALUE;
    	mWindSpeed = INITIAL_VALUE;
    	mHumidity = INITIAL_VALUE;
    }

    /**
     * Set the weather report temperature value.
     *
     * @param value New value for the temperature.
     */
    public void setTemperatureValue(float value) {

    		mTemperature = value;
    		notifyListeners();
    }
    /**
     * Set the weather report wind speed value.
     *
     * @param value New value for the wind speed.
     */
    public void setWindSpeedValue(float value) {

			mWindSpeed = value;
			notifyListeners();
    }
    /**
     * Set the weather report humidity value.
     *
     * @param value New value for the humidity.
     */
    public void setHumidityValue(int value) {

			mHumidity = value;
			notifyListeners();
    }

    /**
     * Return temperature value.
     */
    public String getTemperatureValue() {
    	
        	return Float.toString(mTemperature);
    }
    /**
     * Return wind speed value.
     */
    public String getWindSpeedValue() {
    	
    	return Float.toString(mWindSpeed);
    }
    /**
     * Return humidity value.
     */
    public String getHumidityValue() {
    	
    	return Integer.toString(mHumidity);
    }

    /**
     * Adds the view listener to the list
     *
     * @param listener The model event listener
     */
    public void addModelListener(IModelListener listener) {
        if (mListeners == null) {
            mListeners = new ArrayList<IModelListener>();
        }

        mListeners.add(listener);
    }

    /**
     * Notifies the views listeners of the changed state (value)
     */
    private void notifyListeners() {
        if (mListeners != null && !mListeners.isEmpty()) {
            for (IModelListener listener : mListeners)
                listener.onUpdate();
        }
    }
    
}
