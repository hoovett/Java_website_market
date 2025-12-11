package com.example.First_website.Mappers;

import com.example.First_website.DB_Entity.OrderEntity;
import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.DTO.CreateOrderDTO;
import com.example.First_website.DTO.OrderDTO;
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
                orderEntity.getTotalPrice()  // Add total price from entity
        );
        return orderDTO;
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

}
