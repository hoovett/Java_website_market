package com.example.First_website.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UpdateOrderDTO {

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "^(NEW|PAID|SHIPPED|COMPLETED|CANCELED)$",
            message = "Invalid order status"
    )
    private String status;

    public UpdateOrderDTO() {}

    public UpdateOrderDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
