package app.core.services.impl;

import app.core.exceptions.UserAlwaysRegisteredException;
import app.core.requests.CreateUserRequest;
import app.core.services.interfaces.AuthorizationService;
import app.core.services.interfaces.UserService;
import app.data.mappers.UserMapper;
import app.data.modeles.Role;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public User findByEmail(String email) {
        return userMapper.getByEmail(email);
    }

    @Override
    public User findById(long id) {
        return userMapper.getUser(id);
    }

    @Override
    public User createUser(CreateUserRequest request) throws UserAlwaysRegisteredException {
        User u = userMapper.getByEmail(request.getEmail());
        if (u != null)
            throw new UserAlwaysRegisteredException(request.getEmail(),
                    String.format("User with email %s already exist", request.getEmail()));
        request.setPassword(authorizationService.encode(request.getPassword()));
        userMapper.addUser(request.getFirstName(), request.getLastName(), request.getPhone(), request.getEmail(), request.getPassword());
        var usr =  userMapper.getByEmail(request.getEmail());
        userMapper.addUserRole(usr.getId(), Role.USER.getId());
        return usr;
    }
}
