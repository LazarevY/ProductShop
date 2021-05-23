package app.data.modeles;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {

    public static final Role ADMIN = new Role(0, "ADMIN");
    public static final Role USER = new Role(1, "USER");

    private long id;
    private String value;

}
