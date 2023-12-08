package ru.ddk.simplewebservice.services.kafka;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.ddk.simplewebservice.configure.AppConfig;

@Service
@Log4j2
public class KafkaServiceImpl implements KafkaService {

    private final AppConfig appConfig;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private String oldResult = "";

    public KafkaServiceImpl(AppConfig appConfig, KafkaTemplate<String, String> kafkaTemplate) {
        this.appConfig = appConfig;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(appConfig.getTopic(), message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }

    @Override
    public String readMessage() {
        return oldResult;
    }

//    @KafkaListener(groupId = "${spring.kafka.group-id}", topics = "${spring.kafka.topic}")
//    public void listenKafka(ConsumerRecord<String, String> record) {
//        oldResult+= record.value() + " offset:" + record.offset() + " topic:" + record.topic()+ " partition:" + record.partition() + "\n";
//    }
}
