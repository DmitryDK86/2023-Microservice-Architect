package ru.ddk.simplewebservice.services;

import com.google.gson.JsonObject;
import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.domain.User;

import java.util.List;

public interface HttpLocalChangesRest {
    JsonObject postSaveQuery(LocalChanges localChanges);
    JsonObject putSaveQuery(LocalChanges localChanges);
    String deleteQuery(String userName);
    List<JsonObject> findAll();
}
