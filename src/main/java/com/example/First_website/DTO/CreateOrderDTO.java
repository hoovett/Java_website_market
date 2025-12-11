package com.example.First_website.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderDTO {
    
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 ]+$", message = "Status should not contain special symbols")
    private String status;
    
    @NotNull(message = "User ID is required")
    private final Long userId;
    
    @Valid
    @NotNull(message = "Order items are required")
    private List<CreateOrderItemDTO> items = new ArrayList<>();

    public CreateOrderDTO(String status, Long userId) {
        this.status = status;
        this.userId = userId;
    }
    
    public CreateOrderDTO(String status, Long userId, List<CreateOrderItemDTO> items) {
        this.status = status;
        this.userId = userId;
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
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
    
    public List<CreateOrderItemDTO> getItems() {
        return items;
    }
    
    public void setItems(List<CreateOrderItemDTO> items) {
        this.items = items != null ? new ArrayList<>(items) : new ArrayList<>();
    }

}
