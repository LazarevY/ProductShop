package app.data.mappers;

import app.data.modeles.Order;
import app.data.modeles.OrderProduct;
import app.data.modeles.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderProductMapper {
    List<OrderProduct> getOrderProduct(@Param("orderId") long orderId);

    void addOrderProduct(@Param("orderId") long orderId, @Param("productId") long productId, @Param("countOfProducts") int countOfProducts);

    int deleteOrderProduct(@Param("orderId") long orderId, @Param("productId") long productId);

}