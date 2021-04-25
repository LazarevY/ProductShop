package app.core.services.interfaces;

import app.core.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface AuthorizationService {
    Response registerUser(String firstName, String lastName, String phone, String email, String password);
    Response loginUser(String email, String enteredPassword);
}
