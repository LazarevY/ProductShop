package app.data.mappers;

import app.data.modeles.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductsStoresMapper {

    List<Product> getAllInStore(@Param("storeId") long storeId);

    List<Product> getAllInStoreFilteredOnlyByPrice(
            @Param("storeId") long storeId,
            @Param("priceLow") int priceLow,
            @Param("priceHigh") int priceHigh);

    List<Product> getAllInStoreFilteredByPriceAndName(
            @Param("storeId") long storeId,
            @Param("priceLow") int priceLow,
            @Param("priceHigh") int priceHigh,
            @Param("name") String name);

    List<Product> getAllInStoreFilteredOnlyByCategories(
            @Param("storeId") long storeId,
            @Param("categoriesId") int[] categoriesId);

    List<Product> getAllInStoreFilteredByPriceAndNameAndCategory(
            @Param("storeId") long storeId,
            @Param("priceLow") int priceLow,
            @Param("priceHigh") int priceHigh,
            @Param("name") String name,
            @Param("categoriesId") int[] categoriesId);


}
