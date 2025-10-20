package com.example.First_website.Services;

import com.example.First_website.DB_Entity.ProductEntity;
import com.example.First_website.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public ProductEntity saveProduct(ProductEntity product)
    {
        return productRepository.save(product);
    }

    //Update
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct)
    {
        return productRepository.findById(id).map(product ->
        {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    public List<ProductEntity> getAllProducts()
    {
        return productRepository.findAll();
    }

    public List<ProductEntity> getProductsByName(String name)
    {
        return productRepository.findByName(name);
    }

    public ProductEntity getProductById(Long id)
    {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id)
    {
        productRepository.deleteById(id);
    }
}
