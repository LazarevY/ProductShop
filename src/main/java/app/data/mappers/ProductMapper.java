package app.data.mappers;

import app.data.modeles.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM products WHERE product_id = #{id}")
    @ResultMap("ProductResultMap")
    Product getProduct(@Param("id") Long id);

    @Select("SELECT * FROM products")
    @ResultMap("ProductResultMap")
    List<Product> findAll();

    void addProduct(
            @Param("name") String name,
            @Param("description") String description,
            @Param("weight") Long weight,
            @Param("calories") Long calories,
            @Param("metadataId") Long metadataId);

    void updateProduct(
            @Param("id") long id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("weight") Long weight,
            @Param("calories") Long calories,
            @Param("metadataId") Long metadataId);

    void deleteProductById(@Param("id") long id);

}