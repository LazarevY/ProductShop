package app.core.rest.front.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAddressRequest {
    private long id;
    private String token;
}
