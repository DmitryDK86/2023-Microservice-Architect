package ru.ddk.simplewebservice.services;

import ru.ddk.simplewebservice.domain.TranManager;
import ru.ddk.simplewebservice.dto.TranManagerDto;

import java.util.List;

public interface TranManagerService {
    List<TranManagerDto> findAll();
    TranManagerDto save(TranManager tranManager);
    TranManagerDto update(TranManager tranManager);
    boolean delete(String tranId);
    TranManagerDto findById(String tranId);
}
