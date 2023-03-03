package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "should return order by id=1"

    request {
        url "/orders/1"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                "orderId": "1",
                "itemName": "Sony TV",
                "price": 500.0,
                "units": 1
        )
    }
}