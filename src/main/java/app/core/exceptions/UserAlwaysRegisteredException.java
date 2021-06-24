package app.core.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAlwaysRegisteredException extends Exception{
    private String email;
    public UserAlwaysRegisteredException(String email, String message){
        super(message);
        this.email = email;
    }
}
