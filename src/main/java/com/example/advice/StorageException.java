package com.example.advice;

/**
 * Created by kimyongyeon on 2016-12-23.
 */

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}

