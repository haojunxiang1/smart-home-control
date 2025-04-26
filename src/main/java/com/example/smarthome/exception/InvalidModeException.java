package com.example.smarthome.exception;

public class InvalidModeException extends Exception {
    public InvalidModeException(String mode) {
        super("Mode '" + mode + "' is not valid");
    }
}
