package com.vraenchike.Exception;

/**
 * Created by Alexeev on 03-May-15.
 */
public class PhotoNotFoundException extends Exception {
    public PhotoNotFoundException() {
    }

    public PhotoNotFoundException(String message) {
        super(message);
    }

    public PhotoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoNotFoundException(Throwable cause) {
        super(cause);
    }

    public PhotoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
