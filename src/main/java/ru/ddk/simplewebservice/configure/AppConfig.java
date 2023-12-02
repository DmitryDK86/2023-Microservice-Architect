package ru.ddk.simplewebservice.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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

    @Override
    public String toString() {
        return "AppConfig{" +
                "port=" + port +
                ", uriNeo='" + uriNeo + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public String getWriteServiceUrl() {
        return writeServiceUrl;
    }

    public String getTranmanagerserviceurl() {
        return tranmanagerserviceurl;
    }
}
