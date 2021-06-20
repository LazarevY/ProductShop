package app.core.services.interfaces;

import app.core.exceptions.UserAlwaysRegisteredException;
import app.core.requests.CreateUserRequest;
import app.core.rest.front.models.*;
import app.data.modeles.PayMethod;
import app.data.modeles.User;
import app.data.modeles.UserAddress;
import app.data.modeles.UserCalorieData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findByEmail(String  email);
    User findById(long id);
    UserCalorieData getCalorieData(long userId);
    User createUser(CreateUserRequest request) throws UserAlwaysRegisteredException;
    void updateUserData(UpdateUserData request);
    void addUserCalorieData(UserAddCalorieDataRequest request);
    void updateUserCalorieData(UserAddCalorieDataRequest request);
    void removeUser(long id);
    List<UserAddress> getUserAddressList(UserAddressRequest req);
    void addUserAddress(UserAddAddressRequest req);
    void updateUserAddress(UserAddAddressRequest req);
    void deleteUserAddress(DeleteUserAddress req);
    List<PayMethod> getUserPayMethodsList(UserAddPayMethodRequest req);
    void addUserAddPayMethod(UserAddPayMethodRequest req);
    void updateUserPayMethod(UserAddPayMethodRequest req);
    void deleteUserPayMethod(DeleteUserPayMethod req);
    UserAddress getUserAddress(long addressId);
}
