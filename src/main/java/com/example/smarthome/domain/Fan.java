package com.example.smarthome.domain;

import lombok.Getter;
import com.example.smarthome.exception.InvalidSpeedException;
import lombok.ToString;

@ToString
@Getter
public class Fan implements Appliance {
    private final String id;
    private int speed = 0;   // 0 = OFF

    public Fan(String id) {
        this.id = id;
    }

    @Override
    public void turnOn() {
        speed = 1;
    }

    @Override
    public void turnOff() {
        speed = 0; /* reduce to zero */
    }

    @Override
    public boolean isOn() {
        return speed > 0;
    }

    public void setSpeed(int speed) {
        if (speed < 0 || speed > 2) throw new InvalidSpeedException(speed);
        this.speed = speed;
    }

}
