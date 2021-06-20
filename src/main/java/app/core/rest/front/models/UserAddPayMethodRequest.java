package app.core.rest.front.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAddPayMethodRequest {
    private long userId;
    private long id;
    private String card;
    private String date;
    private String token;
}
