package app.core.rest.front.models;

import lombok.Data;

@Data
public class RegistrationUser {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
}
