package ru.ddk.simplewebservice.controller.api;

import org.springframework.web.bind.annotation.*;
import ru.ddk.simplewebservice.services.kafka.KafkaService;

@RestController
@RequestMapping("/v1/kafka")
public class KafkaController {

    private final KafkaService kafkaService;

    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping(value = "/send")
    private void sendMessage(@RequestParam(defaultValue = "message", required = false) String message) {
        kafkaService.sendMessage(message);
    }

    @GetMapping(value = "/read")
    private String getMessage() {
        return kafkaService.readMessage();
    }
}
