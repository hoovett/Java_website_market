package com.example.First_website.DTO;

public class OrderDTO {
    private Long id;
    private String status;
    private Long userId;
    private String userName;
    private Double totalPrice;

    public OrderDTO() {
    }

    public OrderDTO(Long id, String status, Long userId, String userName, Double totalPrice) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.userName = userName;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
