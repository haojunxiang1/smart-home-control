package com.example.smarthome.domain;

import lombok.Getter;

@Getter
public class AirConditioner implements Appliance {
    private final String id;
    private Mode mode = Mode.OFF;

    public enum Mode {COOL, HEAT, DRY, OFF}

    public AirConditioner(String id) {
        this.id = id;
    }

    @Override
    public void turnOn() {
        mode = Mode.COOL;
    }

    @Override
    public void turnOff() {
        mode = Mode.OFF;  /* thermostat OFF */
    }

    @Override
    public boolean isOn() {
        return mode != Mode.OFF;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
