package app.core.rest.front.models;

import app.data.modeles.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAddCalorieDataRequest {

    private long userId;
    private double weight;
    private int age;
    private int growth;
    private Gender gender;
    private boolean funcEnable;
    private int currentNorm;
    private String token;

}
