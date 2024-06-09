package org.example.orderservice.controllers;

import lombok.AllArgsConstructor;
import org.example.orderservice.dto.CreateOrderRequest;
import org.example.orderservice.entities.Order;
import org.example.orderservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        orderService.createOrder(createOrderRequest.getFromStationId(),
                createOrderRequest.getToStationId());

        return ResponseEntity.ok("Order was created successfully! Wait till it been checked");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
