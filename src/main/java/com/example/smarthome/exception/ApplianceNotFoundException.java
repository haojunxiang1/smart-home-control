package com.example.smarthome.exception;

public class ApplianceNotFoundException extends RuntimeException {
    public ApplianceNotFoundException(String id) {
        super("Appliance '" + id + "' not found");
    }
}