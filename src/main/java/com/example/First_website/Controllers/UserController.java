package com.example.First_website.Controllers;

import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<UserEntity>> getAllUsersByName(@RequestParam("username") String username)
    {
        return ResponseEntity.ok(userService.getAllUsersByUsername(username));
    }

    @GetMapping("/searchByNameIG")
    public ResponseEntity<List<UserEntity>> getAllUsersByNameIgnoreCase(@RequestParam("username") String username)
    {
        return ResponseEntity.ok(userService.getAllUsersByUsernameIgnoreCase(username));
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity<UserEntity> getUserByEmail(@RequestParam("email") String email)
    {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id)
    {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @Valid @RequestBody UserEntity userDetails) {
        return userService.getById(id)
                .map(existingUser -> {
                    existingUser.setUsername(userDetails.getUsername());
                    existingUser.setEmail(userDetails.getEmail());
                    return ResponseEntity.ok(userService.save(existingUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.getById(id)
                .map(user -> {
                    userService.delete(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity user)
    {
        return ResponseEntity.ok(userService.save(user));
    }


}
