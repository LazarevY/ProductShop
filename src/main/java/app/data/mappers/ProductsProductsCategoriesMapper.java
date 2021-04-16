package app.data.mappers;

import app.data.modeles.Product;
import app.data.modeles.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductsProductsCategoriesMapper {

    List<Product> getProductsByCategory(@Param("id") long id);
    List<ProductCategory> getCategoriesOfProduct(@Param("id") long productId);

}
