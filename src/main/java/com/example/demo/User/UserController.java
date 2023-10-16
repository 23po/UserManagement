package com.example.demo.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/register")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        //TODO: process POST request

        User newUser = userService.addUSer(userDto);
        
        if (newUser != null) {
           return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } else
        {
            return new ResponseEntity<>("Failed to Create user", HttpStatus.BAD_REQUEST);

        }
    }
    
    @GetMapping(value="/list") 
    public ResponseEntity<List<User>> listUsers() 
    {
      List<User> userList = userService.listUsers();

      return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    
}
