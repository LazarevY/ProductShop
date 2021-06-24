package app.core.services.interfaces;

import app.data.modeles.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthorizationService {
    public boolean auth(User user, String pass);
    public String encode(String str);
}
