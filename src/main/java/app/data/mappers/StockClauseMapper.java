package app.data.mappers;

import app.data.modeles.StockClause;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockClauseMapper {

    void addStockClause(@Param("stockClauseItemId") long stockClauseItemId, @Param("value") String value);

    StockClause getById(@Param("id") long id);

    List<StockClause> findAll();

    List<StockClause> getClausesForStock(@Param("stockId") long stockId);

    void updateStockClause(@Param("id") long id, @Param("stockClauseItemId") long stockClauseItemId, @Param("value") String value);

    void deleteById(@Param("id") long id);

}
