package app.data.mappers;

import app.data.modeles.OrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderStatusMapper {

    OrderStatus getOrderStatus(@Param("id") Long id);

    List<OrderStatus> findAll();

    void addOrderStatus(String name);

    void deleteById(@Param("id") long id);

}