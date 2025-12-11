package com.example.First_website.DB_Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Transient;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9] +$", message = "Status should not contain special symbols")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();
    
    @Transient
    public Double getTotalPrice() {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        return items.stream()
                   .mapToDouble(item -> item.getPriceAtPurchase() * item.getQuantity())
                   .sum();
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return user.getUsername();
    }

    public UserEntity getUser()
    {
        return user;
    }

    public void setUser(UserEntity user)
    {
        this.user = user;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    // Helper methods for bidirectional relationship
    public void addItem(OrderItemEntity item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(OrderItemEntity item) {
        items.remove(item);
        item.setOrder(null);
    }
}
