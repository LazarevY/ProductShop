package app.core.services.impl;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.services.interfaces.LoginService;
import app.core.services.util.HashService;
import app.data.mappers.UserMapper;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HashService hashService;

    @Override
    public Response checkUserRegistered(String email){
        var user = userMapper.getByEmail(email);
        Response r = new Response();
        r.setCode(ResponseCode.OK);
        r.addParameter("registered", user != null);
        return r;
    }

    @Override
    public Response loginUser(String email, String enteredPassword){

        Response r = checkUserRegistered(email);

        if (!(Boolean) r.getParameter("registered")){
            r.setCode(ResponseCode.ERROR);
            r.setMessage("Incorrect password or login");
            return r;
        }

        var user = userMapper.getByEmail(email);

        boolean correctPassword = hashService.checkPassword(enteredPassword, user.getPasswordHash());
        r.addParameter("login", correctPassword);
        r.setMessage(correctPassword? "" : "Incorrect password or login");
        r.setCode(correctPassword? ResponseCode.OK: ResponseCode.ERROR);
        if (correctPassword){
            r.addParameter("userData", user);
        }
        return r;

    }

}
