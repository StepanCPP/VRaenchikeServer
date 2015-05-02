package com.vraenchike.Exception;

/**
 * Created by Alexeev on 02-May-15.
 */
public class UserCredentialAlreadyExist extends Exception {
    public UserCredentialAlreadyExist() {
        super();
    }

    public UserCredentialAlreadyExist(String message) {
        super(message);
    }

    public UserCredentialAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public UserCredentialAlreadyExist(Throwable cause) {
        super(cause);
    }

    protected UserCredentialAlreadyExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
