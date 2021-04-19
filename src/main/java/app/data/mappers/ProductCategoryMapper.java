package app.data.mappers;

import app.data.modeles.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {

    void addProductCategory(@Param("name") String name);

    ProductCategory getCategoryById(@Param("id") long id);

    List<ProductCategory> getAll();

    void deleteCategoryById(@Param("id") long id);

    void updateCategory(@Param("id") long id, @Param("name") String name);

}
