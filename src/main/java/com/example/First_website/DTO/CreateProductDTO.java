package com.example.First_website.DTO;

import jakarta.validation.constraints.*;

public class CreateProductDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name should contain from 2 to 100 symbols")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 ]+$", message = "Name should not contain special symbols")
    private String name;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Double price;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
