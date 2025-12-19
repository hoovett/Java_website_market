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


}
