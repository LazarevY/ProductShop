package app.core.services.impl;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.services.interfaces.UserOrdersService;
import app.data.mappers.OrderMapper;
import app.data.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOrdersServiceImpl implements UserOrdersService {


    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Response getOrders(long userId) {
        var order = orderMapper.getByUserId(userId);
        Response r = new Response();
        r.setCode(ResponseCode.OK);
        r.addParameter("userOrders", order);
        return r;
    }
}
