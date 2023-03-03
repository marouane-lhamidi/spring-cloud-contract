package com.saggu.cdc;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = ProviderApplication.class)
public abstract class CdcBaseClass {
    @Autowired
    OrderController orderController;

    @MockBean
    OrderService orderService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(orderController);

        Mockito.when(orderService.getOrder("1"))
                .thenReturn(OrderService.orders.get("1"));
    }
}
