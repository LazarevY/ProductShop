package app.core.rest.controllers;

import app.data.mappers.OrderMapper;
import app.data.modeles.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserOrdersController {

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/uso")
    public List<Order> getOrders(){
        System.out.println("Proccess get orders");
        var users = orderMapper.findAll();
        users.forEach(System.out::println);
        return orderMapper.findAll();
    }
}
