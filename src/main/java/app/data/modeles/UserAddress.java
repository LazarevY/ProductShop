package app.data.modeles;

import lombok.Data;

@Data
public class UserAddress {
    private long id;
    private Long userId;
    private String address;
}
