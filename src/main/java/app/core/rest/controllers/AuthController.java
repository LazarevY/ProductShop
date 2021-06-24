package app.core.rest.controllers;

import app.core.exceptions.UserAlwaysRegisteredException;
import app.core.front.models.LoginUser;
import app.core.requests.CreateUserRequest;
import app.core.requests.LoginRequest;
import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.security.JwtProvider;
import app.core.security.dto.ApiResponseMessage;
import app.core.security.util.SecurityCipher;
import app.core.services.interfaces.AuthorizationService;
import app.core.services.interfaces.UserService;
import app.data.modeles.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@RestController
@Log
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/api/reg")
    public Response registerUser(@RequestBody CreateUserRequest user){

        try {
            User registered = userService.createUser(user);
            log.log(Level.FINE, String.format("New user created: %s", registered.toString()));
        } catch (UserAlwaysRegisteredException e) {
            log.log(Level.WARNING, "Can't reg user with email " + e.getEmail());
            return new Response(ResponseCode.ERROR, "Email is busy", Map.of("regSuccess", false));
        }

        return new Response(ResponseCode.OK, "Ok", Map.of("regSuccess", true));

    }

    @PostMapping("/api/log")
    public Response loginUser(@RequestBody LoginRequest request){
        User byEmail = userService.findByEmail(request.getEmail());
        if (byEmail == null){
            return new Response(ResponseCode.ERROR, "Not registered email or incorrect password", Map.of("authSuccess", false));
        }

        boolean successfulAuth = authorizationService.auth(byEmail, request.getPassword());

        if (successfulAuth){
            String token = jwtProvider.generateToken(byEmail);
            return new Response(ResponseCode.OK, "", Map.of("token", token, "authSuccess", true));
        }
        else {
            return new Response(ResponseCode.ERROR, "Not registered email or incorrect password", Map.of("authSuccess", false));
        }

    }
}
