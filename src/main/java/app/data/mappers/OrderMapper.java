package app.data.mappers;

import app.data.modeles.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM orders WHERE order_id = #{id}")
    @ResultMap("OrderResultMap")
    Order getById(@Param("id") long id);

    @Select("SELECT * FROM orders")
    @ResultMap("OrderResultMap")
    List<Order> findAll();

    void addOrder(long orderStatusId, long userId, long storeId, int commonPrice, int stockPrice, String date);

    @Delete("DELETE FROM orders WHERE order_id = #{id}")
    void deleteById(long id);

}