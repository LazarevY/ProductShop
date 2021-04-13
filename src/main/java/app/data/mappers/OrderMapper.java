package app.data.mappers;

import app.data.modeles.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM orders WHERE order_id = #{id}")
    @ResultMap("OrderResultMap")
    Order getOrder(@Param("id") Long id);

    @Select("SELECT * FROM orders")
    @ResultMap("OrderResultMap")
    List<Order> findAll();

    @Insert("INSERT INTO orders (order_status_id, user_id, store_id, common_price, stock_price, order_date) " +
            "VALUES (#{orderStatusId}, #{userId}, #{storeId}, #{commonPrice}, #{stockPrice}, #{date})")
    void addOrder(Long orderStatusId, Long userId, Long storeId, Long commonPrice, Long stockPrice, String date);

}