package com.example.smarthome;

import com.example.smarthome.domain.AirConditioner;
import com.example.smarthome.domain.Fan;
import com.example.smarthome.domain.Light;
import com.example.smarthome.service.SmartHomeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartHomeControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeControlApplication.class, args);
    }
    @Bean
    CommandLineRunner bootstrapDevices(SmartHomeService service) {
        return args -> {
            service.register(new Light("light-1"));
            service.register(new Fan("fan-1"));
            service.register(new AirConditioner("ac-1"));
        };
    }
}
