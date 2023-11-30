package ru.ddk.simplewebservice.controller.api;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.domain.TranManager;
import ru.ddk.simplewebservice.dto.TranManagerDto;
import ru.ddk.simplewebservice.dto.UserDto;
import ru.ddk.simplewebservice.services.TranManagerService;

import java.util.List;

@RestController
@RequestMapping("/v1/tm")
public class DbControllerTranManager {

    private final TranManagerService tranManagerService;

    public DbControllerTranManager(TranManagerService tranManagerService) {
        this.tranManagerService = tranManagerService;
    }

    @GetMapping(value = "/findall" , produces = MediaType.APPLICATION_JSON_VALUE)
    private List<TranManagerDto> findAll(){
        return tranManagerService.findAll();
    }

    @PostMapping("/add")
    private TranManagerDto addTranManager(
                         @RequestParam(defaultValue = "tranId", required = false) String tranId,
                         @RequestParam(defaultValue = "false", required = false) boolean committed,
                         @RequestParam(defaultValue = "false", required = false) boolean aborted,
                         @RequestParam(defaultValue = "1", required = false) Integer cntInst,
                         @RequestParam(defaultValue = "0", required = false) Integer cntRespInst,

                         @RequestParam(defaultValue = "userName", required = false) String userName,
                         @RequestParam(defaultValue = "firstName", required = false) String firstName,
                         @RequestParam(defaultValue = "lastName", required = false) String lastName,
                         @RequestParam(defaultValue = "email", required = false) String email,
                         @RequestParam(defaultValue = "phone", required = false) String phone
    ){
        return tranManagerService.save(new TranManager(tranId, committed, aborted, cntInst, cntRespInst),
                new LocalChanges(userName, committed, aborted, tranId, firstName, lastName, email, phone));
    }

    @PostMapping("/update")
    private TranManagerDto updateTranManager(
            @RequestParam(defaultValue = "tranId", required = false) String tranId,
            @RequestParam(defaultValue = "false", required = false) boolean committed,
            @RequestParam(defaultValue = "false", required = false) boolean aborted
    ){
        return tranManagerService.update(new TranManager(tranId, committed, aborted, 0, 0));
    }

    @DeleteMapping("/delete")
    private String deleteTranManager(@RequestParam(defaultValue = "", required = false) String tranId){
        if(tranId.isEmpty())
        {
            return "tranId has not be empty";
        }
        if(tranManagerService.delete(tranId))
        {
            return "tranManager deleted";
        } else {
            return "tranManager not deleted";
        }
    }
}
