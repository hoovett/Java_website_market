package com.example.First_website.Mappers;

import com.example.First_website.DB_Entity.OrderItemEntity;
import com.example.First_website.DB_Entity.ProductEntity;
import com.example.First_website.DTO.CreateOrderItemDTO;
import com.example.First_website.DTO.OrderItemDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemEntity toEntity(CreateOrderItemDTO dto, ProductEntity product)
    {
        OrderItemEntity item = new OrderItemEntity();

        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setPriceAtPurchase(product.getPrice());

        return item;
    }
}
