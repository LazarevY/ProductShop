package app.data.mappers;

import app.data.modeles.Order;
import app.data.modeles.OrderProduct;
import app.data.modeles.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderProductMapper {
    List<OrderProduct> getOrderProduct(@Param("orderId") Long orderId, @Param("productId") Long productId);

    List<Order> getOrderByProduct(@Param("id") Long id);

    List<Product> getProductByOrder(@Param("id") Long id);

    void addOrderProduct(@Param("orderId") Long orderId, @Param("productId") Long productId, @Param("countOfProducts") int countOfProducts);

    int deleteOrderProduct(@Param("orderId") Long orderId, @Param("productId") Long productId);

}