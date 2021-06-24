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
public class ProductCategoryMapperTest {


    private static SqlSession session;

    @TestConfiguration
    static class ProductCategoryMapperConf {
        @SneakyThrows
        @Bean
        public ProductCategoryMapper create() {
            return session.getMapper(ProductCategoryMapper.class);
        }
    }

    @Autowired
    ProductCategoryMapper mapper;

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
        mapper.addProductCategory("Product_Category_Name");
        var lst = mapper.getAll()
                .stream()
                .filter(productCategory -> productCategory.getName().equals("Product_Category_Name"))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.size() > 0);

        var a = mapper.getCategoryById(lst.get(0).getId());
        Assert.assertEquals(lst.get(0).getName(), a.getName());

        for (var t : lst)
            mapper.deleteCategoryById(t.getId());

        lst = mapper.getAll()
                .stream()
                .filter(productCategory -> productCategory.getName().equals("Product_Category_Name"))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.isEmpty());
    }

    @AfterClass
    public static void cleanup() {
        session.close();
    }

}