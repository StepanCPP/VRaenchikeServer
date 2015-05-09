package com.vraenchike.Exception;

/**
 * Created by Alexeev on 03-May-15.
 */
public class BannedNotFoundException extends Exception {
    public BannedNotFoundException() {
    }

    public BannedNotFoundException(String message) {
        super(message);
    }

    public BannedNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BannedNotFoundException(Throwable cause) {
        super(cause);
    }

    public BannedNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
