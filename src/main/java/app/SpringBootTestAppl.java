package app;

import app.data.mappers.GenderMapper;
import app.data.mappers.OrderProductMapper;
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
    @Autowired
    private GenderMapper genderMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    public void run(String...args) {
        var users = userMapper.findAll();
        var genders = genderMapper.findAll();
        var orderProduct = orderProductMapper.getProductByOrder(2L);

        for (var u: users)
            System.out.println(u.toString());
        for (var u: genders)
            System.out.println(u.toString());
        for (var u: orderProduct)
            System.out.println(u.toString());
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestAppl.class, args);
    }

}
