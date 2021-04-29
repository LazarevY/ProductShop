package app.core.services.impl;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.services.interfaces.PersonalAccountService;
import app.core.services.util.HashService;
import app.data.mappers.PayMethodMapper;
import app.data.mappers.UserAddressMapper;
import app.data.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalAccountServiceImpl implements PersonalAccountService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HashService hashService;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private PayMethodMapper payMethodMapper;

    @Override
    public Response getAccountData(long userId) {
        var user = userMapper.getUser(userId);
        Response r = new Response();
        r.setCode(ResponseCode.OK);
        r.addParameter("userData", user);
        return r;
    }

    @Override
    public Response setAccountData(long userId, String firstName, String lastName, String phone, String email, String password) {
        String hashed = hashService.hashPassword(password);
        userMapper.updateUser(userId, firstName, lastName, phone, email, hashed);
        return new Response();
    }

}
