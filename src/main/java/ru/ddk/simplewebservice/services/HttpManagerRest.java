package ru.ddk.simplewebservice.services;

import com.google.gson.JsonObject;
import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.domain.TranManager;

import java.util.List;

public interface HttpManagerRest {
    JsonObject postSaveQuery(TranManager tranManager, LocalChanges localChanges);
}
