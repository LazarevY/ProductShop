package app.data.mappers;

import app.data.modeles.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM products WHERE product_id = #{id}")
    @ResultMap("ProductResultMap")
    Product getProduct(@Param("id") Long id);

    @Select("SELECT * FROM products")
    @ResultMap("ProductResultMap")
    List<Product> findAll();

    @Insert("INSERT INTO products (product_name, product_description, product_weight, product_calories, product_metadata_id) " +
            "VALUES (#{name}, #{description}, #{weight}, #{calories}, #{metadataId})")
    void addProduct(String name, String description, Long weight, Long calories, Long metadataId);

}