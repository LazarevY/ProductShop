package app.data.modeles;

import lombok.Data;

@Data
public class UserCalorieData {

    private long userId;
    private int age;
    private double weight;
    private double growth;
    private long genderId;
    private int currentNorm;
    private boolean funcEnable;

}
