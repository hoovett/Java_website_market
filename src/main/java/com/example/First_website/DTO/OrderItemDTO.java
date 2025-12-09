package com.example.First_website.DTO;

import com.example.First_website.DB_Entity.ProductEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemDTO {
    private Long id;
    
    private Long orderId;
    
    private ProductEntity product;


    @NotNull(message = "Quantity can't be null")
    @Min(value = 1, message = "Quantity must be >= 1")
    private int quantity;
    
    @NotNull(message = "Price can't be null")
    @Min(value = 0, message = "Price should be >= 0")
    private Double priceAtPurchase;

    public OrderItemDTO(){}

    public OrderItemDTO(Long id, Long orderId, ProductEntity product, int quantity, Double priceAtPurchase)
    {
        this.id = id;
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
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
