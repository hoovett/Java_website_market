package com.example.First_website.Mappers;

import com.example.First_website.DB_Entity.OrderEntity;
import com.example.First_website.DB_Entity.OrderItemEntity;
import com.example.First_website.DB_Entity.ProductEntity;
import com.example.First_website.DTO.CreateOrderItemDTO;
import com.example.First_website.DTO.OrderItemDTO;
import com.example.First_website.DTO.UpdateOrderItemDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemDTO toDTO(OrderItemEntity orderItemEntity) {
        if (orderItemEntity == null) {
            return null;
        }
        return new OrderItemDTO(
                orderItemEntity.getId(),
                orderItemEntity.getOrder().getId(),
                orderItemEntity.getProduct(),
                orderItemEntity.getQuantity(),
                orderItemEntity.getPriceAtPurchase()
        );
    }

    public OrderItemEntity toEntity(CreateOrderItemDTO orderItemDTO, OrderEntity order, ProductEntity product) {
        if (orderItemDTO == null || order == null || product == null) {
            return null;
        }
        return new OrderItemEntity(
                order,
                product,
                orderItemDTO.getQuantity(),
                orderItemDTO.getPriceAtPurchase()
        );
    }

    public void updateEntity(UpdateOrderItemDTO orderItemDTO, OrderItemEntity orderItemEntity, ProductEntity product) {
        if (orderItemDTO == null || orderItemEntity == null) {
            return;
        }
        
        if (product != null) {
            orderItemEntity.setProduct(product);
        }
        
        if (orderItemDTO.getQuantity() > 0) { // Assuming quantity is validated to be > 0
            orderItemEntity.setQuantity(orderItemDTO.getQuantity());
        }
        
        if (orderItemDTO.getPriceAtPurchase() != null && orderItemDTO.getPriceAtPurchase() >= 0) {
            orderItemEntity.setPriceAtPurchase(orderItemDTO.getPriceAtPurchase());
        }
    }
}
