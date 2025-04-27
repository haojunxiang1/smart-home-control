package com.example.smarthome.service;

import com.example.smarthome.domain.AirConditioner;
import com.example.smarthome.domain.Appliance;
import com.example.smarthome.domain.Fan;
import com.example.smarthome.domain.Light;
import com.example.smarthome.exception.ApplianceNotFoundException;
import com.example.smarthome.exception.InvalidModeException;
import com.example.smarthome.exception.InvalidSpeedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SmartHomeServiceTest {

    private SmartHomeService service;

    @BeforeEach
    void setUp() {
        service = new SmartHomeService();
        service.register(new Light("light-1"));
        service.register(new Fan("fan-1"));
        service.register(new AirConditioner("ac-1"));
    }

    @Test @DisplayName("turnOn sets appliance to ON")
    void turnOn_ok() {
        Appliance light = service.turnOn("light-1");
        assertTrue(light.isOn());
    }

    @Test @DisplayName("turnOn unknown id → ApplianceNotFoundException")
    void turnOn_unknownId() {
        assertThrows(ApplianceNotFoundException.class,
                () -> service.turnOn("missing"));
    }

    @Test @DisplayName("setSpeed changes fan speed")
    void setSpeed_ok() {
        Fan fan = service.setSpeed("fan-1", 2);
        assertEquals(2, fan.getSpeed());
    }

    @Test @DisplayName("setSpeed invalid value → InvalidSpeedException")
    void setSpeed_invalid() {
        assertThrows(InvalidSpeedException.class,
                () -> service.setSpeed("fan-1", 5));
    }

    @Test @DisplayName("setMode changes AC mode")
    void setMode_ok() {
        AirConditioner ac = service.setMode("ac-1", "HEAT");
        assertEquals(AirConditioner.Mode.HEAT, ac.getMode());
    }

    @Test @DisplayName("setMode invalid string → InvalidModeException")
    void setMode_invalid() {
        assertThrows(InvalidModeException.class,
                () -> service.setMode("ac-1", "ICE"));
    }
}
