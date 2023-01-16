package com.gt9.travelstories.controller;



import com.gt9.travelstories.model.User;
import com.gt9.travelstories.model.UserRequest;
import com.gt9.travelstories.model.UserResponse;
import com.gt9.travelstories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public UserResponse createUser(@RequestBody UserRequest tempUser){

        User newUser = new User();
        newUser.setName(tempUser.getFirstName()+" "+tempUser.getLastName());
        newUser.setEmail(tempUser.getEmail());
        newUser.setPassword(tempUser.getPassword());
        return service.createUser(newUser);
    }

    @PostMapping("/signin")
    public UserResponse signInUser(@RequestBody UserRequest tempUser){

        User newUser = new User();
        newUser.setEmail(tempUser.getEmail());
        newUser.setPassword(tempUser.getPassword());
        return service.signInUser(newUser);
    }
}
