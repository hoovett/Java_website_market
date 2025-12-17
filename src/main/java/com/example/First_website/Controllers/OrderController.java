package com.example.First_website.Controllers;

import com.example.First_website.DTO.CreateOrderDTO;
import com.example.First_website.DTO.OrderDTO;
import com.example.First_website.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: update controller

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }


    @GetMapping("/user/{userId}/status/{status}")
    public List<OrderDTO> getOrdersByUserIdAndStatus(
            @PathVariable Long userId,
            @PathVariable String status) {
        return orderService.findByUserIdAndStatus(userId, status);
    }

    @PostMapping
    public OrderDTO createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {
        return orderService.createOrder(createOrderDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/count/status/{status}")
    public long countOrdersByStatus(@PathVariable String status) {
        return orderService.countByStatus(status);
    }

    @GetMapping("/exists/user/{userId}/status/{status}")
    public boolean existsByUserAndStatus(
            @PathVariable Long userId,
            @PathVariable String status) {
        return orderService.existsByUserAndStatus(userId, status);
    }
}
