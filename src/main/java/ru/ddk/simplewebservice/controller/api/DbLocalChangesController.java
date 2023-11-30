package ru.ddk.simplewebservice.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.dto.LocalChangesDto;
import ru.ddk.simplewebservice.services.LocalChangesService;

import java.util.List;

@RestController
@RequestMapping("/v1/lc")
public class DbLocalChangesController {

    private final LocalChangesService localChangesService;

    public DbLocalChangesController(LocalChangesService localChangesService) {
        this.localChangesService = localChangesService;
    }

    @GetMapping(value = "/findall" , produces = MediaType.APPLICATION_JSON_VALUE)
    private List<LocalChangesDto> findAll(){
        return localChangesService.findAll();
    }

    @PostMapping("/add")
    private LocalChangesDto addLocalStorage(
            @RequestParam(defaultValue = "userName", required = false) String userName,
            @RequestParam(defaultValue = "false", required = false) boolean committed,
            @RequestParam(defaultValue = "false", required = false) boolean aborted,
            @RequestParam(defaultValue = "tranId", required = false) String tranId,
            @RequestParam(defaultValue = "firstName", required = false) String firstName,
            @RequestParam(defaultValue = "lastName", required = false) String lastName,
            @RequestParam(defaultValue = "email", required = false) String email,
            @RequestParam(defaultValue = "phone", required = false) String phone
    ) {
        return localChangesService.save(new LocalChanges(userName, committed, aborted, tranId, firstName, lastName, email, phone));
    }

    @PutMapping("/update")
    private LocalChangesDto updateLocalStorage(
            @RequestParam(defaultValue = "userName", required = false) String userName,
            @RequestParam(defaultValue = "false", required = false) boolean committed,
            @RequestParam(defaultValue = "false", required = false) boolean aborted,
            @RequestParam(defaultValue = "tranId", required = false) String tranId,
            @RequestParam(defaultValue = "firstName", required = false) String firstName,
            @RequestParam(defaultValue = "lastName", required = false) String lastName,
            @RequestParam(defaultValue = "email", required = false) String email,
            @RequestParam(defaultValue = "phone", required = false) String phone
    ) {
        return localChangesService.save(new LocalChanges(userName, committed, aborted, tranId, firstName, lastName, email, phone));
    }

    @DeleteMapping("/delete")
    private String deleteLocalChang(@RequestParam(defaultValue = "", required = false) String userName){
        if(userName.isEmpty())
        {
            return "tranId has not be empty";
        }
        if(localChangesService.delete(userName))
        {
            return "tranId deleted";
        } else {
            return "tranId not deleted";
        }
    }
}
