package ru.ddk.simplewebservice.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Schema(description = "User info")
@Node("User")
@Data
public class User {

    @Id
    @Schema(description = "user id username")
    private final String username;

    @Schema(description = "user firstName")
    @Property("firstName")
    private final String firstName;

    @Schema(description = "user lastName")
    @Property("lastName")
    private final String lastName;

    @Schema(description = "user email")
    @Property("email")
    private final String email;

    @Schema(description = "user phone")
    @Property("phone")
    private final String phone;

    public User(String username, String firstName, String lastName, String email, String phone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
