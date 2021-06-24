package app.data.mappers;

import app.data.modeles.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StocksStoresMapper {

    void addStockToStore(@Param("stockId") long stockId, @Param("storeId") long storeId);

    Stock getStockFromStore(@Param("stockId") long stockId, @Param("storeId") long storeId);

    List<Stock> getAllStockFromStore(@Param("storeId") long storeId);

    void deleteStockFromStore(@Param("stockId") long stockId, @Param("storeId") long storeId);

}
