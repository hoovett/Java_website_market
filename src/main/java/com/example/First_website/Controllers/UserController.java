package com.example.First_website.Controllers;

import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.DTO.CreateUserRequestDTO;
import com.example.First_website.DTO.UpdateUserRequestDTO;
import com.example.First_website.DTO.UserDTO;
import com.example.First_website.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

//TODO: update UserController to use DTO

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/searchByName")
    public List<UserDTO> getAllUsersByName(@RequestParam("username") String username)
    {
        return userService.getAllUsersByUsername(username);
    }

    @GetMapping("/searchByNameIG")
    public List<UserDTO> getAllUsersByNameIgnoreCase(@RequestParam("username") String username)
    {
        return userService.getAllUsersByUsernameIgnoreCase(username);
    }

    @GetMapping("/searchByEmail")
    public List<UserDTO> getUserByEmail(@RequestParam("email") String email)
    {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id)
    {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestDTO userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody CreateUserRequestDTO user)
    {
        return userService.save(user);
    }


}
