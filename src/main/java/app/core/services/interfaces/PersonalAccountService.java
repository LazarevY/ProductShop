package app.core.services.interfaces;

import app.core.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface PersonalAccountService {
    Response getAccountData(String email);

    Response setAccountData(long id, String firstName, String lastName, String phone, String email, String password);
}

