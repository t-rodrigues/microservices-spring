package dev.trodrigues.order.controllers;

import dev.trodrigues.order.domain.Order;
import dev.trodrigues.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        var orderResponse = orderService.saveOrder(order);
        return ResponseEntity.ok(orderResponse);
    }

}
