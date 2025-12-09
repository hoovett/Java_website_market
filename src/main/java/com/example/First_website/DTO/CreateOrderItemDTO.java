package com.example.First_website.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateOrderItemDTO {
    @NotNull(message = "Order ID can't be null")
    private Long orderId;
    @NotNull(message = "Product ID can't be null")
    private Long productId;
    
    @NotNull(message = "Quantity can't be null")
    @Min(value = 1, message = "Quantity must be >= 1")
    private int quantity;
    
    @NotNull(message = "Price can't be null")
    @Min(value = 0, message = "Price should be >= 0")
    private Double priceAtPurchase;

    public CreateOrderItemDTO() {}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(Double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }
}
