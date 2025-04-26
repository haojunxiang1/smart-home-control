package com.example.smarthome.service;

import com.example.smarthome.domain.*;
import com.example.smarthome.exception.ApplianceNotFoundException;
import com.example.smarthome.exception.InvalidModeException;
import com.example.smarthome.exception.InvalidSpeedException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SmartHomeService {
    private final Map<String, Appliance> devices = new ConcurrentHashMap<>();

    public SmartHomeService() {
        // Pre-register demo devices
        register(new Light("light-1"));
        register(new Fan("fan-1"));
        register(new AirConditioner("ac-1"));
    }

    public void register(Appliance a) {
        devices.put(a.getId(), a);
    }

    private Appliance getRequired(String id) throws ApplianceNotFoundException {
        Appliance a = devices.get(id);
        if (a == null) throw new ApplianceNotFoundException(id);
        return a;
    }

    public Appliance turnOn(String id) throws ApplianceNotFoundException {
        Appliance a = getRequired(id);
        a.turnOn();
        return a;
    }

    public Appliance turnOff(String id) throws ApplianceNotFoundException {
        Appliance a = getRequired(id);
        a.turnOff();
        return a;
    }

    public Fan setSpeed(String id, int speed) throws ApplianceNotFoundException, InvalidSpeedException {
        Fan f = (Fan) getRequired(id);
        f.setSpeed(speed);
        return f;
    }

    public AirConditioner setMode(String id, String mode) throws ApplianceNotFoundException, InvalidModeException {
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
