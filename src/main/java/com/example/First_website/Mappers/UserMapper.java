package com.example.First_website.Mappers;

import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.DTO.CreateUserRequestDTO;
import com.example.First_website.DTO.UpdateUserRequestDTO;
import com.example.First_website.DTO.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail()
        );
    }

    public UserEntity toEntity(CreateUserRequestDTO createUserRequestDTO) {
        if (createUserRequestDTO == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserRequestDTO.getUsername());
        userEntity.setEmail(createUserRequestDTO.getEmail());
        userEntity.setPassword(createUserRequestDTO.getPassword());
        return userEntity;
    }

    public void updateEntity(UpdateUserRequestDTO updateUserRequestDTO, UserEntity userEntity) {
        if (updateUserRequestDTO == null || userEntity == null) {
            return;
        }
        if (updateUserRequestDTO.getUsername() != null) {
            userEntity.setUsername(updateUserRequestDTO.getUsername());
        }
        if (updateUserRequestDTO.getEmail() != null) {
            userEntity.setEmail(updateUserRequestDTO.getEmail());
        }
    }
}
