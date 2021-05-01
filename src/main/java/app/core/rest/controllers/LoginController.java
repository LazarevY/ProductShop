package app.core.rest.controllers;

import app.data.mappers.UserMapper;
import app.data.modeles.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/log")
    public List<User> getUsers(){
        System.out.println("Proccess get users");
        var users = userMapper.findAll();
        users.forEach(System.out::println);
        return userMapper.findAll();
    }
}
