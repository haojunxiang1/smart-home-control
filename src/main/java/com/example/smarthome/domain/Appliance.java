package com.example.smarthome.domain;

public interface Appliance {
    String getId();
    void turnOn();
    void turnOff();           // semantic “OFF” for this device
    boolean isOn();
}
