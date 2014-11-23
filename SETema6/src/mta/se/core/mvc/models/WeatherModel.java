package mta.se.core.mvc.models;


import mta.se.core.mvc.interfaces.IModelListener;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class WeatherModel {
    // Constants
    public static final String INITIAL_VALUE = "0";

    // Member variables defining the weather report
    private BigInteger mTemperature;
    private BigInteger mWindSpeed;
    private BigInteger mHumidity;
    
    private List<IModelListener> mListeners;

    /**
     * Constructor
     */
    public WeatherModel() {
    	mTemperature = new BigInteger(INITIAL_VALUE);
    	mWindSpeed = new BigInteger(INITIAL_VALUE);
    	mHumidity = new BigInteger(INITIAL_VALUE);
    }

    /**
     * Set the weather report temperature value.
     *
     * @param value New value for the temperature.
     */
    public void setTemperatureValue(String value) {

    		mTemperature = new BigInteger(value);
    		notifyListeners();
    }
    /**
     * Set the weather report wind speed value.
     *
     * @param value New value for the wind speed.
     */
    public void setWindSpeedValue(String value) {

			mWindSpeed = new BigInteger(value);
			notifyListeners();
    }
    /**
     * Set the weather report humidity value.
     *
     * @param value New value for the humidity.
     */
    public void setHumidityValue(String value) {

			mHumidity = new BigInteger(value);
			notifyListeners();
    }

    /**
     * Return temperature value.
     */
    public String getTemperatureValue() {
    	
        	return mTemperature.toString();
    }
    /**
     * Return wind speed value.
     */
    public String getWindSpeedValue() {
    	
    	return mWindSpeed.toString();
    }
    /**
     * Return humidity value.
     */
    public String getHumidityValue() {
    	
    	return mHumidity.toString();
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
