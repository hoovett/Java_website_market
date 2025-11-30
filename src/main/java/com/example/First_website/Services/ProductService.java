package com.example.First_website.Services;

import com.example.First_website.DB_Entity.ProductEntity;
import com.example.First_website.DTO.ProductDTO;
import com.example.First_website.Exceptions.ResourceNotFoundException;
import com.example.First_website.Mappers.ProductMapper;
import com.example.First_website.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = productMapper.toEntity(productDTO);
        productEntity = productRepository.save(productEntity);
        return productMapper.toDTO(productEntity);
    }

    //Update
    public ProductDTO updateProduct(Long id, ProductDTO updatedProductDto) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        product.setName(updatedProductDto.getName());
        product.setPrice(updatedProductDto.getPrice());

        ProductEntity updatedProduct = productRepository.save(product);
        return productMapper.toDTO(updatedProduct);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByName(String name) {
        return productRepository.findByName(name)
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public void deleteProduct(Long id)
    {
        productRepository.deleteById(id);
    }
}
