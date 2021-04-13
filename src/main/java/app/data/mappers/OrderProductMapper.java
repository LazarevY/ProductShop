package app.data.mappers;

import app.data.modeles.Product;
import app.data.modeles.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderProductMapper {

    List<Order> getOrderByProduct(@Param("id") Long id);
    List<Product> getProductByOrder(@Param("id") Long id);

    void addOrderProduct(Long orderId, Long productId, int countOfProducts);
    int deleteOrderProduct(Long orderId, Long productId);

}