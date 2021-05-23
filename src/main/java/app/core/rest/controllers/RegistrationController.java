package app.core.rest.controllers;

import app.core.requests.CreateUserRequest;
import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.services.interfaces.UserService;
import app.data.modeles.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

@RestController
@Log
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/reg")
    public Response registerUser(@RequestBody CreateUserRequest user){

        System.out.println(user.toString());

        try {
            User registered = userService.createUser(user);
            System.out.println(registered);
        } catch (Exception e) {
            e.printStackTrace();
            log.log(Level.WARNING, "Can't user reg");
        }


        return new Response(ResponseCode.OK, "Ok");


    }

}

