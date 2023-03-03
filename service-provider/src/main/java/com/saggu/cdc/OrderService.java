package com.saggu.cdc;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Objects.isNull;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@Service
public class OrderService {

    static Map<String, Order> orders = Map.of(
            "1", new Order("1", "Sony TV", 500.00, 1),
            "2", new Order("2", "Samsung TV", 480.00, 1),
            "3", new Order("3", "Washing Machine", 1500.0, 1)
    );

    public Order getOrder(String orderId) {
       return orders.get(orderId);
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
