package ru.ddk.simplewebservice.services;

import com.google.gson.JsonObject;
import ru.ddk.simplewebservice.domain.User;

public interface HttpRest {
    JsonObject postSaveQuery(User user);
    JsonObject putSaveQuery(User user);
    String deleteQuery(String userName);
}
