package app.data.modeles;

import lombok.Data;

/**
 * @author Lazarev Yaroslav
 */

@Data
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
}
