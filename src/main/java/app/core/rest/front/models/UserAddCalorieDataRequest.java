package app.core.rest.front.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAddCalorieDataRequest {

    private long userId;
    private long weight;
    private long growth;
    private long genderId;
    private long currentNorm;

}
