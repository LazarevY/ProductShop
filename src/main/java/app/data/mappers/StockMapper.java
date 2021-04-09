package app.data.mappers;

import app.data.modeles.Stock;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StockMapper {

    @Select("SELECT * FROM Stock WHERE stock_id = #{id}")
    Stock getStock(@Param("id") Long id);

    @Select("SELECT * FROM Stock")
    List<Stock> findAll();

    @Insert("INSERT INTO Stock (name, start_date, end_date) VALUES (#{name}, #{startDate}, #{endDate})")
    void addStock(String name, String startDate, String endDate);

}
