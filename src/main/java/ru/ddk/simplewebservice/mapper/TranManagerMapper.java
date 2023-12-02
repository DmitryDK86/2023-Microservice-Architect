package ru.ddk.simplewebservice.mapper;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import ru.ddk.simplewebservice.domain.TranManager;
import ru.ddk.simplewebservice.dto.TranManagerDto;

@Component
public class TranManagerMapper {

    public TranManagerDto toDto(TranManager tranManager){
        return tranManager == null ? new TranManagerDto("-1",false,false,0,0) :
                new TranManagerDto(tranManager.getIdTran(), tranManager.getCommitted(), tranManager.getAborted(), tranManager.getCntInst(), tranManager.getCntRespInst());
    }

    public TranManager fromDto(TranManagerDto tranManagerDto){
        return new TranManager(tranManagerDto.getTranId(), tranManagerDto.getCommitted(), tranManagerDto.getAborted(), tranManagerDto.getCntInst(), tranManagerDto.getCntRespInst());
    }

    public TranManagerDto toDto(JsonObject u) {
        return u.isEmpty() ? new TranManagerDto("", false, false, 0, 0) :
                new TranManagerDto(u.get("tranId").toString(), u.get("committed").getAsBoolean(), u.get("aborted").getAsBoolean(), u.get("cntInst").getAsInt(), u.get("cntRespInst").getAsInt());
    }
}
