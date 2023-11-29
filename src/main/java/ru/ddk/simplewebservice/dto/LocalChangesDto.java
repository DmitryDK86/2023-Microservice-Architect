package ru.ddk.simplewebservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LocalChangesDto {
    private final String tranId;
    private Boolean committed;
    private Boolean aborted;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;

    public LocalChangesDto(String tranId, Boolean committed, Boolean aborted, String username, String firstName, String lastName, String email, String phone) {
        this.tranId = tranId;
        this.committed = committed;
        this.aborted = aborted;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
