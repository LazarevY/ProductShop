package app.core.rest.controllers;

import app.core.response.Response;
import app.core.rest.front.models.RegistrationUser;
import app.core.services.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/reg")
    public Response registerUser(@RequestBody RegistrationUser user){

        System.out.println(user.toString());

        return new Response();
//        return registrationService.registerUser(
//                user.getFirstName(),
//                user.getLastName(),
//                user.getPhone(),
//                user.getEmail(),
//                user.getPassword()
//        );
    }

}
