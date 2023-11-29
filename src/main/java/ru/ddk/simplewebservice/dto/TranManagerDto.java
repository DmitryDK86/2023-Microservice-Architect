package ru.ddk.simplewebservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TranManagerDto {
    private final String tranId;
    private Boolean committed;
    private Boolean aborted;
    private final Integer cntInst;
    private Integer cntRespInst;

    public TranManagerDto(String tranId, Boolean committed, Boolean aborted, Integer cntInst, Integer cntRespInst) {
        this.tranId = tranId;
        this.committed = committed;
        this.aborted = aborted;
        this.cntInst = cntInst;
        this.cntRespInst = cntRespInst;
    }
}
