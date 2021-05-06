package app.data.mappers;

import app.data.modeles.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface StockMapper {

    void addStock(@Param("name") String name, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    void updateStock(@Param("id") long id, @Param("name") String name, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    Stock getById(@Param("id") long id);

    List<Stock> findAll();

    void deleteById(@Param("id") long id);
}
