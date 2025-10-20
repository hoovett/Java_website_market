package com.example.First_website.DB_Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username can't be blank")
    @Size(min = 2, max = 30, message = "Username should contain from 2 to 30 symbols")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 ]+$", message = "Name should not contain special symbols")
    private String username;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email is required")
    private String email;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
