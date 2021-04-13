package app.data.mappers;

import app.data.modeles.OrderStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderStatusMapper {

    @Select("SELECT * FROM order_status WHERE order_status_id = #{id}")
    @ResultMap("OrderStatusResultMap")
    OrderStatus getOrderStatus(@Param("id") Long id);

    @Select("SELECT * FROM order_status")
    @ResultMap("OrderStatusResultMap")
    List<OrderStatus> findAll();

    @Insert("INSERT INTO order_status (order_status_name) " +
            "VALUES (#{name})")
    void addOrderStatus(String name);

}