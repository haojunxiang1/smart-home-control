package com.example.smarthome.web;

import com.example.smarthome.domain.*;
import com.example.smarthome.service.SmartHomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/appliances")
@RequiredArgsConstructor
public class ApplianceController {

    private final SmartHomeService service;

    /* -------- generic on/off -------- */
    @PostMapping(value = "/{id}/on", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse on(@PathVariable String id) {
        Appliance appliance = service.turnOn(id);
        log.info("{} turned ON", id);
        return new ApiResponse(String.format("%s turned ON", id));
    }

    @PostMapping(value = "/{id}/off", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse off(@PathVariable String id) {
        Appliance appliance = service.turnOff(id);
        log.info("{} turned OFF", id);
        return new ApiResponse(String.format("%s turned OFF", id));
    }

    /* -------- fan-specific ---------- */
    @PostMapping(value = "/fan/{id}/speed/{level}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse setFanSpeed(@PathVariable String id, @PathVariable int level) {
        Fan fan = service.setSpeed(id, level);
        log.info("{} speed set to {}", id, fan.getSpeed());
        return new ApiResponse(String.format("%s speed set: %d", fan.getId(), fan.getSpeed()));
    }

    /* -------- AC-specific ----------- */
    @PostMapping(value = "/ac/{id}/mode/{mode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse setAcMode(@PathVariable String id, @PathVariable String mode) {
        AirConditioner ac = service.setMode(id, mode);
        log.info("{} mode set to {}", id, ac.getMode());
        return new ApiResponse(String.format("%s mode set: %s", ac.getId(), ac.getMode()));
    }

    /* diagnostic */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Appliance> all() {
        return service.all();
    }
}
