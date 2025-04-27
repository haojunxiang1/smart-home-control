package com.example.smarthome.scheduler;

import com.example.smarthome.service.SmartHomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class YearlyMaintenanceJob {

    private final SmartHomeService service;

    public YearlyMaintenanceJob(SmartHomeService service) {
        this.service = service;
    }

    @Scheduled(cron = "${scheduler.yearly.cron}", zone = "${scheduler.yearly.zone}")
    public void shutdownForUpdate() {
        log.info("Yearly maintenance: turning everything OFF");
        service.all().forEach(a -> {
            a.turnOff();
            log.info("→ {} is now OFF", a.getId());
        });
    }
}
