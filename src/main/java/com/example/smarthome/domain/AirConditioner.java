package com.example.smarthome.domain;

import com.example.smarthome.exception.InvalidModeException;
import lombok.Getter;
import lombok.ToString;

@ToString
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

    public void setMode(Mode mode) throws InvalidModeException {
        if (mode == null) throw new InvalidModeException("null");
        this.mode = mode;
    }
}
