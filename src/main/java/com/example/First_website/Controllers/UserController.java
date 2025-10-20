package com.example.First_website.Controllers;

import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.Services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserController {
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping
    public List<UserEntity> getAllUsersByName(String username)
    {
        return userService.getAllUsersByUsername(username);
    }

    public List<UserEntity> getAllUsersByNameIgnoreCase(String username)
    {
        return userService.getAllUsersByUsernameIgnoreCase(username);
    }

    public Optional

    public UserEntity createUser(UserEntity user)
    {
        return userService.save(user);
    }


}
