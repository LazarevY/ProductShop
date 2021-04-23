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
public class StockClauseMapperTest {


    private static SqlSession session;

    @TestConfiguration
    static class StockClauseMapperConf {
        @SneakyThrows
        @Bean
        public StockClauseMapper create() {
            return session.getMapper(StockClauseMapper.class);
        }
    }

    @Autowired
    StockClauseMapper mapper;

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
        mapper.addStockClause(1,1);
        var lst = mapper.findAll()
                .stream()
                .filter(stockClause -> stockClause.getClauseId().equals(1L) && stockClause.getClauseValue().equals(1L))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.size() > 0);

        var a = mapper.getById(lst.get(0).getId());
        Assert.assertEquals(lst.get(0).getClauseValue(), a.getClauseValue());

        for (var t : lst)
            mapper.deleteById(t.getId());

        lst = mapper.findAll()
                .stream()
                .filter(stockClause -> stockClause.getClauseId().equals(1L) && stockClause.getClauseValue().equals(1L))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.isEmpty());
    }

    @AfterClass
    public static void cleanup() {
        session.close();
    }

}