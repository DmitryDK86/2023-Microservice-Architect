package ru.ddk.simplewebservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDto {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
}
