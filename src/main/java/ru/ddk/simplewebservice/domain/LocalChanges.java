
package ru.ddk.simplewebservice.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Schema(description = "Local Changes")
@Node("LocalChanges")
@Data
public class LocalChanges {

    @Id
    @Schema(description = "id object")
    private final String username;

    @Schema(description = "committed")
    private Boolean committed;

    @Schema(description = "aborted")
    private Boolean aborted;

    @Schema(description = "tran id")
    private final String tranId;

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

    public LocalChanges(String username, Boolean committed, Boolean aborted, String tranId, String firstName, String lastName, String email, String phone) {
        this.username = username;
        this.committed = committed;
        this.aborted = aborted;
        this.tranId = tranId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "LocalChanges{" +
                "username='" + username + '\'' +
                ", committed=" + committed +
                ", aborted=" + aborted +
                ", tranId='" + tranId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
