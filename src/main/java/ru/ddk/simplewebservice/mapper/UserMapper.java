package ru.ddk.simplewebservice.mapper;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.domain.User;
import ru.ddk.simplewebservice.dto.UserDto;

@Component
public class UserMapper {

    public UserDto toDto(User u) {
        return new UserDto(u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPhone());
    }

    public UserDto toDto(JsonObject u) {
        return u.isEmpty() ? new UserDto("", "", "", "", "") : new UserDto(u.get("username").toString(), u.get("firstName").toString(), u.get("lastName").toString(), u.get("email").toString(), u.get("phone").toString());
    }

    public UserDto toDto(LocalChanges c) {
        return new UserDto(c.getUsername(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone());
    }

    public User fromDto(UserDto userDto){
        return new User(userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getPhone());
    }
}
