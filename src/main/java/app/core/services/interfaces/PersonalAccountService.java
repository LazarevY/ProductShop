package app.core.services.interfaces;

import app.core.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface PersonalAccountService {
    Response getAccountData(long userId);

    Response setAccountData(long userId, String firstName, String lastName, String phone, String email, String password);
}

