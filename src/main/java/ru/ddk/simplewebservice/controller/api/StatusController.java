package ru.ddk.simplewebservice.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ddk.simplewebservice.configure.AppConfig;

@RestController
public class StatusController  {

    private final AppConfig appConfig;

    public StatusController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping("/health")
    public String getHeath(){
        return "{\"status\": \"OK\"}";
    }

    @GetMapping("/config")
    public String getConfig(){
        return appConfig.toString();
    }
}
