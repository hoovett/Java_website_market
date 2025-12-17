package com.example.First_website.Mappers;

import com.example.First_website.DB_Entity.OrderEntity;
import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.DTO.CreateOrderDTO;
import com.example.First_website.DTO.OrderDTO;
import com.example.First_website.DTO.OrderItemDTO;
import com.example.First_website.DTO.UpdateOrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDTO(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO(
                orderEntity.getId(),
                orderEntity.getStatus(),
                orderEntity.getUser().getId(),
                orderEntity.getUser().getUsername(),
                orderEntity.getTotalPrice(),
                orderEntity.getItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getId(),
                        item.getOrder().getId(),
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPriceAtPurchase()
                ))
                .toList()


        );
        return orderDTO;
    }

    public OrderEntity toEntity(CreateOrderDTO orderDTO, UserEntity userEntity) {
        if (orderDTO == null || userEntity == null) {
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus("NEW");
        orderEntity.setUser(userEntity);
        return orderEntity;
    }

    public void updateStatus(OrderEntity order, UpdateOrderDTO dto) {
        order.setStatus(dto.getStatus());
    }

}
