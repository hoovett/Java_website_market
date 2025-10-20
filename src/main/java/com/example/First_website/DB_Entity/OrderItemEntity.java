package com.example.First_website.DB_Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @NotNull(message = "Quantity can't be null")
    @Min(value = 1, message = "Quantity must be >= 1")
    private int quantity;

    @NotNull(message = "price can't be null")
    @Min(value = 0, message = "Price should be >= 0")
    private Double priceAtPurchase;

    public OrderItemEntity(){}

    public OrderItemEntity(OrderEntity order, ProductEntity product, int quantity, Double priceAtPurchase)
    {
        this.order = order;
        this.product =product;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    // Геттеры/сеттеры
    public Long getId() {
        return id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
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
