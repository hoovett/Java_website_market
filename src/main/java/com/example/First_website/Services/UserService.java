package com.example.First_website.Services;

import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public UserEntity save(UserEntity user)
    {
        return userRepository.save(user);
    }

    public Optional<UserEntity> getById(Long id)
    {
        return userRepository.findById(id);
    }

    public List<UserEntity> getAllUsersByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> getAllUsersByUsernameIgnoreCase(String username)
    {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    public Optional<UserEntity> getUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public List<UserEntity> getAllUsers()
    {
        return userRepository.findAll();
    }

    public void delete(Long id)
    {
        userRepository.deleteById(id);
    }
}
