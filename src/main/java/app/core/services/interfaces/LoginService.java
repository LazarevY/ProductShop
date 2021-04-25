package app.core.services.interfaces;

import app.core.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    Response checkUserRegistered(String email);
    Response loginUser(String email, String enteredPassword);
}
