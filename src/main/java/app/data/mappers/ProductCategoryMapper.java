package app.data.mappers;

        import app.data.modeles.ProductCategory;
        import org.apache.ibatis.annotations.Insert;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Param;
        import org.apache.ibatis.annotations.Select;

        import java.util.List;

@Mapper
public interface ProductCategoryMapper {

    @Select("SELECT * FROM Product_Category WHERE product_category_id = #{id}")
    ProductCategory getProductCategory(@Param("id") Long id);

    @Select("SELECT * FROM Product_Category")
    List<ProductCategory> findAll();

    @Insert("INSERT INTO Product_Category (name) VALUES (#{name})")
    void addProductCategory(String name);

}
