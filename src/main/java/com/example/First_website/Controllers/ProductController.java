package com.example.First_website.Controllers;

import com.example.First_website.DB_Entity.ProductEntity;
import com.example.First_website.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductEntity getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductEntity createProduct(@Valid @RequestBody ProductEntity product)
    {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ProductEntity updateProduct(@PathVariable Long id, @RequestBody ProductEntity updatedProduct)
    {
        return productService.updateProduct(id, updatedProduct);
    }

    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }
}
