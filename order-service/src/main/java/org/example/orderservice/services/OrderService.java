package org.example.orderservice.services;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.entities.Order;
import org.example.orderservice.exceptions.OrderNotFoundException;
import org.example.orderservice.exceptions.StationNotFoundException;
import org.example.orderservice.repositories.OrderRepository;
import org.example.orderservice.repositories.StationRepository;
import org.example.orderservice.security.UserDetailsImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final StationRepository stationRepository;

    private final Random random = new Random();

    public void createOrder(Integer fromStationId, Integer toStationId) {
        if (!stationRepository.existsById(fromStationId) ||
                !stationRepository.existsById(toStationId)) {
            throw new StationNotFoundException("Station not found");
        }

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Order order = Order.builder()
                .userId(user.getUserId())
                .fromStationId(fromStationId)
                .toStationId(toStationId)
                .status(1)
                .created(LocalDateTime.now())
                .build();

        orderRepository.save(order);
    }

    @Scheduled(fixedRate = 10000)
    public void processOrders() {
        List<Order> orders = orderRepository.findByStatus(1);
        for (Order order : orders) {
            processOrder(order);
        }
    }

    public void processOrder(Order order) {
        order.setStatus(random.nextInt(2) + 2);
        orderRepository.save(order);
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new OrderNotFoundException("Order not found"));
    }

}
