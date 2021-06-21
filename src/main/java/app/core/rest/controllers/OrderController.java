package app.core.rest.controllers;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.rest.front.models.ProductsOrder;
import app.core.rest.front.models.ProductsRegisterOrder;
import app.core.security.JwtProvider;
import app.core.services.interfaces.UserOrdersService;
import app.core.services.interfaces.UserService;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private UserOrdersService userOrdersService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/api/order/price")
    public Response getOrderPrice(@RequestBody ProductsOrder req){
        return userOrdersService.getOrderPrice(req);
    }

    @PostMapping("/api/order/new")
    public Response registerOrder(@RequestBody ProductsRegisterOrder req){
        if (req.getToken() != null && jwtProvider.validateToken(req.getToken())) {
            User u = userService.findByEmail(jwtProvider.getNicknameFromToken(req.getToken()));
            req.setUserId(u.getId());
            userOrdersService.registerOrder(req);
            return new Response(ResponseCode.OK, "");
        }
        return new Response(ResponseCode.ERROR, "", Map.of("success", false));
    }


}
