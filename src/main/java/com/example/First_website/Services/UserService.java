package com.example.First_website.Services;

import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.DTO.CreateUserRequestDTO;
import com.example.First_website.DTO.UpdateUserRequestDTO;
import com.example.First_website.DTO.UserDTO;
import com.example.First_website.Exceptions.UserNotFoundException;
import com.example.First_website.Mappers.UserMapper;
import com.example.First_website.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper)
    {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO save(CreateUserRequestDTO newUser)
    {
        UserEntity userEntity = userRepository.save(userMapper.toEntity(newUser));
        return userMapper.toDTO(userEntity);
    }

    public List<UserDTO> getAllUsers()
    {
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }
    public UserDTO getById(Long id)
    {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User with this id not found"));
    }

    public List<UserDTO> getAllUsersByUsername(String username)
    {
        return userRepository.findByUsername(username).stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsersByUsernameIgnoreCase(String username)
    {
        return userRepository.findByUsernameContainingIgnoreCase(username).stream().map(userMapper::toDTO).toList();
    }

    public List<UserDTO> getUserByEmail(String email)
    {
        return userRepository.findByEmail(email).stream().map(userMapper::toDTO).toList();
    }



    public void delete(Long id)
    {
        // Check if user exists before deleting
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(Long id, UpdateUserRequestDTO updateUserRequestDTO) {
        // Find the existing user or throw an exception if not found
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        
        // Update the existing user entity with the new data
        userMapper.updateEntity(updateUserRequestDTO, existingUser);
        
        // Save the updated user
        UserEntity updatedUser = userRepository.save(existingUser);
        
        // Convert to DTO and return
        return userMapper.toDTO(updatedUser);
    }
}

