package app.core.rest.front.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteUserPayMethod {
    private long userId;
    private String token;
    private long methodId;
}
