package app.core.services.impl;

import app.core.response.Response;
import app.core.response.ResponseCode;
import app.core.services.interfaces.UserCaloriesDataService;
import app.data.mappers.UserCalorieDataMapper;
import app.data.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCaloriesDataServiceImpl implements UserCaloriesDataService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCalorieDataMapper userCalorieDataMapper;

    @Override
    public Response getDataCalories(long userId) {
        var calorie = userCalorieDataMapper.getByUserId(userId);
        Response r = new Response();
        r.setCode(ResponseCode.OK);
        r.addParameter("calorieData", calorie);
        return r;
    }

    @Override
    public Response setDataCalories(long userId, int weight, int growth, Long gender) {
        int currentNorm = 0; //TODO:need calculate this parameter
        userCalorieDataMapper.updateCalorieData(userId, weight, growth, gender, currentNorm);
        return new Response();
    }
}
