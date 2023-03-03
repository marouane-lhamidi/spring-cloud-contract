package com.saggu.cdc;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.UPGRADE_REQUIRED;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class OrderServiceConsumer {
    public static Map<String, Order> orders = Map.of(
            "1", new Order("1", "Sony TV", 500.00, 1),
            "2", new Order("2", "Samsung TV", 480.00, 1),
            "3", new Order("3", "Washing Machine", 1500.0, 1)
    );
        private static final String SERVICE_PROVIDER = "http://localhost:8080";
    private final RestTemplate restTemplate;

    public OrderServiceConsumer(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @GetMapping(value = "/reports/orders/{orderId}", produces = "application/json")
    public ResponseEntity<Order> getOrder(@PathVariable("orderId") String orderId) {

        try {
            Order order = this.restTemplate.getForObject(SERVICE_PROVIDER + "/orders/{orderId}", Order.class, orderId);

            if (isNull(order)) {
                return notFound().build();
            } else if (isNull(order.getOrderId())) {
                return ResponseEntity.status(UPGRADE_REQUIRED.value()).build();//426
            }
            return ok(order);
        } catch (Exception e) {
            return notFound().build();
        }
    }

    @Data
    @AllArgsConstructor
    static class Order {
        private String orderId;
        private String itemName;
        private double price;
        private int units;
    }
}
