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
public class UserAddressMapperTest {


    private static SqlSession session;

    @TestConfiguration
    static class UserAddressMapperConf {
        @SneakyThrows
        @Bean
        public UserAddressMapper create() {
            return session.getMapper(UserAddressMapper.class);
        }
    }

    @Autowired
    UserAddressMapper mapper;

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
        mapper.addUserAddress(1,"TEST_ADDRESS");
        var lst = mapper.findAll()
                .stream()
                .filter(userAddress -> userAddress.getUserId().equals(1L)
                        && userAddress.getAddress().equals("TEST_ADDRESS"))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.size() > 0);

        var a = mapper.getById(lst.get(0).getId());
        Assert.assertEquals(lst.get(0).getAddress(), a.getAddress());

        for (var t : lst)
            mapper.deleteById(t.getId());

        session.commit();
        var lst1 = mapper.findAll();
        lst = lst1
                .stream()
                .filter(userAddress -> userAddress.getUserId().equals(1L)
                        && userAddress.getAddress().equals("TEST_ADDRESS"))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.isEmpty());
    }

    @AfterClass
    public static void cleanup() {
        session.close();
    }

}