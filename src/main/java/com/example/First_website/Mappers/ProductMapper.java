package com.example.First_website.Mappers;

import com.example.First_website.DB_Entity.ProductEntity;
import com.example.First_website.DTO.ProductDTO;
import org.springframework.stereotype.Component;

//TODO: implement updateEntity method


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

    public ProductEntity toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        return productEntity;
    }
    public void updateEntity(ProductDTO productDTO, ProductEntity productEntity)
    {
        if(productDTO == null || productEntity == null)
            return;
        if(productDTO.getName() != null)
            productEntity.setName(productDTO.getName());
        if(productDTO.getPrice() != null)
            productEntity.setPrice(productDTO.getPrice());
    }
}
