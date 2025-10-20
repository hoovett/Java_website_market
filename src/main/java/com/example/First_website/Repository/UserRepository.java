package com.example.First_website.Repository;

import com.example.First_website.DB_Entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByUsername(String username);

    List<UserEntity> findByUsernameContainingIgnoreCase(String username);
}
