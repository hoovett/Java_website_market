package com.example.First_website.DB_Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name= "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name of product can't be blank")
    @Size(min = 2, max = 100, message = "Name should contain from 2 to 100 symbols")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9 ]+$", message = "Name should not contain special symbols")
    private String name;

    @NotNull(message = "Price is obligated")
    @Positive(message = "Price should be positive")
    private Double price;

    public ProductEntity(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return price;
    }
    public void setPrice(Double price)
    {
        this.price = price;
    }
}
