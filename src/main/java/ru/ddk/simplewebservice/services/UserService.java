package ru.ddk.simplewebservice.services;

import ru.ddk.simplewebservice.domain.User;
import ru.ddk.simplewebservice.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    boolean delete(String userId);
    UserDto save(Integer id, User user);
    UserDto findById(String id);
}
