package app.data.modeles;

import lombok.Data;

@Data
public class UserCalorieData {

    private long userId;
    private long weight;
    private long growth;
    private long genderId;
    private long currentNorm;
    private boolean funcEnable;

}
