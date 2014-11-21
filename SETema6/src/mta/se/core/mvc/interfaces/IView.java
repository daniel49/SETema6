package mta.se.core.mvc.interfaces;

/**
 * Created by Daniel on 21/11/2014.
 * <p/>
 * All the views should implement this interface in order for the controller to know how to interact
 */
public interface IView {

    /**
     * On message received from controller
     *
     * @param isError {@code true} if the message is an error, {@code false} otherwise
     * @param message The string to be displayed
     */
    public void onMessage(boolean isError, String message);
}
