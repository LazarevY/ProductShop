package app.core.services.interfaces;

import app.core.exceptions.UserAlwaysRegisteredException;
import app.core.requests.CreateUserRequest;
import app.core.rest.front.models.UpdateUserData;
import app.core.rest.front.models.UserAddCalorieDataRequest;
import app.data.modeles.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByEmail(String  email);
    User findById(long id);
    User createUser(CreateUserRequest request) throws UserAlwaysRegisteredException;
    void updateUserData(UpdateUserData request);
    void addUserCalorieData(UserAddCalorieDataRequest request);
    void updateUserCalorieData(UserAddCalorieDataRequest request);
    void removeUser(long id);
}
