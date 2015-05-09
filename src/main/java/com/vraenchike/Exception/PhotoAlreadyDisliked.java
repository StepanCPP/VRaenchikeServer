package com.vraenchike.Exception;

/**
 * Created by Alexeev on 03-May-15.
 */
public class PhotoAlreadyDisliked extends Exception {
    public PhotoAlreadyDisliked() {
    }

    public PhotoAlreadyDisliked(String message) {
        super(message);
    }

    public PhotoAlreadyDisliked(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoAlreadyDisliked(Throwable cause) {
        super(cause);
    }

    public PhotoAlreadyDisliked(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
