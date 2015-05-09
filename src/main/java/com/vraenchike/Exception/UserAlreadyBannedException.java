package com.vraenchike.Exception;

/**
 * Created by Alexeev on 03-May-15.
 */
public class UserAlreadyBannedException extends Exception {
    public UserAlreadyBannedException() {
    }

    public UserAlreadyBannedException(String message) {
        super(message);
    }

    public UserAlreadyBannedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyBannedException(Throwable cause) {
        super(cause);
    }

    public UserAlreadyBannedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
