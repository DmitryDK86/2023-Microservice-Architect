package ru.ddk.simplewebservice.services.kafka;

public interface KafkaService {
    void  sendMessage(String message);
    String readMessage();
}
