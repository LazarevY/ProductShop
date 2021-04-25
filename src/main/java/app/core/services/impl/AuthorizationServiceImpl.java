package app.core.services.impl;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.services.interfaces.AuthorizationService;
import app.core.services.interfaces.LoginService;
import app.core.services.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private LoginService loginService;

    @Override
    public Response registerUser(String firstName, String lastName, String phone, String email, String password) {
        Response r = loginService.checkUserRegistered(email);

        if ((Boolean)r.getParameter("registered")){
            r.setMessage("User with such email already exit");
            r.setCode(ResponseCode.ERROR);
            return r;
        }

        return registrationService.registerUser(firstName, lastName, phone, email, password);
    }

    @Override
    public Response loginUser(String email, String enteredPassword) {
        return loginService.loginUser(email, enteredPassword);
    }
}
