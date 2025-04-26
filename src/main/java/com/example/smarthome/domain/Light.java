package com.example.smarthome.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Light implements Appliance {
    private final String id;
    private boolean switchOn = false;

    public Light(String id) {
        this.id = id;
    }

    @Override
    public void turnOn() {
        switchOn = true;
    }

    @Override
    public void turnOff() {
        switchOn = false; /* toggle switch OFF */
    }

    @Override
    public boolean isOn() {
        return switchOn;
    }
}
