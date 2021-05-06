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
import java.util.Date;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class OrderMapperTest {


    private static SqlSession session;

    @TestConfiguration
    static class OrderMapperConf {
        @SneakyThrows
        @Bean
        public OrderMapper create() {
            return session.getMapper(OrderMapper.class);
        }
    }

    @Autowired
    OrderMapper mapper;

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
        mapper.addOrder(1,1,1,1000, 100, new Date(121,4,26));
        var lst = mapper.findAll()
                .stream()
                .filter(order -> order.getUserId().equals(1L) && order.getOrderStatusId().equals(1L) && order.getStoreId().equals(1L) && order.getCommonPrice().equals(1000L)&& order.getStockPrice().equals(100L))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.size() > 0);

        var a = mapper.getById(lst.get(0).getId());
        Assert.assertEquals(lst.get(0).getCommonPrice(), a.getCommonPrice());

        for (var t : lst)
            mapper.deleteById(t.getId());

        lst = mapper.findAll()
                .stream()
                .filter(order -> order.getUserId().equals(1L) && order.getOrderStatusId().equals(1L) && order.getStoreId().equals(1L) && order.getCommonPrice().equals(1000L)&& order.getStockPrice().equals(100L))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.isEmpty());
    }

    @AfterClass
    public static void cleanup() {
        session.close();
    }

}