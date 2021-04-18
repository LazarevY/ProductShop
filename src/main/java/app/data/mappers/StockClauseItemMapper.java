package app.data.mappers;

import app.data.modeles.StockClauseItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockClauseItemMapper {

    void addStockClauseItem(@Param("fieldName") String fieldName);

    StockClauseItem getStockClauseItemById(@Param("id") long id);

    List<StockClauseItem> findAll();

    void deleteById(@Param("id") long id);

    void updateStockClause(@Param("id") long id, @Param("fieldName") String fieldName);

}
