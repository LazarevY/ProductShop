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
public class UserCalorieDataMapperTest {


    private static SqlSession session;

    @TestConfiguration
    static class UserCalorieDataMapperConf {
        @SneakyThrows
        @Bean
        public UserCalorieDataMapper create() {
            return session.getMapper(UserCalorieDataMapper.class);
        }
    }

    @Autowired
    UserCalorieDataMapper mapper;

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
        mapper.addCalorieData(3,60,170,1,100);
        var lst = mapper.findAll()
                .stream()
                .filter(userCalorieData -> userCalorieData.getUserId().equals(3L)
                        && userCalorieData.getWeight().equals(60L)
                        && userCalorieData.getGrowth().equals(170L)
                        && userCalorieData.getGenderId().equals(1L)
                        && userCalorieData.getCurrentNorm().equals(100L))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.size() > 0);

        var a = mapper.getByUserId(lst.get(0).getUserId());
        Assert.assertEquals(lst.get(0).getCurrentNorm(), a.getCurrentNorm());

        for (var t : lst)
            mapper.deleteByUserId(t.getUserId());

        lst = mapper.findAll()
                .stream()
                .filter(userCalorieData -> userCalorieData.getUserId().equals(3L)
                        && userCalorieData.getWeight().equals(60L)
                        && userCalorieData.getGrowth().equals(170L)
                        && userCalorieData.getGenderId().equals(1L)
                        && userCalorieData.getCurrentNorm().equals(100L))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.isEmpty());
    }

    @AfterClass
    public static void cleanup() {
        session.close();
    }

}