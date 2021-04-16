package app.data.modeles;

import lombok.Data;

@Data
public class UserAddress {
    private long id;
    private long userId;
    private String address;
}
