package app.data.mappers;

import app.data.modeles.StockClause;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockClauseMapper {

    void addStockClause(@Param("stockClauseId") long stockClauseId, @Param("value") int value);

    StockClause getById(@Param("id") long id);

    List<StockClause> findAll();

    void updateStockClause(@Param("id") long id, @Param("stockClauseItemId") long stockClauseItemId, @Param("value") int value);

    void deleteById(@Param("id") long id);

}
