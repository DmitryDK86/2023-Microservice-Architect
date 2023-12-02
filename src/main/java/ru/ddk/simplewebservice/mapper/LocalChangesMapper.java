package ru.ddk.simplewebservice.mapper;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.dto.LocalChangesDto;

@Component
public class LocalChangesMapper {

    public LocalChangesDto toDto(LocalChanges c) {
        return new LocalChangesDto(c.getTranId(), c.getCommitted(), c.getAborted(), c.getUsername(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone());
    }

    public LocalChangesDto toDto(JsonObject j) {
        return j.isEmpty() ? new LocalChangesDto("-1", false, false, "", "", "", "", "") :
                new LocalChangesDto(j.get("tranId").toString(),
                        Boolean.valueOf(j.get("committed").toString()),
                        Boolean.valueOf(j.get("aborted").toString()),
                        j.get("username").toString(),
                        j.get("firstName").toString(),
                        j.get("lastName").toString(),
                        j.get("email").toString(),
                        j.get("phone").toString());
    }

    public LocalChanges fromDto(LocalChangesDto c) {
        return new LocalChanges(c.getTranId(), c.getCommitted(), c.getAborted(), c.getUsername(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone());
    }
}
