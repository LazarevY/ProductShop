package app.core.services.util;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class HashServiceImpl implements HashService {

    private MessageDigest digest;

    @Override
    public String hashPassword(String password) {
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }

    @Override
    public boolean checkPassword(String password, String trueHash) {
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return trueHash.equals(new String(hash));
    }

    @SneakyThrows
    @PostConstruct
    private void init(){
        digest = MessageDigest.getInstance("SHA-256");;
    }
}
