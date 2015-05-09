package com.vraenchike.Exception;

/**
 * Created by Alexeev on 03-May-15.
 */
public class PhotoAlreadyLiked extends Exception {
    public PhotoAlreadyLiked() {
    }

    public PhotoAlreadyLiked(String message) {
        super(message);
    }

    public PhotoAlreadyLiked(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoAlreadyLiked(Throwable cause) {
        super(cause);
    }

    public PhotoAlreadyLiked(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
