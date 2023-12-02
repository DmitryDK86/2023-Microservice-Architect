package ru.ddk.simplewebservice.controller.api;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ddk.simplewebservice.domain.LocalChanges;
import ru.ddk.simplewebservice.domain.TranManager;
import ru.ddk.simplewebservice.dto.TranManagerDto;
import ru.ddk.simplewebservice.dto.UserDto;
import ru.ddk.simplewebservice.services.UserServiceTranManagerHttp;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class DbControllerUser {

    private final UserServiceTranManagerHttp userService;

    public DbControllerUser(UserServiceTranManagerHttp userService) {
        this.userService = userService;
    }

    @Timed(value = "ddk_timed_find_by_id")
    @PostMapping(value = "/findbyid/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    private UserDto findById( @RequestParam("id") String id ){
        return userService.findById(id);
    }

    @Timed(value = "ddk_timed_findall")
    @GetMapping(value = "/findall" , produces = MediaType.APPLICATION_JSON_VALUE)
    private List<UserDto> findAll(){
        return userService.findAll();
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
        return userService.save(new TranManager(tranId, committed, aborted, cntInst, cntRespInst),
                new LocalChanges(userName, committed, aborted, tranId, firstName, lastName, email, phone));
    }

//    @Timed(value = "ddk_timed_add_new")
//    @PostMapping("/add")
//    private UserDto addUser(@RequestParam(defaultValue = "userName", required = false) String userName,
//                         @RequestParam(defaultValue = "firstName", required = false) String firstName,
//                         @RequestParam(defaultValue = "lastName", required = false) String lastName,
//                         @RequestParam(defaultValue = "email", required = false) String email,
//                         @RequestParam(defaultValue = "phone", required = false) String phone
//    ){
//        return userService.savePost(new User(userName, firstName, lastName, email, phone));
//    }

//    @Timed(value = "ddk_timed_update")
//    @PutMapping("/update")
//    private UserDto updateUser(@RequestParam(defaultValue = "userName", required = false) String userName,
//                         @RequestParam(defaultValue = "firstName", required = false) String firstName,
//                         @RequestParam(defaultValue = "lastName", required = false) String lastName,
//                         @RequestParam(defaultValue = "email", required = false) String email,
//                         @RequestParam(defaultValue = "phone", required = false) String phone
//    ){
//        return userService.savePut(new User(userName, firstName, lastName, email, phone));
//    }

//    @Timed(value = "ddk_timed_del")
//    @DeleteMapping("/delete")
//    private String deleteUser(@RequestParam(defaultValue = "", required = false) String userName){
//        if(userName.isEmpty())
//        {
//            return "UserName has not be empty";
//        }
//        if(userService.delete(userName))
//        {
//            return "User deleted";
//        } else {
//            return "User not deleted";
//        }
//    }
}
