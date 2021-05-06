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
public class ProductMetadataMapperTest {


    private static SqlSession session;

    @TestConfiguration
    static class ProductMetadataMapperConf {
        @SneakyThrows
        @Bean
        public ProductMetadataMapper create() {
            return session.getMapper(ProductMetadataMapper.class);
        }
    }

    @Autowired
    ProductMetadataMapper mapper;

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
        mapper.addProductMetadata("TEST_PRODUCT_PIC");
        var lst = mapper.findAll()
                .stream()
                .filter(productMetadata -> productMetadata.getProductFileName().equals("TEST_PRODUCT_PIC"))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.size() > 0);

        var a = mapper.getProductMetadataById(lst.get(0).getMetadataId());
        Assert.assertEquals(lst.get(0).getProductFileName(), a.getProductFileName());

        for (var t : lst)
            mapper.deleteById(t.getMetadataId());

        lst = mapper.findAll()
                .stream()
                .filter(productMetadata -> productMetadata.getProductFileName().equals("TEST_PRODUCT_PIC"))
                .collect(Collectors.toList());
        Assert.assertTrue(lst.isEmpty());
    }

    @AfterClass
    public static void cleanup() {
        session.close();
    }

}