package app.core.services.util;

import org.springframework.stereotype.Service;

@Service
public interface HashService {
    String hashPassword(String password);
    boolean checkPassword(String password, String hash);
}
