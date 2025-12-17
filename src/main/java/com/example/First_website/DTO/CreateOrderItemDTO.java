package com.example.First_website.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateOrderItemDTO {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public CreateOrderItemDTO() {}

    public CreateOrderItemDTO(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

}
