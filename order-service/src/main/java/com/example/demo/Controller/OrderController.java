package com.example.demo.Controller;

import com.example.demo.Service.OrderService;
import com.example.demo.DTO.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void createOrder(@RequestBody OrderRequest order) {
        orderService.CreateOrder2(order);
    }
}
