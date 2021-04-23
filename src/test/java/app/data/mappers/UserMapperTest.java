package app.data.mappers;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Reader;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
public class UserMapperTest {


    private static SqlSession session;

    @TestConfiguration
    static class UserMapperConf {
        @SneakyThrows
        @Bean
        public UserMapper create() {
            return session.getMapper(UserMapper.class);
        }
    }

    @Autowired
    UserMapper mapper;

    @SneakyThrows
    @BeforeClass
    public static void setup() {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        SqlSessionFactory sessionFactory = builder.build(reader);
        session = sessionFactory.openSession();
    }

    @Test
    public void test000() {
        mapper.addUser("TEST_NAME1","TEST_NAME2","111111111","test@gmail.com","123");
        var lst = mapper.findAll()
                .stream()
                .filter(user -> user.getFirstName().equals("TEST_NAME1")
                        && user.getLastName().equals("TEST_NAME2")
                        && user.getPhone().equals("111111111")
                        && user.getEmail().equals("test@gmail.com")
                        && user.getPasswordHash().equals("123"))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.size() > 0);

        var a = mapper.getUser(lst.get(0).getId());
        Assert.assertEquals(lst.get(0).getEmail(), a.getEmail());

        for (var t : lst)
            mapper.deleteById(t.getId());

        lst = mapper.findAll()
                .stream()
                .filter(user -> user.getFirstName().equals("TEST_NAME1")
                        && user.getLastName().equals("TEST_NAME2")
                        && user.getPhone().equals("111111111")
                        && user.getEmail().equals("test@gmail.com")
                        && user.getPasswordHash().equals("123"))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.isEmpty());
    }

    @AfterClass
    public static void cleanup() {
        session.close();
    }

}