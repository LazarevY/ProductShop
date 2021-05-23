package app.core.services.impl;


import app.core.services.interfaces.AuthorizationService;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean auth(User user, String pass) {
        return passwordEncoder.matches(pass, user.getPasswordHash());
    }

    @Override
    public String encode(String str) {
        return passwordEncoder.encode(str);
    }
}
