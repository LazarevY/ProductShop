package app;

import app.data.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lazarev Yaroslav
 */

@SpringBootApplication
public class SpringBootTestAppl implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void run(String...args) {
        var users = userMapper.findAll();

        for (var u: users)
            System.out.println(u.toString());
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestAppl.class, args);
    }

}
