package com.vraenchike.Exception;

/**
 * Created by Alexeev on 03-May-15.
 */
public class UserNotAuth extends Exception {
    public UserNotAuth() {
    }

    public UserNotAuth(String message) {
        super(message);
    }

    public UserNotAuth(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotAuth(Throwable cause) {
        super(cause);
    }

    public UserNotAuth(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
