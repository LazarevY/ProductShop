package app.core.services.impl;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.services.interfaces.OrdersService;
import app.data.mappers.OrderMapper;
import app.data.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Response getOrders(String email) {
        var user = userMapper.getByEmail(email);
        var order = orderMapper.getByUserId(user.getId());
        Response r = new Response();
        r.setCode(ResponseCode.OK);
        r.addParameter("addOrder", order);
        return r;
    }
}
