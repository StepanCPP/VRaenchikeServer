package com.vraenchike.Exception;

/**
 * Created by Alexeev on 03-May-15.
 */
public class PlaceNotExist extends Exception {
    public PlaceNotExist() {
    }

    public PlaceNotExist(String message) {
        super(message);
    }

    public PlaceNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceNotExist(Throwable cause) {
        super(cause);
    }

    public PlaceNotExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
