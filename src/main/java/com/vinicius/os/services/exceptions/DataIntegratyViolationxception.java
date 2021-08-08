package com.vinicius.os.services.exceptions;

public class DataIntegratyViolationxception extends RuntimeException{
    public DataIntegratyViolationxception(String message) {
        super(message);
    }

    public DataIntegratyViolationxception(String message, Throwable cause) {
        super(message, cause);
    }
}
