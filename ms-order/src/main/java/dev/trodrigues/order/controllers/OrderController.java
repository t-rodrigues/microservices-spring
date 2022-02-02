package dev.trodrigues.order.controllers;

import dev.trodrigues.order.controllers.requests.OrderDto;
import dev.trodrigues.order.controllers.responses.OrderResponse;
import dev.trodrigues.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody @Valid OrderDto order) {
        var orderResponse = orderService.saveOrder(order.toOrder());
        return ResponseEntity.ok(new OrderResponse(orderResponse));
    }

}
