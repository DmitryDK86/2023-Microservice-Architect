package ru.ddk.simplewebservice.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Schema(description = "Local Changes")
@Node("TranManager")
@Data
public class TranManager {
    @Id
    @Schema(description = "id tran")
    private final String idTran;

    @Schema(description = "committed")
    private Boolean committed;

    @Schema(description = "aborted")
    private Boolean aborted;

    @Schema(description = "cnt instance write")
    private Integer cntInst;

    @Schema(description = "cnt response instance write")
    private Integer cntRespInst;

    public TranManager(String idTran, Boolean committed, Boolean aborted, Integer cntInst, Integer cntRespInst) {
        this.idTran = idTran;
        this.committed = committed;
        this.aborted = aborted;
        this.cntInst = cntInst;
        this.cntRespInst = cntRespInst;
    }

    @Override
    public String toString() {
        return "TranManager{" +
                "idTran='" + idTran + '\'' +
                ", committed=" + committed +
                ", aborted=" + aborted +
                ", cntInst=" + cntInst +
                ", cntRespInst=" + cntRespInst +
                '}';
    }
}
