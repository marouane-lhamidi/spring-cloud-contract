package com.saggu.cdc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ConsumerApplication.class)
@AutoConfigureStubRunner(ids = {"com.saggu:service-provider:+:stubs:8080"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class CdcBaseClassService {
    @Autowired
    OrderServiceConsumer serviceConsumer;


    @Test
    void setup() {
        String expected = "Sony TV";
        String actual = serviceConsumer.getOrder("1").getBody().getItemName();

        assertEquals(actual, expected);
    }
    

}
