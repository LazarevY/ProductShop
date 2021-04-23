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


@RunWith(SpringRunner.class)
public class OrderProductMapperTest {


    private static SqlSession session;

    @TestConfiguration
    static class OrderProductMapperConf {
        @SneakyThrows
        @Bean
        public OrderProductMapper create() {
            return session.getMapper(OrderProductMapper.class);
        }
    }

    @Autowired
    OrderProductMapper mapper;

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
        mapper.addOrderProduct(1L,1L, 5);
        var lst = mapper.getOrderProduct(1L, 1L)
                .stream()
                .filter(orderProduct -> orderProduct.getOrderId().equals(1L) && orderProduct.getProductId().equals(1L))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.size() > 0);

        var a = mapper.getOrderProduct(lst.get(0).getOrderId(), lst.get(0).getProductId());
        Assert.assertEquals(lst.get(0).getCountOfProducts(), a.get(0).getCountOfProducts());

        for (var t : lst)
            mapper.deleteOrderProduct(t.getOrderId(), t.getProductId());

        lst = mapper.getOrderProduct(1L, 1L)
                .stream()
                .filter(orderProduct -> orderProduct.getOrderId().equals(1L) && orderProduct.getProductId().equals(1L))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.isEmpty());
    }

    @AfterClass
    public static void cleanup() {
        session.close();
    }

}