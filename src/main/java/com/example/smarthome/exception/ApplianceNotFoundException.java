package com.example.smarthome.exception;

public class ApplianceNotFoundException extends Exception {
    public ApplianceNotFoundException(String id) {
        super("Appliance '" + id + "' not found");
    }
}