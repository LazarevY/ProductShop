package app.data.mappers;

import app.data.modeles.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM orders WHERE order_id = #{id}")
    @ResultMap("OrderResultMap")
    Order getById(@Param("id") long id);

    @Select("SELECT * FROM orders")
    @ResultMap("OrderResultMap")
    List<Order> findAll();

    int addOrder(
            @Param("orderStatusId") long orderStatusId,
            @Param("userId") long userId,
            @Param("storeId") long storeId,
            @Param("commonPrice") int commonPrice,
            @Param("stockPrice") int stockPrice,
            @Param("date") Date date);

    @Delete("DELETE FROM orders WHERE order_id = #{id}")
    void deleteById(long id);

    List<Order> getByUserId(@Param("userId") long userId);
}