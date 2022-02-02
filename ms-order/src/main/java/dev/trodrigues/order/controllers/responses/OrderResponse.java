package dev.trodrigues.order.controllers.responses;

import dev.trodrigues.order.domain.Order;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class OrderResponse {

    private Long id;
    private String name;
    private Long product;
    private BigDecimal total;
    private LocalDate purchaseDate;
    private String cpfClient;
    private String cep;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.name = order.getName();
        this.product = order.getProduct();
        this.total = order.getTotal();
        this.purchaseDate = order.getPurchaseDate();
        this.cpfClient = order.getCpfClient();
        this.cep = order.getCep();
    }

}
