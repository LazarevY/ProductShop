package app.data.mappers;

import app.data.modeles.OrderStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderStatusMapper {

    @Select("SELECT * FROM Order_Status WHERE status_id = #{id}")
    OrderStatus getOrderStatus(@Param("id") Long id);

    @Select("SELECT * FROM Order_Status")
    List<OrderStatus> findAll();

    @Insert("INSERT INTO Order_Status (status_name) VALUES (#{name})")
    void addOrderStatus(String name);

}