package ru.ddk.simplewebservice.services;

import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.domain.TranManager;
import ru.ddk.simplewebservice.dto.TranManagerDto;
import ru.ddk.simplewebservice.dto.UserDto;

import java.util.List;

public interface UserServiceTranManagerHttp {
    List<UserDto> findAll();
    TranManagerDto save(TranManager tranManager, LocalChanges localChanges);
    UserDto findById(String id);
}
