package ru.ddk.simplewebservice.services;

import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.dto.LocalChangesDto;

import java.util.List;

public interface LocalChangesService {
    List<LocalChangesDto> findAll();
    boolean delete(String userName);
    LocalChangesDto save(LocalChanges localChanges);
}
