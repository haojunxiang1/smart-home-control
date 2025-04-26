package com.example.smarthome.web;

import com.example.smarthome.domain.*;
import com.example.smarthome.exception.ApplianceNotFoundException;
import com.example.smarthome.exception.InvalidModeException;
import com.example.smarthome.exception.InvalidSpeedException;
import com.example.smarthome.service.SmartHomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/appliances")
@RequiredArgsConstructor
public class ApplianceController {

    private final SmartHomeService service;

    /* -------- generic on/off -------- */
    @PostMapping("/{id}/on")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse on(@PathVariable String id) throws ApplianceNotFoundException {
        Appliance appliance = service.turnOn(id);
        return new ApiResponse(id+" Turned ON, status: "+appliance.toString());
    }

    @PostMapping("/{id}/off")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse off(@PathVariable String id) throws ApplianceNotFoundException {
        Appliance appliance = service.turnOff(id);
        return new ApiResponse(id+"turned OFF, status: "+appliance.toString());
    }

    /* -------- fan-specific ---------- */
    @PostMapping("/fan/{id}/speed/{level}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse setFanSpeed(@PathVariable String id, @PathVariable int level) throws InvalidSpeedException, ApplianceNotFoundException {
        Fan f = service.setSpeed(id, level);
        return new ApiResponse(f.getId()+" Speed set: "+f.getSpeed());
    }

    /* -------- AC-specific ----------- */
    @PostMapping("/ac/{id}/mode/{mode}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse setAcMode(@PathVariable String id,
                                 @PathVariable String mode) throws InvalidModeException, ApplianceNotFoundException {
        AirConditioner ac = service.setMode(id, mode);
        return new ApiResponse(ac.getId()+" Mode set: "+ac.getMode());
    }

    /* diagnostic */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Appliance> all() { return service.all(); }
}
