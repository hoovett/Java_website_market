package com.example.First_website.DTO;

import jakarta.validation.constraints.Pattern;

public class UpdateOrderDTO {
    
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 ]+$", message = "Status should not contain special symbols")
    private String status;

    public UpdateOrderDTO() {
    }

    public UpdateOrderDTO(String status) {
        this.status = status;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean hasStatus() {
        return status != null && !status.trim().isEmpty();
    }
}
