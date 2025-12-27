package com.example.First_website.Services;

import com.example.First_website.DB_Entity.OrderEntity;
import com.example.First_website.DB_Entity.OrderItemEntity;
import com.example.First_website.DB_Entity.ProductEntity;
import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.DTO.CreateOrderDTO;
import com.example.First_website.DTO.CreateOrderItemDTO;
import com.example.First_website.DTO.OrderDTO;
import com.example.First_website.DTO.UpdateOrderDTO;
import com.example.First_website.Exceptions.ResourceNotFoundException;
import com.example.First_website.Exceptions.UserNotFoundException;
import com.example.First_website.Mappers.OrderItemMapper;
import com.example.First_website.Mappers.OrderMapper;
import com.example.First_website.Repository.OrderRepository;
import com.example.First_website.Repository.ProductRepository;
import com.example.First_website.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
   private final OrderRepository orderRepository;
   private final UserRepository userRepository;
   private final ProductRepository productRepository;
   private final OrderMapper orderMapper;
   private final OrderItemMapper orderItemMapper;

   public OrderService(
           OrderRepository orderRepository,
           UserRepository userRepository,
           ProductRepository productRepository,
           OrderMapper orderMapper,
           OrderItemMapper orderItemMapper
   ) {
      this.orderRepository = orderRepository;
      this.userRepository = userRepository;
      this.productRepository = productRepository;
      this.orderMapper = orderMapper;
      this.orderItemMapper = orderItemMapper;
   }

   //Create Order
    public OrderDTO createOrder(CreateOrderDTO createOrderDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = userRepository.findById(createOrderDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with this ID not found"));

        OrderEntity orderEntity = orderMapper.toEntity(createOrderDTO, user);

        for (CreateOrderItemDTO itemDTO : createOrderDTO.getItems())
        {
            ProductEntity product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product with this ID not found"));

            OrderItemEntity orderItemEntity = orderItemMapper.toEntity(itemDTO, product);
            orderEntity.addItem(orderItemEntity);
        }

        OrderEntity saveOrder = orderRepository.save(orderEntity);
        return orderMapper.toDTO(saveOrder);
    }
    //===========
    //UpdateORDER
    //===========
    public OrderDTO updateOrderStatus(Long orderId, UpdateOrderDTO updatedOrderDTO)
    {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with this Id not found"));

        orderMapper.updateStatus(orderEntity, updatedOrderDTO);

        return orderMapper.toDTO(orderEntity);
    }

    //============
    //GET ORDER BY ID
    //===========
    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long orderId)
    {
     OrderEntity orderEntity = orderRepository.findById(orderId)
             .orElseThrow(() -> new ResourceNotFoundException("Order with this Id not found"));

     return orderMapper.toDTO(orderEntity);
    }

    //=============
    //GET ALL ORDERS
    //=============
    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders()
    {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public  List<OrderDTO> getOrdersByUser(Long userId)
    {
        return orderRepository.findByUserId(userId)
                .stream().map(orderMapper::toDTO)
                .toList();
    }

    public void deleteOrder(Long orderId)
    {
        orderRepository.deleteById(orderId);
    }
}
