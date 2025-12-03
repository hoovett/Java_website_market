package com.example.First_website.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

public class CreateOrderDTO {
    
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 ]+$", message = "Status should not contain special symbols")
    private String status;
    
    @NotNull(message = "User ID is required")
    private final Long userId;

    public CreateOrderDTO(String status, Long userId) {
        this.status = status;
        this.userId = userId;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

}
