package app.core.services.impl;

import app.core.exceptions.UserAlwaysRegisteredException;
import app.core.requests.CreateUserRequest;
import app.core.rest.front.models.*;
import app.core.services.interfaces.AuthorizationService;
import app.core.services.interfaces.UserService;
import app.data.mappers.GenderMapper;
import app.data.mappers.UserAddressMapper;
import app.data.mappers.UserCalorieDataMapper;
import app.data.mappers.UserMapper;
import app.data.modeles.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserCalorieDataMapper userCalorieDataMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private GenderMapper genderMapper;

    @Override
    public User findByEmail(String email) {
        return userMapper.getByEmail(email);
    }

    @Override
    public User findById(long id) {
        return userMapper.getUser(id);
    }

    @Override
    public UserCalorieData getCalorieData(long userId) {
        return userCalorieDataMapper.getByUserId(userId);
    }

    @Override
    public User createUser(CreateUserRequest request) throws UserAlwaysRegisteredException {
        User u = userMapper.getByEmail(request.getEmail());
        if (u != null)
            throw new UserAlwaysRegisteredException(request.getEmail(),
                    String.format("User with email %s already exist", request.getEmail()));
        request.setPassword(authorizationService.encode(request.getPassword()));
        userMapper.addUser(request.getFirstName(), request.getLastName(), request.getPhone(), request.getEmail(), request.getPassword());
        var usr = userMapper.getByEmail(request.getEmail());
        userMapper.addUserRole(usr.getId(), Role.USER.getId());
        return usr;
    }

    @Override
    public void updateUserData(UpdateUserData request) {
        userMapper.updateUserMain(request.getId(), request.getFirstName(), request.getLastName(), request.getPhone());
    }

    @Override
    public void addUserCalorieData(UserAddCalorieDataRequest request) {
        request.setCurrentNorm(getCurrentNorm(request));
        userCalorieDataMapper.addCalorieData(request.getUserId(),
                request.getWeight(),
                request.getGrowth(),
                request.getAge(),
                request.getGender().getId(),
                request.getCurrentNorm(),
                request.isFuncEnable());
    }

    @Override
    public void updateUserCalorieData(UserAddCalorieDataRequest request) {
        UserCalorieData calorieData = userCalorieDataMapper.getByUserId(request.getUserId());
        request.setCurrentNorm(getCurrentNorm(request));
        if (calorieData == null) {
            addUserCalorieData(request);
            return;
        }
        userCalorieDataMapper.updateCalorieData(request.getUserId(),
                request.getWeight(),
                request.getGrowth(),
                request.getAge(),
                request.getGender().getId(),
                request.getCurrentNorm(),
                request.isFuncEnable());
    }


    @Override
    public void removeUser(long id) {

    }

    @Override
    public List<UserAddress> getUserAddressList(UserAddressRequest req) {
        return userAddressMapper.getAllByUserId(req.getId());
    }

    @Override
    public void addUserAddress(UserAddAddressRequest req) {
        userAddressMapper.addUserAddress(req.getUserId(), req.getAddress());
    }

    @Override
    public void updateUserAddress(UserAddAddressRequest req) {
        userAddressMapper.updateAddress(req.getId(), req.getUserId(), req.getAddress());
    }

    @Override
    public void deleteUserAddress(DeleteUserAddress req) {
        userAddressMapper.deleteById(req.getId());
    }

    @Override
    public UserAddress getUserAddress(long addressId) {
        return userAddressMapper.getById(addressId);
    }

    int getCurrentNorm(UserAddCalorieDataRequest req) {
        Gender gender = genderMapper.getGender(req.getGender().getId());
        return gender.getName().equals("Мужской") ? (int) (66.5 + (13.75 * req.getWeight()) + (5.003 * req.getGrowth()) - (6.775 * req.getAge())) :
                (int) (655.1 + (9.653 * req.getWeight()) + (1.85 * req.getGrowth()) - (4.676 * req.getAge()));
    }
}
