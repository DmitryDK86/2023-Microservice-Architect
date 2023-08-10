package ru.ddk.simplewebservice.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ddk.simplewebservice.domain.User;
import ru.ddk.simplewebservice.dto.UserDto;
import ru.ddk.simplewebservice.services.UserService;

import java.util.List;

@RestController
public class DbController {

    private final UserService userService;

    public DbController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/findbyid/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    private UserDto findById( @RequestParam("id") String id ){
        return userService.findById(id);
    }


    @GetMapping(value = "/findall" , produces = MediaType.APPLICATION_JSON_VALUE)
    private List<UserDto> findAll(){
        return userService.findAll();
    }

    @PostMapping("/add")
    private UserDto addUser(@RequestParam(defaultValue = "userName", required = false) String userName,
                         @RequestParam(defaultValue = "firstName", required = false) String firstName,
                         @RequestParam(defaultValue = "lastName", required = false) String lastName,
                         @RequestParam(defaultValue = "email", required = false) String email,
                         @RequestParam(defaultValue = "phone", required = false) String phone
    ){
        return userService.save(new User(userName, firstName, lastName, email, phone));
    }

    @PutMapping("/update")
    private UserDto updateUser(@RequestParam(defaultValue = "userName", required = false) String userName,
                         @RequestParam(defaultValue = "firstName", required = false) String firstName,
                         @RequestParam(defaultValue = "lastName", required = false) String lastName,
                         @RequestParam(defaultValue = "email", required = false) String email,
                         @RequestParam(defaultValue = "phone", required = false) String phone
    ){
        return userService.save(new User(userName, firstName, lastName, email, phone));
    }

    @DeleteMapping("/delete")
    private String deleteUser(@RequestParam(defaultValue = "", required = false) String userName){
        if(userName.isEmpty())
        {
            return "UserName has not be empty";
        }
        if(userService.delete(userName))
        {
            return "User deleted";
        } else {
            return "User not deleted";
        }
    }
}
