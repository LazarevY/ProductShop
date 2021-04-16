package app.data.modeles;

import lombok.Data;

@Data
public class UserCalorieData {

    private long userId;
    private int weight;
    private int growth;
    private long genderId;
    private int currentNorm;

}
