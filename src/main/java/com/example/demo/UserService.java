package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUSer(UserDto userDto) {

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        userRepository.save(user);

        return user; 

    }


    public List<User> listUsers() {
        return userRepository.findAll();
    }





    
}
