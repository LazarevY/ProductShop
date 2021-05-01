package app.core.rest.controllers;

import app.core.response.Response;
import app.core.rest.front.models.RegistrationUser;
import app.core.services.interfaces.RegistrationService;
import app.data.mappers.UserMapper;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/reg")
    public Response registerUser(@RequestBody RegistrationUser user){
        return registrationService.registerUser(
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword()
        );
    }

}
