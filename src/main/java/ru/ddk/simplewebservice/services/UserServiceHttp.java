package ru.ddk.simplewebservice.services;

import ru.ddk.simplewebservice.domain.User;
import ru.ddk.simplewebservice.dto.UserDto;

import java.util.List;

public interface UserServiceHttp {
    List<UserDto> findAll();
    boolean delete(String userId);
    UserDto savePost(User user);
    UserDto savePut(User user);
    UserDto findById(String id);
}
