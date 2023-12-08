package ru.ddk.simplewebservice.configure;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Value("${server.port}")
    private Integer port;

    @Value("${spring.neo4j.uri}")
    private String uriNeo;

    @Value("${spring.neo4j.authentication.username}")
    private String userName;

    @Value("${spring.neo4j.authentication.password}")
    private String userPassword;

    @Value("${writeserviceurl}")
    private String writeServiceUrl;

    @Value("${tranmanagerserviceurl}")
    private String tranmanagerserviceurl;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.topic}")
    private String topic;

    @Value(value = "${spring.kafka.group-id}")
    private String groupId;

    @Override
    public String toString() {
        return "AppConfig{" +
                "port=" + port +
                ", uriNeo='" + uriNeo + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public Map<String, Object> getKafkaProps(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getBootstrapAddress());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, getGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    public String getWriteServiceUrl() {
        return writeServiceUrl;
    }

    public String getTranmanagerserviceurl() {
        return tranmanagerserviceurl;
    }

    public String getBootstrapAddress() {
        return bootstrapAddress;
    }

    public String getTopic() {
        return topic;
    }

    public String getGroupId() {
        return groupId;
    }
}
