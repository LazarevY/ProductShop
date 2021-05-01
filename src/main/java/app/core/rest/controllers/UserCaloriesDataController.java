package app.core.rest.controllers;

import app.data.mappers.UserCalorieDataMapper;
import app.data.mappers.UserMapper;
import app.data.modeles.User;
import app.data.modeles.UserCalorieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCaloriesDataController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCalorieDataMapper userCalorieDataMapper;

    @RequestMapping("/uscd")
    public List<User> getUsers(){
        System.out.println("Proccess get users");
        var users = userMapper.findAll();
        users.forEach(System.out::println);
        return userMapper.findAll();
    }
    public List<UserCalorieData> getCalorieData(){
        System.out.println("Proccess get calorie data");
        var users = userCalorieDataMapper.findAll();
        users.forEach(System.out::println);
        return userCalorieDataMapper.findAll();
    }
}
