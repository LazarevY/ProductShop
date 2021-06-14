package app.data.modeles;

import lombok.Data;

import java.util.List;

/**
 * @author Lazarev Yaroslav
 */

@Data
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String passwordHash;

    private List<Role> roles;
}
