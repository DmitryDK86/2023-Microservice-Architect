package ru.ddk.simplewebservice.services;

import com.google.gson.JsonObject;
import ru.ddk.simplewebservice.domain.User;

import java.util.List;

public interface HttpRest {
    JsonObject postSaveQuery(User user);
    JsonObject putSaveQuery(User user);
    String deleteQuery(String userName);
    List<JsonObject> findAll(User user);
}
