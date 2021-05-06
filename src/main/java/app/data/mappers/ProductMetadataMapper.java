package app.data.mappers;

import app.data.modeles.ProductMetadata;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMetadataMapper {

    ProductMetadata getProductMetadataById(@Param("id") long id);

    List<ProductMetadata> findAll();

    void addProductMetadata(@Param("fileName") String fileName);

    void updateProductMetadata(@Param("id") long id, @Param("fileName") String fileName);

    void deleteById(@Param("id") long id);

}
