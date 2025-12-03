package com.example.First_website.Mappers;

import com.example.First_website.DB_Entity.OrderEntity;
import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.DTO.CreateOrderDTO;
import com.example.First_website.DTO.OrderDTO;
import com.example.First_website.DTO.UpdateOrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDTO(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }
        return new OrderDTO(
                orderEntity.getId(),
                orderEntity.getStatus(),
                orderEntity.getUser().getId(),
                orderEntity.getUser().getUsername() // Assuming UserEntity has getUsername()
        );
    }

    public OrderEntity toEntity(CreateOrderDTO orderDTO, UserEntity userEntity) {
        if (orderDTO == null || userEntity == null) {
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setStatus(orderDTO.getStatus());
        orderEntity.setUser(userEntity);
        return orderEntity;
    }

    public void updateEntity(UpdateOrderDTO orderDTO, OrderEntity orderEntity) {
        if (orderDTO == null || orderEntity == null) {
            return;
        }
        if (orderDTO.hasStatus()) {
            orderEntity.setStatus(orderDTO.getStatus());
        }
        // User cannot be updated through order update
    }
}
