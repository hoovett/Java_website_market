package com.example.First_website.Services;

import com.example.First_website.DB_Entity.OrderEntity;
import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.DTO.CreateOrderDTO;
import com.example.First_website.DTO.OrderDTO;
import com.example.First_website.Exceptions.ResourceNotFoundException;
import com.example.First_website.Mappers.OrderMapper;
import com.example.First_website.Repository.OrderRepository;
import com.example.First_website.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, 
                       UserRepository userRepository,
                       OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderDTO createOrder(CreateOrderDTO createOrderDTO) {
        // Find the user
        UserEntity user = userRepository.findById(createOrderDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + createOrderDTO.getUserId()));
        
        // Convert DTO to entity and save
        OrderEntity orderEntity = orderMapper.toEntity(createOrderDTO, user);
        orderEntity = orderRepository.save(orderEntity);
        
        return orderMapper.toDTO(orderEntity);
    }

    @Transactional(readOnly = true)
    public OrderDTO getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        return orderMapper.toDTO(orderEntity);
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        return orderRepository.findByUserId(userId)
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<OrderDTO> findByUserIdAndStatus(Long userId, String status) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        return orderRepository.findByUserIdAndStatus(userId, status)
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByUserAndStatus(Long userId, String status) {
        return userRepository.findById(userId)
                .map(user -> orderRepository.existsByUserAndStatus(user, status))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }

    @Transactional(readOnly = true)
    public long countByStatus(String status) {
        return orderRepository.countByStatus(status);
    }
}
