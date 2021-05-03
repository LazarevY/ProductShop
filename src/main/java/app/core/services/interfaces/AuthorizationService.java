package app.core.services.interfaces;

import app.core.front.models.LoginUser;
import app.core.response.Response;
import app.data.modeles.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public interface AuthorizationService {
    ResponseEntity<Response> login(LoginUser loginRequest, String accessToken, String refreshToken);

    ResponseEntity<Response> refresh(String accessToken, String refreshToken);

    User getUserProfile();

    String logout(HttpServletRequest request, HttpServletResponse response);
}
