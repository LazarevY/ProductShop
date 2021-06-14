package app.data.mappers;

import app.data.modeles.Product;
import app.data.modeles.ProductInStore;
import app.data.modeles.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StocksProductsMapper {

    void addStockToProduct(@Param("stockId") long stockId, @Param("productId") long productId);

    List<Stock> getAllStocksForProduct(@Param("productId") long productId);

    Stock getStockForProductInStore(@Param("productId") long productId, @Param("storeId") long storeId);

    List<Product> getAllProductsForStock(@Param("stockId") long stockId);

    List<ProductInStore> getAllProductsForStockInStore(@Param("stockId") long stockId, @Param("storeId") long storeId);

    void deleteProductStock(@Param("stockId") long stockId, @Param("productId") long productId);

}
