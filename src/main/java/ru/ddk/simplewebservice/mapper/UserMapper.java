package ru.ddk.simplewebservice.mapper;

import org.springframework.stereotype.Component;
import ru.ddk.simplewebservice.domain.User;
import ru.ddk.simplewebservice.dto.UserDto;

@Component
public class UserMapper {
    public UserDto toDto(User u){
        return new UserDto(u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhone());
    }
}
