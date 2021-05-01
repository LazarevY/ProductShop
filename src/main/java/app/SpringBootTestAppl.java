package app;

import app.data.mappers.GenderMapper;
import app.data.mappers.OrderProductMapper;
import app.data.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Lazarev Yaroslav
 */

@SpringBootApplication
public class SpringBootTestAppl{

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestAppl.class, args);
    }

}
