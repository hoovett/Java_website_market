package com.example.First_website.Controllers;

import com.example.First_website.DTO.CreateOrderDTO;
import com.example.First_website.DTO.OrderDTO;
import com.example.First_website.DTO.UpdateOrderDTO;
import com.example.First_website.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: update controller

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id)
    {
        return orderService.getOrderById(id);
    }

    @GetMapping
    List<OrderDTO> getAllOrder()
    {
        return orderService.getAllOrders();
    }

    @GetMapping
    public List<OrderDTO> getOrderByUser(@PathVariable Long userId)
    {
        return orderService.getOrdersByUser(userId);
    }

    @PatchMapping("/{id}/status")
    public OrderDTO updateOrderStatus(@PathVariable Long id, @Valid @RequestBody UpdateOrderDTO dto)
    {
        return orderService.updateOrderStatus(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id)
    {
        orderService.deleteOrder(id);
    }


}
