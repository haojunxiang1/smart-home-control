package com.example.smarthome.exception;

public class InvalidSpeedException extends Exception {
    public InvalidSpeedException(int speed) {
        super("Speed must be 0-2, got " + speed);
    }
}
