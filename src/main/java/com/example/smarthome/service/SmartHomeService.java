package com.example.smarthome.service;

import com.example.smarthome.domain.*;
import com.example.smarthome.exception.ApplianceNotFoundException;
import com.example.smarthome.exception.InvalidModeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SmartHomeService {
    private final Map<String, Appliance> devices = new ConcurrentHashMap<>();


    public void register(Appliance a) {
        if (devices.putIfAbsent(a.getId(), a) != null) {
            throw new IllegalStateException(String.format("Device %s already exists", a.getId()));
        }
        devices.put(a.getId(), a);
        log.info("Device {} registered: {}", a.getId(), a.getClass().getSimpleName());
    }

    private Appliance getRequired(String id) {
        Appliance a = devices.get(id);
        if (a == null) throw new ApplianceNotFoundException(id);
        return a;
    }

    public Appliance turnOn(String id) {
        Appliance a = getRequired(id);
        a.turnOn();
        return a;
    }

    public Appliance turnOff(String id) {
        Appliance a = getRequired(id);
        a.turnOff();
        return a;
    }

    public Fan setSpeed(String id, int speed) {
        Fan f = (Fan) getRequired(id);
        f.setSpeed(speed);
        return f;
    }

    public AirConditioner setMode(String id, String mode) {
        AirConditioner ac = (AirConditioner) getRequired(id);
        AirConditioner.Mode m;
        try {
            m = AirConditioner.Mode.valueOf(mode.toUpperCase());
        } catch (IllegalArgumentException ex) {      // enum lookup failed
            throw new InvalidModeException(mode);
        }

        ac.setMode(m);
        return ac;
    }

    public Collection<Appliance> all() {
        return devices.values();
    }
}
