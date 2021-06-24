package app.data.mappers;

import app.data.modeles.Product;
import app.data.modeles.ProductInStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Arrays;
import java.util.List;

@Mapper
public interface ProductsStoresMapper {

    void addProductToStore(@Param("storeId") long storeId,
                           @Param("productId") long productId,
                           @Param("count") int count,
                           @Param("price") int price);

    void updateProductData(@Param("storeId") long storeId,
                           @Param("productId") long productId,
                           @Param("newStoreId") long newStoreId,
                           @Param("count") int count,
                           @Param("price") int price);

    void updateProductCount(@Param("storeId") long storeId,
                            @Param("productId") long productId,
                            @Param("newCount") int newCount);

    List<ProductInStore> getAllInStore(@Param("storeId") long storeId);

    List<ProductInStore> getAllInStoreFilteredOnlyByPrice(
            @Param("storeId") long storeId,
            @Param("priceLow") int priceLow,
            @Param("priceHigh") int priceHigh);

    List<ProductInStore> getAllInStoreFilteredByPriceAndName(
            @Param("storeId") long storeId,
            @Param("priceLow") int priceLow,
            @Param("priceHigh") int priceHigh,
            @Param("name") String name);

    List<ProductInStore> getAllInStoreFilteredOnlyByCategories(
            @Param("storeId") long storeId,
            @Param("categoriesId") int[] categoriesId);

    List<ProductInStore> getAllInStoreFilteredByPriceAndNameAndCategory(
            @Param("storeId") long storeId,
            @Param("priceLow") int priceLow,
            @Param("priceHigh") int priceHigh,
            @Param("name") String name,
            @Param("categoriesId") int[] categoriesId);

    List<ProductInStore> getUserOrientedList(
            @Param("storeId") long storeId,
            @Param("userId") long userId,
            @Param("priceLow") int priceLow,
            @Param("priceHigh") int priceHigh,
            @Param("name") String name,
            @Param("categoriesId") int[] categoriesId
    );

    ProductInStore getProductData(@Param("storeId") long storeId, @Param("productId") long productId);

    List<ProductInStore> getProductsData(@Param("storeId") long storeId, @Param("productIds") List<Long> productIds);

    void deleteByProductAndStoreId(@Param("productId") long productId, @Param("storeId") long storeId);

}
