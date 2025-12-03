package com.example.First_website.Mappers;

import com.example.First_website.DB_Entity.ProductEntity;
import com.example.First_website.DTO.CreateProductDTO;
import com.example.First_website.DTO.ProductDTO;
import com.example.First_website.DTO.UpdateProductDTO;
import org.springframework.stereotype.Component;



@Component
public class ProductMapper {

    public ProductDTO toDTO(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return new ProductDTO(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice()
        );
    }

    public ProductEntity toEntity(CreateProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        return productEntity;
    }
    public void updateEntity(UpdateProductDTO productDTO, ProductEntity productEntity)
    {
        if(productDTO == null || productEntity == null)
            return;
        if(productDTO.getName() != null)
            productEntity.setName(productDTO.getName());
        if(productDTO.getPrice() != null)
            productEntity.setPrice(productDTO.getPrice());
    }
}
