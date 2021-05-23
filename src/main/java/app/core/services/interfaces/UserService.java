package app.core.services.interfaces;

import app.core.requests.CreateUserRequest;
import app.data.modeles.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByEmail(String  email);
    User findById(long id);
    User createUser(CreateUserRequest request) throws Exception;
}
