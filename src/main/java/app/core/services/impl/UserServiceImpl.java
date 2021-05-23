package app.core.services.impl;

import app.core.requests.CreateUserRequest;
import app.core.services.interfaces.UserService;
import app.data.mappers.UserMapper;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        return userMapper.getByEmail(email);
    }

    @Override
    public User findById(long id) {
        return userMapper.getUser(id);
    }

    @Override
    public User createUser(CreateUserRequest request) throws Exception {
        if (userMapper.getByEmail(request.getEmail()) != null)
            throw new Exception(String.format("User with email %s already exist", request.getEmail()));
        userMapper.addUser(request.getFirstName(), request.getLastName(), request.getPhone(), request.getEmail(), request.getPasswordHash());
        return userMapper.getByEmail(request.getEmail());
    }
}
