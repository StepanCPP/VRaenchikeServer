package com.vraenchike.Exception;

/**
 * Created by Alexeev on 04-May-15.
 */
public class PhotoAlreadyAddedeException extends Exception {
    public PhotoAlreadyAddedeException() {
    }

    public PhotoAlreadyAddedeException(String message) {
        super(message);
    }

    public PhotoAlreadyAddedeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoAlreadyAddedeException(Throwable cause) {
        super(cause);
    }

    public PhotoAlreadyAddedeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
