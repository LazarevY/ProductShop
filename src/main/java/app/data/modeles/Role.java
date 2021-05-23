package app.data.modeles;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {

    private static Role ADMIN = new Role(0, "ADMIN");
    private static Role USER = new Role(1, "USER");

    private long id;
    private String value;

}
