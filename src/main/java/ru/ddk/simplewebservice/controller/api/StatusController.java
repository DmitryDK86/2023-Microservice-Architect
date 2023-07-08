package ru.ddk.simplewebservice.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController  {

    @GetMapping("/health")
    public String getHeath(){
        return "{\"status\": \"OK\"}";
    }
}
