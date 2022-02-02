package dev.trodrigues.order.controllers;

import dev.trodrigues.order.domain.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        return ResponseEntity.ok(order);
    }

}
