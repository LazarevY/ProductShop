package app.core.rest.controllers;

import app.core.response.Response;
import app.core.rest.front.models.ProductsOrder;
import app.core.services.interfaces.UserOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private UserOrdersService userOrdersService;

    @PostMapping("/api/order/price")
    public Response getOrderPrice(@RequestBody ProductsOrder req){
        return userOrdersService.getOrderPrice(req);
    }

}
