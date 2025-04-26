package com.example.smarthome.config;

import com.example.smarthome.domain.AirConditioner;
import com.example.smarthome.domain.Fan;
import com.example.smarthome.domain.Light;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceConfig {

    @Bean         // bean name defaults to method name -> "light1"
    public Light light1() {
        return new Light("light-1");
    }

    @Bean
    public Fan fan1() {
        return new Fan("fan-1");
    }

    @Bean
    public AirConditioner ac1() {
        return new AirConditioner("ac-1");
    }
}

