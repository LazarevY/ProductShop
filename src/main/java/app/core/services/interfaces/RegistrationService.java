package app.core.services.interfaces;

import app.core.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    Response registerUser(String firstName, String lastName, String phone, String email, String password);
}
