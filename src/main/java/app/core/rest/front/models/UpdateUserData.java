package app.core.rest.front.models;

import lombok.Data;

@Data
public class UpdateUserData {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String token;
}
