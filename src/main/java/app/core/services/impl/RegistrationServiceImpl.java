package app.core.services.impl;

import app.core.response.Response;
import app.core.services.interfaces.RegistrationService;
import app.core.services.util.HashService;
import app.data.mappers.UserMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HashService hashService;

    @SneakyThrows
    @Override
    public Response registerUser(String firstName, String lastName, String phone, String email, String password){

        String hashed = hashService.hashPassword(password);
        userMapper.addUser(firstName, lastName, phone, email, hashed, "");
        return new Response();

    }

}
