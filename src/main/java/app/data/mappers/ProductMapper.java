package app.data.mappers;

import app.data.modeles.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM Product WHERE product_id = #{id}")
    Product getProduct(@Param("id") Long id);

    @Select("SELECT * FROM Product")
    List<Product> findAll();

    @Insert("INSERT INTO Product (product_name, product_weight, price, energy_price, image) VALUES (#{name}, #{weight}, #{price}, #{energyPrice}, #{image})")
    void addProduct(String name, Long weight, Long price, Long energyPrice, String image);

}
